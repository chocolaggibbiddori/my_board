package com.bestteam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bestteam.service.Service;

@WebServlet("/delete")
public class PostDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private final Service service = new Service();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		
		service.delete(num);
		
		response.sendRedirect("board?pageNum=1");
	}
}
