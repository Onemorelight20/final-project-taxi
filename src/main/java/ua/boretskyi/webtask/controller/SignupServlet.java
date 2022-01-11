package ua.boretskyi.webtask.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.boretskyi.webtask.dao.RegistrationDAO;
import ua.boretskyi.webtask.entity.User;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("sign-up.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("firstName");
		String number = req.getParameter("phoneNumber");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String repeatedPassword = req.getParameter("conpassword");
		
		User user = new User(name, number, email, password);
		RegistrationDAO registrationDAO = new RegistrationDAO();
		boolean succesfullyRegistered = registrationDAO.registerUser(user);

		if(succesfullyRegistered) {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			req.getRequestDispatcher("user-profile.jsp").forward(req, resp);
		} else {
			//req.getRequestDispatcher("sign-up.jsp").forward(req, resp);
			resp.sendError(0, repeatedPassword);
		}
	}
}
