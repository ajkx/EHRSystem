package com.victory.ehrsystem.domain.sys;

import com.victory.ehrsystem.util.StringUtil;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色表
 *
 * @author ajkx_Du
 * @create 2016-10-27 10:25
 */
@Entity
@Table(name = "Sys_role")
public class SysRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany(targetEntity = SysResource.class)
    @JoinTable(name = "role_resource",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"))
    private Set<SysResource> resources = new HashSet<SysResource>();


    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<User>();

    @Column
    private boolean available;

    public SysRole() {

    }

    public SysRole(String name, String description, Set<SysResource> resources, boolean available) {
        this.name = name;
        this.description = description;
        this.resources = resources;
        this.available = available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<SysResource> getResources() {
        return resources;
    }

    public void setResources(Set<SysResource> resources) {
        this.resources = resources;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    //public void setResourcesStr(List<Long> list) {
    //    if (CollectionUtils.isEmpty(list)) {
    //        this.resources  = "";
    //    }
    //    StringBuilder s = new StringBuilder();
    //    for (Long resourceId : list) {
    //        s.append(resourceId);
    //        s.append(",");
    //    }
    //    this.resources = s.toString();
    //}
    //
    //public List<Long> getResourcesStr() {
    //    if (StringUtil.isEmpty(this.resources)) {
    //        return null;
    //    }
    //    String[] resourceIdstrs = resources.split(",");
    //    List<Long> list = new ArrayList<Long>();
    //    for (String resourceIdstr : resourceIdstrs) {
    //        if (StringUtil.isEmpty(resourceIdstr)) {
    //            continue;
    //        }
    //        list.add(Long.parseLong(resourceIdstr));
    //    }
    //    return list;
    //}
}
