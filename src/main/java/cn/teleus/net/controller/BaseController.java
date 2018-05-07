package cn.teleus.net.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController implements HandlerInterceptor {
	public BaseController(){
	}

	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )
		throws Exception {
		return true;
	}



	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView ) throws Exception {

	}


	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex )
		throws Exception {
	}

}
