package comp3350.ims.application;


import comp3350.ims.persistence.DataAccess;
import comp3350.ims.persistence.DataAccessDatabase;


public class Services
{
	private static DataAccess dataAccessService = null;

	public static DataAccess createDataAccess(String dbName)
	{
		if (dataAccessService == null)
		{
			dataAccessService = new DataAccessDatabase(dbName);
			dataAccessService.open(Main.getDBPathName());
		}
		return dataAccessService;
	}

	public static void setAutoCommitOff(){
		if(dataAccessService instanceof DataAccessDatabase){
			((DataAccessDatabase) dataAccessService).setAutoCommitOff();
		}
	}

	public static DataAccess createDataAccess(DataAccess alternateDataAccessService)
	{
		if (dataAccessService == null)
		{
			dataAccessService = alternateDataAccessService;
			dataAccessService.open(Main.dbName);
		}
		return dataAccessService;
	}

	public static DataAccess getDataAccess(String dbName)
	{
		if (dataAccessService == null)
		{
			System.out.println("Connection to data access has not been established.");
			System.exit(1);
		}
		return dataAccessService;
	}

	public static void closeDataAccess()
	{
		if (dataAccessService != null)
		{
			dataAccessService.close();
		}
		dataAccessService = null;
	}
}
