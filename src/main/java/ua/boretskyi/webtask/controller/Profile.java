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
import ua.boretskyi.webtask.dao.entity.RideBean;
import ua.boretskyi.webtask.dao.entity.User;
import ua.boretskyi.webtask.logic.DBException;
import ua.boretskyi.webtask.logic.RideManager;

@WebServlet("/profile")
public class Profile extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		act(req, resp);
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("user") == null) {
			resp.sendRedirect("login");
		}else {
			User user = (User) session.getAttribute("user");

			if(isAdmin(user)) {
				req.getRequestDispatcher("admin.jsp").forward(req, resp);;
			} else if(isClient(user)) {
				if(req.getParameter("rides") != null) {
					showUserRides(req, resp, user);
					return;
				}
				
				req.getRequestDispatcher("user-profile.jsp").forward(req, resp);
			}
			
		}

		session.removeAttribute("errMessage");
	}


	
	

	private void act(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if(isClient(user)) {
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
				if(pointA == null || pointA.length() == 0) {
					session.setAttribute("errMessage", "Please, write where would you like to ride from");
					resp.sendRedirect("profile?order=true");
					return;
				} else if(pointB == null || pointB.length() == 0) {
					session.setAttribute("errMessage", "Please, write where would you like to ride to");
					resp.sendRedirect("profile?order=true");
					return;
				} else if(passengers == 0) {
					session.setAttribute("errMessage", "Please, write how many passengers are there in ride");
					resp.sendRedirect("profile?order=true");
					return;
				} else if(carType.equals("Open this select menu")) {
					session.setAttribute("errMessage", "Please, select the type of car");
					resp.sendRedirect("profile?order=true");
					return;
				}
				RideBean rideBean = new RideBean(pointA, pointB, passengers, Car.Type.valueOf(carType), comment);
				session.setAttribute("rideInfo", rideBean);
				resp.sendRedirect("ride");
		}
	}


	private boolean isClient(User user) {
		return user.getRole().equals("client");
	}
	
	private boolean isAdmin(User user) {
		return user.getRole().equals("admin");
	}
	
	private void showUserRides(HttpServletRequest req, HttpServletResponse resp, User user)
			throws ServletException, IOException {
		RideManager rm = new RideManager();
		List<Ride> userRides = null;
		try {
			userRides = rm.findAll().stream().filter(ride -> ride.getUserId() == user.getId()).collect(Collectors.toList());
		} catch (DBException e) {
			e.printStackTrace();
		}
		req.setAttribute("userRides", userRides);
		req.getRequestDispatcher("user-rides.jsp").forward(req, resp);
	}
}
