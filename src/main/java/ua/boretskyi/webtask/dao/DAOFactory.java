package ua.boretskyi.webtask.dao;


public abstract class DAOFactory {
	
	private static DAOFactory instance;
	private static final String DAO_FACTORY_FQN = "ua.boretskyi.webtask.dao.mysql.MysqlDAOFactory";

	public static synchronized DAOFactory getInstance() {
		if (instance == null) {
			try {
				instance = (DAOFactory)Class.forName(DAO_FACTORY_FQN)
					.getDeclaredConstructor()
					.newInstance();
			} catch (ReflectiveOperationException ex) {
				throw new IllegalStateException(
					"Cannot instantiate DAOFactory " + DAO_FACTORY_FQN, ex);
			}
		}
		return instance;
	}

	protected DAOFactory() {
	}
	

	public abstract UserDAO getUserDAO();
	
	public abstract CarDAO getCarDAO();
	
	public abstract RideDAO getRideDAO();
	
	public abstract RideStatsDAO getRideStatsDAO();
}
