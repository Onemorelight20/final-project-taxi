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
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("sign-up.jsp").forward(req, resp);
		req.getSession().removeAttribute("errMessage");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String name = req.getParameter("firstName");
		String number = req.getParameter("phoneNumber");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String repeatedPassword = req.getParameter("conpassword");

		User user = new User(name, number, email, password);
		UserManager userManager = new UserManager();

		if (userManager.userAlreadyExists(email)) {
			session.setAttribute("errMessage", "User with the email already exists");
			resp.sendRedirect("signup");
		} else {

			if (!password.equals(repeatedPassword)) {
				session.setAttribute("errMessage", "Please, enter the same password twice");
				resp.sendRedirect("signup");
				return;
			}
			try {
				userManager.createUser(user);
				session.setAttribute("user", user);
				req.getRequestDispatcher("user-profile.jsp").forward(req, resp);
			} catch (DBException e) {

				session.setAttribute("errMessage", "failed to log in, try to insert proper data");
				resp.sendRedirect("signup");
				e.printStackTrace();
			}

		}
	}
}
