package ru.elleriumsoft;

import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 29.01.2017.
 */
public class ConnectionToDb
{
    private static ConnectionToDb instance = new ConnectionToDb();

    private final HikariDataSource ds;

    private ConnectionToDb()
    {
        ds = new HikariDataSource();
        ds.setMaximumPoolSize(20);
        ds.setDriverClassName("org.mariadb.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mariadb://localhost:3306/test");
        ds.addDataSourceProperty("user","1");
        ds.addDataSourceProperty("password","1");
        ds.setAutoCommit(true);
        try
        {
            ds.setLogWriter(new PrintWriter(new File("c://log//log.txt")));
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
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
            e.printStackTrace();
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
            throw new RuntimeException(e);
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
            e.printStackTrace();
        }
    }
}
