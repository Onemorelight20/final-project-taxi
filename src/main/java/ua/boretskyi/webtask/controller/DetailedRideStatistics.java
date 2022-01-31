package ua.boretskyi.webtask.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.boretskyi.webtask.dao.entity.Ride;
import ua.boretskyi.webtask.logic.DBException;
import ua.boretskyi.webtask.logic.RideManager;

@WebServlet("/stats")
public class DetailedRideStatistics extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RideManager rideManager = new RideManager();
		List<Ride> allRides = new ArrayList<>();
		try {
			allRides = rideManager.findAll();
		} catch (DBException e) {
			e.printStackTrace();
		}

		String sortBy = req.getParameter("sortby");
		String order = req.getParameter("order");
		sortRidesByParams(allRides, sortBy, order);

		req.setAttribute("allRides", allRides);
		req.getRequestDispatcher("detailed-stats.jsp").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	private void sortRidesByParams(List<Ride> allRides, String sortBy, String order) {
		Comparator<Ride> byCreationTime = (o1, o2) -> o1.getTimeCreated().compareTo(o2.getTimeCreated());
		Comparator<Ride> byPrice = (o1, o2) -> (int) (o1.getPrice() - o2.getPrice()) * 100;
		Comparator<Ride> byStatus = (o1, o2) -> (o1.getStatus().compareTo(o2.getStatus()));
		Comparator<Ride> byPassengersAmount = (o1, o2) -> o1.getPeopleInRide() - o2.getPeopleInRide();

		if (sortBy != null) {
			switch (sortBy) {
			case "status":
				if (order == null || order.equals("asc"))
					allRides.sort(byStatus);
				else
					allRides.sort(byStatus.reversed());
				break;
			case "passengers":
				if (order == null || order.equals("asc"))
					allRides.sort(byPassengersAmount);
				else
					allRides.sort(byPassengersAmount.reversed());
				break;
			case "cost":
				if (order == null || order.equals("asc"))
					allRides.sort(byPrice);
				else
					allRides.sort(byPrice.reversed());
				break;
			case "crt":
				if (order == null || order.equals("asc"))
					allRides.sort(byCreationTime);
				else
					allRides.sort(byCreationTime.reversed());
				break;
			}
		}
	}
}
