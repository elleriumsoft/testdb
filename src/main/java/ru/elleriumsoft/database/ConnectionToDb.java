package ru.elleriumsoft.database;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 29.01.2017.
 */
public class ConnectionToDb
{
    private static final Logger logger = Logger.getLogger(ConnectionToDb.class.getName());

    private static ConnectionToDb instance = new ConnectionToDb();

    private final HikariDataSource ds;

    private ConnectionToDb()
    {
        ds = new HikariDataSource();
        ds.setMaximumPoolSize(20);
        ds.setDriverClassName("org.mariadb.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mariadb://test-pma.elleriumsoft.ru:3306/test");
        ds.addDataSourceProperty("user","test");
        ds.addDataSourceProperty("password","niger5555a");
        ds.setAutoCommit(true);
//        try
//        {
//            ds.setLogWriter(new PrintWriter(new File("c://log//log.txt")));
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        } catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
    }

    public static ConnectionToDb getInstance()
    {
        return instance;
    }

    public void connectToDb(DatabaseRequest databaseRequest)
    {
        Connection connection = null;

        try
        {
            connection = getConnection();
            databaseRequest.sendRequest(connection);
        }
        catch (SQLException e)
        {
            logger.info(e.getMessage());
        }
        finally
        {
            closeConnection(connection);
        }
    }

    private Connection getConnection()
    {
        try
        {
            //return DriverManager.getConnection("jdbc:mariadb://localhost:3306/test?user=1&password=1");
            return ds.getConnection();
        }
        catch (SQLException e)
        {
            logger.info(e.getMessage());
            throw new RuntimeException();
        }
    }

    private void closeConnection(Connection connection)
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            logger.info(e.getMessage());
        }
    }
}
