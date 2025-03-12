package com.kong.Kong.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.core.config.Order;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 요청 URL을 가져옵니다.
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        // 특정 패턴에 맞지 않는 경우에만 리라이트를 적용합니다.
        if (requestURI.startsWith("/api")) {
            // 리라이트 로직을 여기에 추가합니다.

            // /api 패턴에 해당하는 경우, /api를 제외한 나머지 부분을 취하고 리라이트합니다.
            String newPath = requestURI.substring(4); // /api를 제외한 부분

            // newPath를 사용하여 리라이트 로직을 적용
            String newTarget = newPath; // 새 경로 지정

            // 새 경로로 리라이트
            RequestDispatcher dispatcher = request.getRequestDispatcher(newTarget);
            dispatcher.forward(request, response);
            return;

        }

        // 다음 필터 또는 서블릿으로 요청을 전달합니다.
        chain.doFilter(request, response);
    }
}