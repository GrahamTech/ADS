package com.grahamtech.ads.utilities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BaseControllerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory
	    .getLogger(BaseControllerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
	    HttpServletResponse response, Object handler) throws Exception {
	logger.debug("*** IN preHandle METHOD ***");
	long startTime = System.currentTimeMillis();
	logger.info("Request URL::" + request.getRequestURL().toString()
		+ ":: Start Time=" + startTime);
	request.setAttribute("startTime", startTime);
	// if returned false, ensure that response is sent
	return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
	    HttpServletResponse response, Object handler,
	    ModelAndView modelAndView) {

	long currentTime = System.currentTimeMillis();

	logger.debug("*** IN postHandle METHOD ***");
	logger.info("Request URL::" + request.getRequestURL().toString()
		+ ":: Sent To Handler=" + currentTime);

	// Disable Browser Cache
	response.setHeader("Cache-Control",
		"no-cache, no-store, must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setDateHeader("Expires", 0); // Proxies

	// We can add attributes to the modelAndView and use that in the view
	// page
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
	    HttpServletResponse response, Object handler, Exception ex)
	    throws Exception {
	long endTime = System.currentTimeMillis();
	long startTime = (Long) request.getAttribute("startTime");

	logger.debug("*** IN afterCompletion METHOD ***");
	logger.info("Request URL::" + request.getRequestURL().toString()
		+ ":: End Time=" + endTime);
	logger.info("Request URL::" + request.getRequestURL().toString()
		+ ":: Time Taken=" + (endTime - startTime));
    }
}