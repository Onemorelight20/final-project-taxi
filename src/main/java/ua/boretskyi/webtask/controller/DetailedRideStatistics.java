package ua.boretskyi.webtask.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.boretskyi.webtask.dao.entity.Ride;
import ua.boretskyi.webtask.logic.DBException;
import ua.boretskyi.webtask.logic.RideManager;
import ua.boretskyi.webtask.logic.UrlParseHelper;

@WebServlet("/stats")
public class DetailedRideStatistics extends HttpServlet {
	private static final Logger log = Logger.getLogger(DetailedRideStatistics.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RideManager rideManager = new RideManager();
		List<Ride> allRides = new ArrayList<>();

		try {
			allRides = rideManager.findAll();
		} catch (DBException e) {
			log.warn("Exception happend while trying to get all rides from RM");
			e.printStackTrace();
		}

		String sortBy = req.getParameter("sortby");
		String order = req.getParameter("order");
		sortRidesByParams(allRides, sortBy, order);
		
		
		if(req.getParameter("rdf") != null) {
			StringBuffer requestURL = req.getRequestURL();
			if (req.getQueryString() != null) {
			    requestURL.append("?").append(req.getQueryString());
			}
			UrlParseHelper urlParseHelper = new UrlParseHelper(requestURL.toString());
			String completeURL = requestURL.toString();
			System.out.println("URL: " + completeURL);
			String newUrl = urlParseHelper.removeParamFromUrl("rdf")
					.removeParamFromUrl("from")
					.removeParamFromUrl("to")
					.getUrl();
			resp.sendRedirect(newUrl);
			return;
		}
		
		try {
			processDateFiltering(req, resp, allRides);
		} catch (ParseException e) {
			log.warn("Exception happend while trying to filter rides by date");
			e.printStackTrace();
		}

		req.setAttribute("allRides", allRides);
		req.getRequestDispatcher("detailed-stats.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
	private void processDateFiltering(HttpServletRequest req, HttpServletResponse resp, List<Ride> rides) throws ParseException {
		String dateFrom = req.getParameter("from");
		String dateTo = req.getParameter("to");
		boolean doActions = (req.getParameter("datereset") == null) && (dateFrom != null && dateTo != null);
		
		if(doActions) {
			LocalDate fromSelected = LocalDate.parse(dateFrom);
			LocalDate toSelected = LocalDate.parse(dateTo).plusDays(1L);
			log.info("Process date filtering from " + fromSelected + " to " + toSelected);

			rides.removeIf(ride -> !(ride.getTimeCreated().after(Timestamp.valueOf(fromSelected.atStartOfDay())) 
					&& ride.getTimeCreated().before(Timestamp.valueOf(toSelected.atStartOfDay()))));

		}
	}
	
	private void sortRidesByParams(List<Ride> allRides, String sortBy, String order) {
		Comparator<Ride> byCreationTime = (o1, o2) -> o1.getTimeCreated().compareTo(o2.getTimeCreated());
		Comparator<Ride> byPrice = (o1, o2) -> (int) (o1.getPrice() - o2.getPrice()) * 100;
		Comparator<Ride> byStatus = (o1, o2) -> (o1.getStatus().compareTo(o2.getStatus()));
		Comparator<Ride> byPassengersAmount = (o1, o2) -> o1.getPeopleInRide() - o2.getPeopleInRide();

		if (sortBy != null) {
			switch (sortBy) {
			case "status":
				wrappedSorting(allRides, order, byStatus);
				break;
			case "passengers":
				wrappedSorting(allRides, order, byPassengersAmount);
				break;
			case "cost":
				wrappedSorting(allRides, order, byPrice);
				break;
			case "crt":
				wrappedSorting(allRides, order, byCreationTime);
				break;
			}
		}
	}


	private void wrappedSorting(List<Ride> allRides, String order, Comparator<Ride> comparator) {
		if (order == null || order.equals("asc"))
			allRides.sort(comparator);
		else
			allRides.sort(comparator.reversed());
	}
}
