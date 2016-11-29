package com.victory.ehrsystem.filter.shiro;

import com.victory.ehrsystem.service.sys.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ajkx_Du
 * @create 2016-11-04 13:48
 */
public class SysUserFilter extends PathMatchingFilter{

    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, Object mappedValue) throws Exception {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("USER",userService.findByUsername(username));
        return true;
    }
}
