package com.victory.ehrsystem.domain.sys;

import com.victory.ehrsystem.util.StringUtil;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "username")
    private String name;

    @Column
    private String password;

    @Column
    private String salt;

    @Column(name = "role_ids")
    private String roleids;

    @Column
    private boolean locked;

    public User() {
    }

    public User(String name, String password, String salt, String roleids, boolean locked) {
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.roleids = roleids;
        this.locked = locked;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRoleids() {
        return roleids;
    }

    public void setRoleids(String roleids) {
        this.roleids = roleids;
    }

    public boolean isLocked() {
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

    //设置user的roles的字符串
    public List<Long> getRoleIdsStr() {
        if (StringUtil.isEmpty(roleids)) {
            return null;
        }
        String[] roleIdStrs = roleids.split(",");
        List<Long> list = new ArrayList<Long>();
        for (String roleId : roleIdStrs) {
            if (StringUtil.isEmpty(roleId)) {
                continue;
            }
            list.add(Long.parseLong(roleId));
        }
        return list;
    }

    public void setRoleIdsStr(List<Long> list) {
        if (CollectionUtils.isEmpty(list)) {
            this.roleids = "";
        }

        StringBuilder s = new StringBuilder();
        for (Long l : list) {
            s.append(l);
            s.append(",");
        }
        this.roleids = s.toString();
    }

    public String getCredentialsSalt() {
        return name + salt;
    }
}
