package org.example.config;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomExceptionResolverTest {

    private CustomExceptionResolver exceptionResolver;

    @BeforeEach
    public void setUp() {
        exceptionResolver = new CustomExceptionResolver();
    }

    @Test
    public void testResolveException() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Exception exception = new Exception("Test exception");

        ModelAndView modelAndView = exceptionResolver.resolveException(request, response, null, exception);

        String responseContent = response.getContentAsString();
        assertEquals("An error occurred: Test exception", responseContent, "Response content does not match");

        assertEquals(500, response.getStatus(), "HTTP status code should be 500");

        assertEquals(null, modelAndView, "ModelAndView should be null");
    }
}
