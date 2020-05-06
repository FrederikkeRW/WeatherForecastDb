package WeatherForecast.ForcastHandler;

import WeatherForecast.DMI_Simulator.DMI_Simulator;
import WeatherForecast.DMI_Simulator.WeatherTypes;
import WeatherForecast.Database.DbAccess;

import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ForecastHandler {
    /*
    Gets the forecast from DMI, and convertes the data, according to the diffenitions from the tariff tables
     */
    public static ArrayList<ArrayList> fourDaysWeatherForecast(){

        ArrayList<ArrayList> fourDays = new ArrayList<ArrayList>();
        ArrayList<Map<WeatherTypes,Object>> dayForcasts = new ArrayList<Map<WeatherTypes, Object>>();

        ArrayList<ArrayList> dmiData = DMI_Simulator.fourDaysWeatherForecast();
        for(int i = 0; i < dmiData.size(); i ++){
            ArrayList<HashMap<WeatherTypes,Object>> day = dmiData.get(i);
            /*
            We already know whats in our cells in the Array:
                0 = morning
                1 = afternoon
                2 = evening
             */
            HashMap<WeatherTypes,Object> morning = day.get(0);
            HashMap<WeatherTypes,Object> afternoon = day.get(1);
            HashMap<WeatherTypes,Object> evening = day.get(2);


            String temp = (String) morning.get(WeatherTypes.Temp);

            HashMap<String,String> resultTemp = DbAccess.getTempValues(Integer.decode(temp).intValue());

            String tempValue = resultTemp.get("TEMP_VALUE");
            String tempText = resultTemp.get("TEMP_TEXT");
            String tempDescription = resultTemp.get("TEMP_DESCRIPTION");

            System.out.println("The temp today: " + temp +" "+ tempText + " " + tempDescription);

        }

        return fourDays;
    }
}
