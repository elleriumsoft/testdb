package ru.elleriumsoft;


public class App 
{
    public static void main( String[] args )
    {
        ConnectionToDb.getInstance().connectToDb(new SimpleGetTable());
    }
}
