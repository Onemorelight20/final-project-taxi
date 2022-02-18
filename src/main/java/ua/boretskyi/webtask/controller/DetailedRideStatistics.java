package ua.boretskyi.webtask.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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
	private static final int shift = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RideManager rideManager = new RideManager();
		List<Ride> allRides = new ArrayList<>();
		String paramPage = req.getParameter("page");
		String paramPageSize = req.getParameter("pageSize");
		String sortBy = req.getParameter("sortby");
		String order = req.getParameter("order");
		Integer page = null;
		Integer pageSize = null;
		try {
			if (paramPage == null || paramPageSize == null) {
				paramPage = req.getAttribute("page").toString();
				paramPageSize = req.getAttribute("pageSize").toString();
				log.info("paramPage || paramPageSize were not passed in URL params, getting them from request");
			}
			page = Integer.parseInt(paramPage);
			pageSize = Integer.parseInt(paramPageSize);
		} catch (Exception e) {

			log.info("Exception was thrown while parsing page params");
			e.printStackTrace();
			req.setAttribute("message", "Illegal params were passed in URL");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
			return;
		}

		try {
			allRides = rideManager.findAll();
		} catch (DBException e) {
			log.warn("Exception happend while trying to get all rides from RM");
			e.printStackTrace();
		}

		
		sortRidesByParams(allRides, sortBy, order);

		if (req.getParameter("rdf") != null) {
			StringBuffer requestURL = getFullURL(req);
			String newUrl = removeDateFilteringParamsFromUrl(requestURL);
			resp.sendRedirect(newUrl);
			return;
		}

		try {
			processDateFiltering(req, resp, allRides);
		} catch (ParseException e) {
			log.warn("Exception happend while trying to filter rides by date");
			e.printStackTrace();
		}
		
		int size = allRides.size();
		int pageCount = (int) Math.ceil((double)size / pageSize);

		if(page > pageCount) {
			page = pageCount;
		}
		int fromIndex = pageSize * (page - 1) > 0 ? pageSize * (page - 1) : 0;
		int toIndex = pageSize * (page - 1) + pageSize;
		List<Ride> ridesToShow = allRides.subList(fromIndex,
				(toIndex > allRides.size() ? allRides.size() : toIndex));

		int minPagePossible = page - shift < 1 ? 1 : page - shift;
		int maxPagePossible = page + shift > pageCount ? pageCount : page + shift;

		System.out.println("RidesToShow: " + ridesToShow);
		System.out.println("List size: " + ridesToShow.size());
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("page", page);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("generalSize", size);
		req.setAttribute("minPossiblePage", minPagePossible);
		req.setAttribute("maxPossiblePage", maxPagePossible);
		req.setAttribute("servletUrl", "stats");
		req.setAttribute("list", ridesToShow);
		req.getRequestDispatcher("detailed-stats.jsp").forward(req, resp);
	}

	private String removeDateFilteringParamsFromUrl(StringBuffer requestURL) {
		UrlParseHelper urlParseHelper = new UrlParseHelper(requestURL.toString());
		String completeURL = requestURL.toString();
		System.out.println("URL: " + completeURL);
		String newUrl = urlParseHelper.removeParamFromUrl("rdf").removeParamFromUrl("from").removeParamFromUrl("to")
				.getUrl();
		return newUrl;
	}

	private StringBuffer getFullURL(HttpServletRequest req) {
		StringBuffer requestURL = req.getRequestURL();
		if (req.getQueryString() != null) {
			requestURL.append("?").append(req.getQueryString());
		}
		return requestURL;
	}

	private void processDateFiltering(HttpServletRequest req, HttpServletResponse resp, List<Ride> rides)
			throws ParseException {
		String dateFrom = req.getParameter("from");
		String dateTo = req.getParameter("to");
		boolean doActions = (req.getParameter("datereset") == null) && (dateFrom != null && dateTo != null);

		if (doActions) {
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
