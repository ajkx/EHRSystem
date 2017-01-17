package com.victory.ehrsystem.entity.sys;

import com.victory.ehrsystem.entity.hrm.HrmResource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 管理员表
 *
 * @author ajkx_Du
 * @create 2016-10-24 10:09
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username",unique = true)
    private String name;

    @Column
    private String password;

    @Column
    private String salt;

    @OneToOne(targetEntity = HrmResource.class)
    private HrmResource hrmResource;


    @ManyToMany(targetEntity = SysRole.class,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<SysRole> roleids = new HashSet<SysRole>();

    //默认为false 0
    @Column
    private boolean locked;

    public User() {
    }

    public User(String name, String password, String salt, boolean locked) {
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.locked = locked;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<SysRole> getRoleids() {
        return roleids;
    }

    public void setRoleids(Set<SysRole> roleids) {
        this.roleids = roleids;
    }

    public boolean getLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HrmResource getHrmResource() {
        return hrmResource;
    }

    public void setHrmResource(HrmResource hrmResource) {
        this.hrmResource = hrmResource;
    }

    public boolean isLocked() {
        return locked;
    }

    ////设置user的roles的字符串
    //public List<Long> getRoleIdsStr() {
    //    if (StringUtil.isEmpty(roleids)) {
    //        return null;
    //    }
    //    String[] roleIdStrs = roleids.split(",");
    //    List<Long> list = new ArrayList<Long>();
    //    for (String roleId : roleIdStrs) {
    //        if (StringUtil.isEmpty(roleId)) {
    //            continue;
    //        }
    //        list.add(Long.parseLong(roleId));
    //    }
    //    return list;
    //}
    //
    //public void setRoleIdsStr(List<Long> list) {
    //    if (CollectionUtils.isEmpty(list)) {
    //        this.roleids = "";
    //    }
    //
    //    StringBuilder s = new StringBuilder();
    //    for (Long l : list) {
    //        s.append(l);
    //        s.append(",");
    //    }
    //    this.roleids = s.toString();
    //}

    public String getCredentialsSalt() {
        return name + salt;
    }
}
