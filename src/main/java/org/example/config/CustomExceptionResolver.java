package org.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Custom exception resolver to handle exceptions and return a simple text response.
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionResolver.class);

    /**
     * Resolves exceptions by returning a simple error message as text.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @param handler  the handler
     * @param ex       the exception thrown
     * @return a ModelAndView with a simple error message
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error("Exception occurred during request processing: " + ex.getMessage(), ex);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ModelAndView("errorView").addObject("message", "An error occurred: " + ex.getMessage());
    }
}
