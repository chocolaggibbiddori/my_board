package com.bestteam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bestteam.dto.PostDetailDto;
import com.bestteam.service.Service;

@WebServlet("/detail")
public class PostDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Service service = new Service();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));

		service.addHit(num);
		PostDetailDto detailDto = service.findById(num);
		
		request.setAttribute("num", num);
		request.setAttribute("subject", detailDto.getSubject());
		request.setAttribute("writer", detailDto.getWriter());
		request.setAttribute("contents", detailDto.getContents());
		request.setAttribute("hit", detailDto.getHit());
		request.setAttribute("reg_date", detailDto.getReg_date());
		request.getRequestDispatcher("PostDetail.jsp").forward(request, response);
	}
}
