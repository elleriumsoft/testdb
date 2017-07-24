package ru.elleriumsoft.database.requests;

import org.apache.log4j.Logger;
import ru.elleriumsoft.database.DatabaseRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mayorov_an on 24.07.2017.
 */
public class SimpleGetTable implements DatabaseRequest
{
    private static final Logger logger = Logger.getLogger(SimpleGetTable.class.getName());

    @Override
    public void sendRequest(Connection connection) throws SQLException
    {
        ResultSet rs = connection.prepareStatement("select * from players").executeQuery();
        while (rs.next())
        {
            logger.info(rs.getInt(1) + "," + rs.getString(2));
        }
    }
}
