package com.victory.ehrsystem.entity.sys;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限表
 *
 * @author ajkx_Du
 * @create 2016-10-27 10:40
 */
@Entity
@Table(name = "Sys_Resource")
public class SysResource {

    public static enum ResourceType {
        menu("菜单"), button("按钮");
        private final String info;

        private ResourceType(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private ResourceType type = ResourceType.menu;

    @Column
    private Integer priority;

    @Column
    private Integer parent_id;

    @Column
    private String parent_ids;

    @Column
    private String permission;

    @ManyToOne(targetEntity = SysModule.class)
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    private SysModule module;

    @Column
    private Boolean available;

    @Column
    private String url;

    @ManyToMany(targetEntity = SysRole.class,fetch = FetchType.EAGER)
    @JoinTable(name = "role_resource",
            joinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<SysRole> roles = new HashSet<SysRole>();

    public SysResource() {
    }

    public SysResource(String name, String description, ResourceType type, Integer priority, Integer parent_id, String parent_ids, String permission, boolean available,String url) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.priority = priority;
        this.parent_id = parent_id;
        this.parent_ids = parent_ids;
        this.permission = permission;
        this.available = available;
        this.url = url;
    }
    @JsonBackReference
    public Set<SysRole> getRoles() {
        return roles;
    }
    @JsonBackReference
    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
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

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_ids() {
        return parent_ids;
    }

    public void setParent_ids(String parent_ids) {
        this.parent_ids = parent_ids;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

//    public boolean isRootNode() {
//        return parent_id == 0;
//    }

    public SysModule getModule() {
        return module;
    }

    public void setModule(SysModule module) {
        this.module = module;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
