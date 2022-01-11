package ua.boretskyi.webtask.controller;


import java.io.IOException;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.boretskyi.webtask.dao.LoginDAO;
import ua.boretskyi.webtask.dao.UserDAO;
import ua.boretskyi.webtask.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		LoginDAO u = new LoginDAO();
		UserDAO userdao = new UserDAO();
		
		User user = u.checkLogin(email, password);
		String destPage = "login.jsp";
		if(user != null) {
			System.out.println(user);
			String role = userdao.getUserRole(user);
			HttpSession session = req.getSession();
			if(role.equals("admin")) {
				session.setAttribute("admin", user);
				req.setAttribute("admin", user);
				req.getRequestDispatcher("admin.jsp").forward(req, resp);
			} else if(role.equals("client")) {
				session.setMaxInactiveInterval(10*60);
	            session.setAttribute("user", user);
	            req.setAttribute("user", user);
	 
	            req.getRequestDispatcher("user-profile.jsp").forward(req, resp);
			}

		} else {
			String message = "Invalid email/password";
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getRequestDispatcher(destPage);
			dispatcher.forward(req, resp);
		}
	}
}
