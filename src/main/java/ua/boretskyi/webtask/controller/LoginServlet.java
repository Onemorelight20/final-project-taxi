package ua.boretskyi.webtask.controller;


import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import ua.boretskyi.webtask.dao.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.getRequestDispatcher("user-profile.jsp").forward(req, resp);
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		
		User user = null;;
		String destPage = "login.jsp";
		if(user != null) {
			System.out.println(user);
			HttpSession session = req.getSession();
				session.setAttribute("user", user);
				resp.sendRedirect("profile");
		} else {
			System.out.println("wrong email or password");
			String message = "Invalid email/password";
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getRequestDispatcher(destPage);
			dispatcher.forward(req, resp);
		}
	}
}
