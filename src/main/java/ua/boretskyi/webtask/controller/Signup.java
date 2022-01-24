package ua.boretskyi.webtask.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.boretskyi.webtask.dao.entity.User;
import ua.boretskyi.webtask.logic.DBException;
import ua.boretskyi.webtask.logic.UserManager;

@WebServlet("/signup")
public class Signup extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("sign-up.jsp").forward(req, resp);
		req.getSession().removeAttribute("errMessage");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("firstName");
		String number = req.getParameter("phoneNumber");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String repeatedPassword = req.getParameter("conpassword");
		
		
		
		User user = new User(name, number, email, password);
		UserManager userManager = new UserManager();
		
		if(userManager.userAlreadyExists(email)) {
			req.getSession().setAttribute("errMessage", "User with the email already exists");
			resp.sendRedirect("signup");
		} else {
		
		try {
			userManager.createUser(user);
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			req.getRequestDispatcher("user-profile.jsp").forward(req, resp);
		} catch (DBException e) {

			req.getSession().setAttribute("errMessage", "failed to log in, try to insert proper data");
			resp.sendRedirect("signup");
			e.printStackTrace();
		}
		
	}
	}
}
