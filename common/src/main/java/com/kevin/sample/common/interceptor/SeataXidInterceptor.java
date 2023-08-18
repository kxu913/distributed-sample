package com.kevin.sample.common.interceptor;

import com.google.common.base.Strings;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class SeataXidInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equalsIgnoreCase("GET")){
            return true;
        }
        String xid = request.getHeader("xid");
        if(Strings.isNullOrEmpty(xid)){
            log.error("xid is null.");
            return false;
        }
        RootContext.bind(xid);
        log.info("{} binded in {}", xid, Thread.currentThread().getName());
        return true;
    }
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        String xid = RootContext.unbind();
        log.info("{} unbind in {}", xid, Thread.currentThread().getName());
    }
}
