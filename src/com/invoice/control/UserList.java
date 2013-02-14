package com.invoice.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.invoice.beans.basic.LoginBean;


public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public UserList()
    {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processRequest(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processRequest(request,response);
	}
	private void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		
		String nextpage = "/faces/pages/Users.xhtml";
		String username = request.getParameter("id");
		String action = request.getParameter("action");
		HttpSession session =request.getSession();
		LoginBean user = (session != null) ? (LoginBean)session.getAttribute("loginUser") : null;
		if(user == null || !user.getUser().getRole().isViewUser())  nextpage = "/faces/errors/notauth.xhtml";
		boolean edit =Boolean.parseBoolean(request.getParameter("edit"));
		if(username != null && username != "")
		{
			session.setAttribute("id",username);
			session.setAttribute("edit", edit);
			nextpage = "/faces/pages/user.xhtml";
			if(user == null || !user.getUser().getRole().isViewUser())  nextpage = "/faces/errors/notauth.xhtml";
		}
		else if (action != null && action != "")
		{
			session.setAttribute("action", action);
			nextpage = "/faces/pages/newuser.xhtml";
			if(user == null || !user.getUser().getRole().isAddUser())  nextpage = "/faces/errors/notauth.xhtml";
		}
		request.getRequestDispatcher(nextpage).forward(request, response);
	}

}
