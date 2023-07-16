package com.bestteam.servlet;

import com.bestteam.dto.PostListDto;
import com.bestteam.service.SearchCondition;
import com.bestteam.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Service service = new Service();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		System.out.println("Call BoardServlet");

		List<PostListDto> postList = service.extractTen(pageNum);
		int pageTotal = service.getBoardSize();

		request.setAttribute("postList", postList);
		request.setAttribute("pageTotal", pageTotal);

		request.getRequestDispatcher("BoardList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		SearchCondition searchCondition = SearchCondition.valueOf(request.getParameter("searchCondition"));
		String searchContent = request.getParameter("searchContent");
		System.out.println(searchContent);
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));

		List<PostListDto> postList = service.extractTen(pageNum, searchCondition, searchContent);
		int pageTotal = service.getBoardSize(searchCondition, searchContent);

		request.setAttribute("postList", postList);
		request.setAttribute("pageTotal", pageTotal);

		request.getRequestDispatcher("BoardList.jsp").forward(request, response);
	}
}
