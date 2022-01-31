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
import ua.boretskyi.webtask.dao.entity.Car;
import ua.boretskyi.webtask.dao.entity.Ride;
import ua.boretskyi.webtask.dao.entity.RideStats;
import ua.boretskyi.webtask.dao.entity.User;
import ua.boretskyi.webtask.dao.entity.User.Role;
import ua.boretskyi.webtask.logic.DBException;
import ua.boretskyi.webtask.logic.RideBuilder;
import ua.boretskyi.webtask.logic.RideManager;
import ua.boretskyi.webtask.logic.RideStatsManager;

@WebServlet("/profile")
public class Profile extends HttpServlet {

	public static final String SUCCESS_MESSAGE_ATTRIBUTE = "successMessage";
	private static final String ERROR_MESSAGE_ATTRIBUTE = "errMessage";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		fillInRideInfo(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			resp.sendRedirect("login");
		} else {
			if (isAdmin(user)) {
				provideRequestWithStatsAttributes(req);
				req.getRequestDispatcher("admin.jsp").forward(req, resp);
			} else if (isClient(user)) {
				if (req.getParameter("rides") != null) {
					showUserRides(req, resp, user);
					return;
				}
				req.getRequestDispatcher("user-profile.jsp").forward(req, resp);
			}
		}

		session.setAttribute("typesOfCar", Car.Type.values());
		session.removeAttribute(ERROR_MESSAGE_ATTRIBUTE);
		session.removeAttribute(SUCCESS_MESSAGE_ATTRIBUTE);
	}

	private void provideRequestWithStatsAttributes(HttpServletRequest req) {
		RideStatsManager rideStatsManager = new RideStatsManager();
		RideStats allTimeStats = null;
		RideStats lastMonthStats = null;
		RideStats lastWeekStats = null;
		RideStats last24HoursStats = null;
		try {
			allTimeStats = rideStatsManager.getAllTimeStats();
			lastMonthStats = rideStatsManager.getLastMonthStats();
			lastWeekStats = rideStatsManager.getLastWeekStats();
			last24HoursStats = rideStatsManager.get24HoursStats();
		} catch (DBException e) {
			e.printStackTrace();
		}
		req.setAttribute("allTimeStats", allTimeStats);
		req.setAttribute("lastMonthStats", lastMonthStats);
		req.setAttribute("lastWeekStats", lastWeekStats);
		req.setAttribute("last24HoursStats", last24HoursStats);
	}

	private void fillInRideInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if (isClient(user)) {
			String pointA = req.getParameter("pointA");
			String pointB = req.getParameter("pointB");
			int passengers = 0;
			try {
				passengers = Integer.parseInt(req.getParameter("passangersAmount"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			String carType = req.getParameter("carType");
			String comment = req.getParameter("comment");
			if (pointA == null || pointA.length() == 0) {
				session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Please, write where would you like to ride from");
				resp.sendRedirect("profile?order=true");
				return;
			} else if (pointB == null || pointB.length() == 0) {
				session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Please, write where would you like to ride to");
				resp.sendRedirect("profile?order=true");
				return;
			} else if (passengers == 0) {
				session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Please, write how many passengers are there in ride");
				resp.sendRedirect("profile?order=true");
				return;
			} else if (carType.equals("Open this select menu")) {
				session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "Please, select the type of car");
				resp.sendRedirect("profile?order=true");
				return;
			}
			RideBuilder rideBuilder = new RideBuilder(pointA, pointB, passengers, Car.Type.valueOf(carType), comment);
			session.setAttribute("rideInfo", rideBuilder);
			resp.sendRedirect("ride");
		}
	}

	private boolean isClient(User user) {
		return user.getRole() == User.Role.CLIENT;
	}

	private boolean isAdmin(User user) {
		return user.getRole() == User.Role.ADMIN;
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
