package com.invoice.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.invoice.dbacces.UserDAO;


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
		boolean edit =Boolean.parseBoolean(request.getParameter("edit"));
		if(username != null && username != "")
		{
			try {
				HttpSession session =request.getSession();
				session.setAttribute("user", UserDAO.getUser(username));
				session.setAttribute("edit", edit);
				nextpage = "/faces/pages/user.xhtml";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(nextpage).forward(request, response);
	}

}
