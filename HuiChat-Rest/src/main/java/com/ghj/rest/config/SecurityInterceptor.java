package com.ghj.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/9/10 14:43
 */
@Configuration
public class SecurityInterceptor implements HandlerInterceptor {

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        HttpSession session = request.getSession();
//        if (session.getAttribute(session.getId()) != null){
//            return true;
//        }
//        return false;
//    }
}
