package WeatherForecast;

import WeatherForecast.ForcastHandler.ForecastHandler;

import java.util.ArrayList;

public class Main {
    public static void main (String[] args){


        /**
         * Show all four days from DMI
         */

        /*
        ArrayList fourDays = DMI_Simulator.fourDaysWeatherForecast();
        System.out.println("List of four days: " + fourDays);
        */

        ArrayList fourDaysWeatherForecast = ForecastHandler.fourSeasonsWeatherForecast();
        for (int i = 0; i < fourDaysWeatherForecast.size(); i++){
            System.out.println("Day "+ i +": "+ fourDaysWeatherForecast.get(i));
        }


        /**
         * Tells if there is a connection or not, to the database
         */

        /*
        if (DbTest.testConnection()){
            System.out.println("Database connection: SUCCESS");
        } else {
            System.out.println("Database connection: FAILED");
        }

        */


        /**
         *If you want to see the path to your home directory
         */

        /*
        System.out.println("home: " + System.getProperty("user.home"));
        System.out.println("App Config Dirs: " + System.getProperty("application.config.dirs"));
        //$APPLICATION_CONFIG_DIR$

        */
    }
}
