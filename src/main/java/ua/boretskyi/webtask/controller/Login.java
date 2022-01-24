package ua.boretskyi.webtask.controller;


import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import ua.boretskyi.webtask.dao.entity.User;
import ua.boretskyi.webtask.logic.DBException;
import ua.boretskyi.webtask.logic.UserManager;



@WebServlet("/login")
public class Login extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		Logger logger = Logger.getLogger(Login.class);
		BasicConfigurator.configure();
		logger.info("This is my first log4j's statement");

		UserManager userManager = new UserManager();
		User user = null;
		try {
			user = userManager.findUser(email, password);
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		
		if(user != null) {
			HttpSession session = req.getSession();
				session.setAttribute("user", user);
				resp.sendRedirect("profile");
		} else {
			System.out.println("wrong email or password");
			String message = "Invalid email/password";
			req.setAttribute("message", message);
			RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
