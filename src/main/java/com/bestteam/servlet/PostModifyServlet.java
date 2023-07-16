package com.bestteam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bestteam.dto.PostDetailDto;
import com.bestteam.dto.PostModifyDto;
import com.bestteam.service.Service;

@WebServlet("/modify")
public class PostModifyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Service service = new Service();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));

		PostDetailDto postDetailDto = service.findById(num);
		
		request.setAttribute("num", num);
		request.setAttribute("subject", postDetailDto.getSubject());
		request.setAttribute("writer", postDetailDto.getWriter());
		request.setAttribute("contents", postDetailDto.getContents());
		request.getRequestDispatcher("PostModify.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");

		service.modify(new PostModifyDto(num, subject, writer, contents));

		response.sendRedirect("detail?num=" + num);
	}
}
