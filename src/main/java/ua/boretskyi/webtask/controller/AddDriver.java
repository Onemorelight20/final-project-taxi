package ua.boretskyi.webtask.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.boretskyi.webtask.dao.entity.User;
import ua.boretskyi.webtask.dao.entity.User.Role;
import ua.boretskyi.webtask.logic.DBException;
import ua.boretskyi.webtask.logic.UserManager;


@WebServlet("/add-driver")
public class AddDriver extends HttpServlet {
	private static final String ERROR_ATTRIBUTE = "errMessage";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("add-driver.jsp").forward(req, resp);
		req.getSession().removeAttribute(ERROR_ATTRIBUTE);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String name = req.getParameter("name");
		String phoneNumber = req.getParameter("phoneNumber");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String passwordRepeated = req.getParameter("conpassword");
		
		if(!password.equals(passwordRepeated)) {
			session.setAttribute(ERROR_ATTRIBUTE, "Please, type in the same password twice");
			resp.sendRedirect("add-driver");
			return;
		}
		
		UserManager userManager = new UserManager();
		User user = new User(name, phoneNumber, email, password);
		user.setRole(Role.DRIVER);
		
		if(userManager.userAlreadyExists(email)) {
			session.setAttribute(ERROR_ATTRIBUTE, "User with such email already exists");
			resp.sendRedirect("add-driver");
			return;
		}
		
		try {
			userManager.createUser(user);
			session.setAttribute("successMessage", "The driver was successfully added into the system");
			resp.sendRedirect("profile");
		} catch (DBException e) {

			session.setAttribute("message", "Failed to add the driver into system.");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
			e.printStackTrace();
		}
	}

}
