package com.invoice.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		response.sendRedirect("/error.xhtml");
	}

}
