package tr.com.obss.jss.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import tr.com.obss.jss.controller.UserController;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestInFilter extends CommonsRequestLoggingFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInFilter.class);

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        String pathInfo = request.getRequestURI();
        return pathInfo.contains("/api/");
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        LOGGER.info("Bir kullanıcı girdi: {}  {}",request.getRequestURI(),request.getMethod());
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        LOGGER.info("Bir kullanıcı çıktı: {}  {} ", request.getRequestURI(),request.getMethod());
    }
}
