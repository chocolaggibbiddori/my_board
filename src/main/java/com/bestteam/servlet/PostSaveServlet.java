package com.bestteam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bestteam.dto.PostSaveDto;
import com.bestteam.service.Service;

@WebServlet("/save")
public class PostSaveServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Service service = new Service();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	
		
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		String ip = request.getRemoteAddr();

		int num = service.save(new PostSaveDto(subject, writer, contents, ip));

		response.sendRedirect("detail?num=" + num);
	}
}
