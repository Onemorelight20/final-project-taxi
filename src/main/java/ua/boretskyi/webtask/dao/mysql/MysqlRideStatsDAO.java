package ua.boretskyi.webtask.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.boretskyi.webtask.dao.RideStatsDAO;
import ua.boretskyi.webtask.dao.entity.RideStats;
import ua.boretskyi.webtask.dao.util.ConnectionPool;
import ua.boretskyi.webtask.logic.DBException;

public class MysqlRideStatsDAO implements RideStatsDAO {

	private static final String ALL_TIME_STATS = "SELECT COUNT(*) AS rides, \r\n"
			+ "	ROUND(AVG(people_in_ride), 1) AS avg_people,\r\n" + "    ROUND(AVG(price), 1) AS avg_cost,\r\n"
			+ "    SUM(price) AS summary_cost FROM ride;";

	private static final String TIME_FILTER_STATS_TEMPLATE = "SELECT COUNT(*) AS rides, \r\n"
			+ "	COALESCE((ROUND(AVG(people_in_ride), 1)), 0) AS avg_people,\r\n"
			+ "    COALESCE(ROUND(AVG(price), 1), 0) AS avg_cost,\r\n"
			+ "    COALESCE(SUM(price), 0) AS summary_cost from ride\r\n";
	private static final String LASTH_MONTH_FILTER = "where time_created > now() - interval 1 month";
	private static final String LAST_WEEK_FILTER = "where time_created > now() - interval 1 week";
	private static final String LAST_24H_FILTER = "where time_created > now() - interval 1 day";
	private static final String PS_DRIVER_ID_FILTER = " AND driver_id = ?";
	private static final String PS_ONLY_DRIVER_ID_FILTER = " WHERE driver_id = ?";
	private static ConnectionPool pool = ConnectionPool.getInstance();

	@Override
	public RideStats getAllTimeStats() throws DBException {
		Connection conn = pool.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		RideStats rideStats = new RideStats();
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(ALL_TIME_STATS);
			rs.next();
			fillInRideStatsFromResultSet(rs, rideStats);
			return rideStats;
		} catch (SQLException e) {
			e.printStackTrace();

			throw new DBException("failed to get all time stats", e);
		} finally {
			close(rs);
			close(statement);
			close(conn);
		}
	}

	@Override
	public RideStats getLastMonthStats() throws DBException {
		return getTimeFilteredStats(LASTH_MONTH_FILTER);
	}

	@Override
	public RideStats getLastWeekStats() throws DBException {
		return getTimeFilteredStats(LAST_WEEK_FILTER);
	}

	@Override
	public RideStats get24HoursStats() throws DBException {
		return getTimeFilteredStats(LAST_24H_FILTER);
	}

	@Override
	public RideStats get24HoursStats(int driverId) throws DBException {
		return getTimeAndDriverFilteredStats(LAST_24H_FILTER, driverId);
	}

	@Override
	public RideStats getAllTimeStats(int driverId) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		RideStats rideStats = new RideStats();
		try {
			ps = conn.prepareStatement(TIME_FILTER_STATS_TEMPLATE + PS_ONLY_DRIVER_ID_FILTER);
			ps.setInt(1, driverId);
			rs = ps.executeQuery();
			rs.next();
			fillInRideStatsFromResultSet(rs, rideStats);
			return rideStats;
		} catch (SQLException e) {
			e.printStackTrace();

			throw new DBException("failed to get stats for driver with id " + driverId, e);
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}
	}

	@Override
	public RideStats getLastMonthStats(int driverId) throws DBException {
		return getTimeAndDriverFilteredStats(LASTH_MONTH_FILTER, driverId);
	}

	@Override
	public RideStats getLastWeekStats(int driverId) throws DBException {
		return getTimeAndDriverFilteredStats(LAST_WEEK_FILTER, driverId);
	}

	private RideStats getTimeFilteredStats(String filter) throws DBException {
		Connection conn = pool.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		RideStats rideStats = new RideStats();
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(TIME_FILTER_STATS_TEMPLATE + filter);
			rs.next();
			fillInRideStatsFromResultSet(rs, rideStats);
			return rideStats;
		} catch (SQLException e) {
			e.printStackTrace();

			throw new DBException("failed to get " + filter + " stats", e);
		} finally {
			close(rs);
			close(statement);
			close(conn);
		}
	}

	private RideStats getTimeAndDriverFilteredStats(String filter, int driverId) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		RideStats rideStats = new RideStats();
		try {
			ps = conn.prepareStatement(TIME_FILTER_STATS_TEMPLATE + filter + PS_DRIVER_ID_FILTER);
			ps.setInt(1, driverId);
			rs = ps.executeQuery();
			rs.next();
			fillInRideStatsFromResultSet(rs, rideStats);
			return rideStats;
		} catch (SQLException e) {
			e.printStackTrace();

			throw new DBException("failed to get " + filter + " stats", e);
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}
	}

	private void fillInRideStatsFromResultSet(ResultSet rs, RideStats rideStats) throws SQLException {
		rideStats.setRidesAmount(rs.getInt("rides"));
		rideStats.setAvgPeople(rs.getDouble("avg_people"));
		rideStats.setAvgPrice(rs.getDouble("avg_cost"));
		rideStats.setSummaryCost(rs.getDouble("summary_cost"));
	}

	private void close(Statement statement) {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void close(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
