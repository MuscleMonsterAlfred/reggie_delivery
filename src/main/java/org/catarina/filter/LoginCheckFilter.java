package org.catarina.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.catarina.common.BaseContext;
import org.catarina.common.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: reggie_delivery
 * @ClassName LoginCheckFilter
 * @description:
 * @author: alfred-chenzhonghao
 * @create: 2023-01-13 16:29
 * @Version 1.0
 **/
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，能适配通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    /**
     * 配置拦截器，拦截所有的http请求
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        //获取本次请求的url
        String requestUrl = request.getRequestURI();
        log.info("拦截到请求{}",requestUrl);
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**"
        };

        //判断本次请求是否需要处理
        boolean checkUrl = checkUrl(urls,requestUrl);
        //如果不需要处理，则直接放行
        if(checkUrl){
            log.info("本次请求{}不需要处理", requestUrl);
            filterChain.doFilter(request,response);
            return;
        }
        //判断登录状态，如果已经登录，则直接放行
        if(request.getSession().getAttribute("employee")!= null){
            log.info("用户已登录，用户id为:{}",request.getSession().getAttribute("employee"));

            //判断线程是否是同一个
            long id = Thread.currentThread().getId();
            log.info("线程id为：{}",id);

            //将用户id放到线程存储空间中
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request,response);
            return;
        }
        log.info("用户未登录");
        //如果未登录则返回未登录结果，通过输出流方式想客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
        return;
//        log.info("拦截到请求:{}", request.getRequestURI());
//        filterChain.doFilter(request,response);
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     * @param urls
     * @param requestUrl
     * @return
     */
    public boolean checkUrl(String[] urls,String requestUrl){
        for(String url : urls){
            boolean match = PATH_MATCHER.match(url,requestUrl);
            if(match) {
                return true;
            }
        }
        return false;
    }
}
