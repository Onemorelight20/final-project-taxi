package ua.boretskyi.webtask.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.boretskyi.webtask.dao.entity.Ride;
import ua.boretskyi.webtask.dao.entity.User;
import ua.boretskyi.webtask.logic.DBException;
import ua.boretskyi.webtask.logic.RideManager;

@WebServlet("/rides")
public class ShowUserRides extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		showUserRides(req, resp, user);
	}
	
	
	private void showUserRides(HttpServletRequest req, HttpServletResponse resp, User user)
			throws ServletException, IOException {
		RideManager rm = new RideManager();
		List<Ride> userRides = null;
		try {
			userRides = rm.findAll().stream().filter(ride -> ride.getUserId() == user.getId())
					.collect(Collectors.toList());
		} catch (DBException e) {
			e.printStackTrace();
		}
		req.setAttribute("userRides", userRides);
		req.getRequestDispatcher("user-rides.jsp").forward(req, resp);
	}
}
