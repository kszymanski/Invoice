package com.invoice.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.invoice.beans.basic.LoginBean;



public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ProductList() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	private void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		
		String nextpage = "/faces/pages/lists/stocklist.xhtml";
		String productId = request.getParameter("id");
		HttpSession session =request.getSession();
		LoginBean user = (session != null) ? (LoginBean)session.getAttribute("loginUser") : null;
		if(user == null || !user.getUser().getRole().isViewProduct())  nextpage = "/faces/errors/notauth.xhtml";
		if(productId != null && productId != "")
		{
			session.setAttribute("id",productId);
		}
		request.getRequestDispatcher(nextpage).forward(request, response);
	}
}
