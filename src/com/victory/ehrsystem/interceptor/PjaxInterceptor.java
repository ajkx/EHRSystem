package com.victory.ehrsystem.interceptor;

import com.victory.ehrsystem.controller.Layout;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * pjax请求拦截器
 *
 * @author ajkx_Du
 * @create 2016-11-26 8:31
 */
public class PjaxInterceptor implements HandlerInterceptor{

    private static final String PJAX = "_pjax";
    private static final String DESC = "desc";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accept = request.getHeader("Accept");
        if (accept.contains("json")){
            return true;}
        String path = request.getServletPath();
        if (path.lastIndexOf(".html") == -1){
            System.out.println("====进入非HTML====");
            System.out.println("path : " + path);
            System.out.println("header : "+request.getHeaderNames());
            System.out.println("====退出非HTML====");
            return true;
        }

        String header = request.getHeader("X-PJAX");
        String desc = request.getHeader(DESC);
        //如果不为pjax请求，模态框请求，不为公用菜单请求，则进行layout的转发
        if (header == null && !path.contains(Layout.LAYOUT_PATH) && desc == null) {
            //因为没有servletpath 所以url直接等于layoutpath
            String url = request.getContextPath()+Layout.LAYOUT_PATH;
            System.out.println("====进入跳转pjax====");
            System.out.println("path : " + path);
            Enumeration a = request.getHeaderNames();
            while (a.hasMoreElements()) {
                System.out.println("header : " + a.nextElement());
            }
            System.out.println("====退出跳转pjax====");
            request.getRequestDispatcher(url).forward(request,response);
            return false;
        }
        System.out.println("====进入正常pjax====");
        System.out.println("path : " + path);
        System.out.println("header : "+request.getHeaderNames());
        System.out.println("====退出正常pjax====");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
