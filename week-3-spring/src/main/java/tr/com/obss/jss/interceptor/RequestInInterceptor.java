package tr.com.obss.jss.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tr.com.obss.jss.filter.RequestInFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String pathInfo = request.getRequestURI();
        if (pathInfo.contains("/api/")) {
            LOGGER.info("Request Interceptor başladı: {} {} ", request.getRequestURI(), request.getMethod());
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("Request Interceptor gitti: {} {}",request.getRequestURI(),request.getMethod());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
