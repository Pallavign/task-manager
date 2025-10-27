package com.pallavi.taskmanager.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * interceptor for handling http requests and responses.
 * </p>
 * 
 * <p>
 * this class implements the <strong> @HandlerInterceptor </strong> interface to
 * intercept requests before they reach the controller, after the controller has
 * processed the request, and after the response has been sent. It logs
 * important information about the request and response.
 * </p>
 */
@Component
@Slf4j
public class RequestProcessingTimeInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(RequestProcessingTimeInterceptor.class);

	/**
	 * Pre-handle method that is executed before the request reaches the controller.
	 * 
	 * Logs the request method, uri, and servlet path, as well as the controller and
	 * method names if the handler is a <strong> @HandlerMethod </strong>
	 * 
	 * @param request  the <strong> @HttpServletRequest </strong>
	 * @param response the </strong> @HttpServletResponse </strong>
	 * @param object   the handler (either a <strong><em> @HandlerMethod
	 *                 </em></strong> or a simple handler)
	 * @return true to continue the request processing; false to abort
	 * @throws <strong> @Exception </strong> in case of errors
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
		logger.info("1 - pre handle.");
		logger.info("METHOD type : " + request.getMethod());
		logger.info("Request URI : " + request.getRequestURI());
		logger.info("Servlet PATH : " + request.getServletPath());
		if (object instanceof HandlerMethod) {
			Class<?> controllerClass = ((HandlerMethod) object).getBeanType();
			String methodName = ((HandlerMethod) object).getMethod().getName();
			logger.info("Controller name : " + controllerClass.getName());
			logger.info("Method name : " + methodName);
		}
		return true;
	}

	/**
	 * Post-handle method that is executed after the controller processes the
	 * request.
	 * 
	 * Can be used to modify the <strong> @ModelAndView </strong> before rendering
	 * the view.
	 * 
	 * @param request  the <strong> @HttpServletRequest </strong>
	 * @param response the <strong> @HttpServletResponse </strong>
	 * @param object   the handler (either a <strong><em> @HandlerMethod
	 *                 </em></strong> or a simple handler)
	 * @param model    the <strong> @ModelAndView </strong>
	 * @throws <strong> @Exception </strong> in case of errors
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView model) {
		logger.info("2 - Post-handle : Request processed by controller.");
	}

	/**
	 * after completion method that is executed after the complete request has
	 * finished.
	 * 
	 * Logs any exceptions that occurred during request processing.
	 * 
	 * @param request   the <strong> @HttpServletRequest </strong>
	 * @param response  the <strong> @HttpServletResponse </strong>
	 * @param object    the handler (either a <strong><em> @HandlerMethod
	 *                  </em></strong> or a simple handler)
	 * @param exception any exception that occurred during request processing
	 * @throws <strong> @Exception </strong> in case of errors
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) {
		if (exception != null) {
			logger.info("An error occurred during request processing: {} ", exception.getMessage());
		}
		logger.info("3 - After completion: Request processing finished.");
	}
}
