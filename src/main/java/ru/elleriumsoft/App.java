package ru.elleriumsoft;


import ru.elleriumsoft.database.ConnectionToDb;
import ru.elleriumsoft.database.requests.SimpleGetTable;

public class App
{
    public static void main( String[] args )
    {
        ConnectionToDb.getInstance().connectToDb(new SimpleGetTable());
    }
}
