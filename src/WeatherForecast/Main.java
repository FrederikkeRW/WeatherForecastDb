package WeatherForecast;

import WeatherForecast.Database.DbTest;

public class Main {
    public static void main (String[] args){

        if (DbTest.testConnection()){
            System.out.println("Database connection: SUCCESS");
        } else {
            System.out.println("Database connection: FAILED");
        }



        /*
        If you want to see the path to your home directory
         */
        //System.out.println("home: " + System.getProperty("user.home"));
    }
}
