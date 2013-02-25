package com.invoice.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.invoice.beans.basic.LoginBean;

public class AuthFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
		FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		HttpSession session=request.getSession(true);
		String uri = request.getRequestURI();
		String loginUri = request.getContextPath() + "/login.xhtml";
		int tologin = uri.compareToIgnoreCase(loginUri);
		LoginBean user = (session != null) ? (LoginBean)session.getAttribute("loginUser") : null;
		
		if(((!uri.contains("/javax.faces.resource") && tologin != 0)) && (session == null || user == null || !user.isValid()))
		{
			String from=request.getRequestURI();
			if(request.getQueryString()!=null)from += '?' + request.getQueryString();
			response.sendRedirect(request.getContextPath()+"/login.xhtml?from="+from);
		}
		else 
		{
			chain.doFilter(req, res);
		}
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
