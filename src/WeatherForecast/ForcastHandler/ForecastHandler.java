package WeatherForecast.ForcastHandler;

import WeatherForecast.DMI_Simulator.CloudinessTypes;
import WeatherForecast.DMI_Simulator.DMI_Simulator;
import WeatherForecast.DMI_Simulator.WeatherTypes;
import WeatherForecast.Database.DbAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ForecastHandler {
    /**
     * Gets the forecast from DMI, and convertes the data, according to the diffenitions from the tariff tables
     */
    public static ArrayList<ArrayList> fourDaysWeatherForecast(){

        /**
         * ArrayList of all four days.
         */
        ArrayList<ArrayList> fourDays = new ArrayList<ArrayList>();

        ArrayList<ArrayList> dmiData = DMI_Simulator.fourDaysWeatherForecast();
        for(int i = 0; i < dmiData.size(); i ++){
            ArrayList<HashMap<WeatherTypes,Object>> day = dmiData.get(i);
            /**
             * We already know whats in our cells in the Array:
                0 = morning
                1 = afternoon
                2 = evening
             */
            HashMap<WeatherTypes,Object> morning = day.get(0);
            HashMap<WeatherTypes,Object> afternoon = day.get(1);
            HashMap<WeatherTypes,Object> evening = day.get(2);

            String tempMorning = (String) morning.get(WeatherTypes.Temp);
            String tempAfternoon = (String) afternoon.get(WeatherTypes.Temp);
            String tempEvening = (String) evening.get(WeatherTypes.Temp);

            String windMorning = (String) morning.get(WeatherTypes.Wind);
            String windAfternoon = (String) afternoon.get(WeatherTypes.Wind);
            String windEvening = (String) evening.get(WeatherTypes.Wind);

            String rainMorning = (String) morning.get(WeatherTypes.Rain);
            String rainAfternoon = (String) afternoon.get(WeatherTypes.Rain);
            String rainEvening = (String) evening.get(WeatherTypes.Rain);

            String snowMorning = (String) morning.get(WeatherTypes.Snow);
            String snowAfternoon = (String) afternoon.get(WeatherTypes.Snow);
            String snowEvening = (String) evening.get(WeatherTypes.Snow);

            CloudinessTypes cloudinessMorning = (CloudinessTypes) morning.get(WeatherTypes.Cloudiness);
            CloudinessTypes cloudinessAfternoon = (CloudinessTypes) afternoon.get(WeatherTypes.Cloudiness);
            CloudinessTypes cloudinessEvening = (CloudinessTypes) evening.get(WeatherTypes.Cloudiness);
            //System.out.println("Morning: "+ cloudinessMorning + ", Afternoon: " + cloudinessAfternoon + ", Evening: "+ cloudinessEvening);

            HashMap<String,String> resultTempMorning = DbAccess.getTemperatureValues(Integer.decode(tempMorning).intValue());
            HashMap<String,String> resultTempAfternoon = DbAccess.getTemperatureValues(Integer.decode(tempAfternoon).intValue());
            HashMap<String,String> resultTempEvening = DbAccess.getTemperatureValues(Integer.decode(tempEvening).intValue());

            /**
             * TODO
             * Wind and snow should be float, not int
             */
            HashMap<String,String> resultWindMorning = DbAccess.getWindValues(Integer.decode(windMorning).intValue());
            HashMap<String,String> resultWindAfternoon = DbAccess.getWindValues(Integer.decode(windAfternoon).intValue());
            HashMap<String,String> resultWindEvening = DbAccess.getWindValues(Integer.decode(windEvening).intValue());

            HashMap<String,String> resultRainMorning = DbAccess.getRainValues(Integer.decode(rainMorning).intValue());
            HashMap<String,String> resultRainAfternoon = DbAccess.getRainValues(Integer.decode(rainAfternoon).intValue());
            HashMap<String,String> resultRainEvening = DbAccess.getRainValues(Integer.decode(rainEvening).intValue());

            HashMap<String,String> resultSnowMorning = DbAccess.getSnowValues(Integer.decode(snowMorning).intValue());
            HashMap<String,String> resultSnowAfternoon = DbAccess.getSnowValues(Integer.decode(snowAfternoon).intValue());
            HashMap<String,String> resultSnowEvening = DbAccess.getSnowValues(Integer.decode(snowEvening).intValue());

            /**
             * We got all the values from the database
             * Now we can get game suggestions based on these values
             */
            ArrayList<HashMap<String, Object>> morningGames = DbAccess.getGames(resultWindMorning.get("VALUE"), resultTempMorning.get("VALUE"), resultSnowMorning.get("VALUE"), resultRainMorning.get("VALUE"));
            ArrayList<HashMap<String, Object>> afternoonGames = DbAccess.getGames(resultWindAfternoon.get("VALUE"), resultTempAfternoon.get("VALUE"), resultSnowAfternoon.get("VALUE"), resultRainAfternoon.get("VALUE"));
            ArrayList<HashMap<String, Object>> eveningGames = DbAccess.getGames(resultWindEvening.get("VALUE"), resultTempEvening.get("VALUE"), resultSnowEvening.get("VALUE"), resultRainEvening.get("VALUE"));

            /**
            ArrayList = Morning, afternoon and evening.
            Map = one for each table (TariffTableTypes) and each type, has a Map (3 values - one each day)
             */
            ArrayList<Map<TariffTableTypes,Map<String, String>>> dayForcasts = new  ArrayList<Map<TariffTableTypes,Map<String, String>>>();

            /**
            HashMap, containing the different tables.
             */
            HashMap<TariffTableTypes, Map<String, String>> tableTypesMorning = new HashMap<TariffTableTypes, Map<String, String>>();
            HashMap<TariffTableTypes, Map<String, String>> tableTypesAfternoon = new HashMap<TariffTableTypes, Map<String, String>>();
            HashMap<TariffTableTypes, Map<String, String>> tableTypesEvening = new HashMap<TariffTableTypes, Map<String, String>>();

            /**
            Putting temp, wind, rain, snow, cloudiness morning values inside Hashmap
             */
            HashMap<String, String> values;

            values = new HashMap<String, String>();
            values.put("TEMP", tempMorning);
            //values.put("TEMP_VALUE",resultTempMorning.get("VALUE"));
            values.put("TEMP_TEXT",resultTempMorning.get("TEXT"));
            values.put("TEMP_DESCRIPTION",resultTempMorning.get("DESCRIPTION"));
            tableTypesMorning.put(TariffTableTypes.TEMP_TARIFF,values);

            values = new HashMap<String, String>();
            values.put("WIND", windMorning);
            //values.put("WIND_VALUE",resultWindMorning.get("VALUE"));
            values.put("WIND_TEXT",resultWindMorning.get("TEXT"));
            values.put("WIND_DESCRIPTION",resultWindMorning.get("DESCRIPTION"));
            tableTypesMorning.put(TariffTableTypes.WIND_TARIFF,values);

            values = new HashMap<String, String>();
            values.put("RAIN", rainMorning);
            //values.put("RAIN_VALUE",resultRainMorning.get("VALUE"));
            values.put("RAIN_TEXT",resultRainMorning.get("TEXT"));
            values.put("RAIN_DESCRIPTION",resultRainMorning.get("DESCRIPTION"));
            tableTypesMorning.put(TariffTableTypes.RAIN_TARIFF,values);

            values = new HashMap<String, String>();
            values.put("SNOW", snowMorning);
            //values.put("SNOW_VALUE",resultSnowMorning.get("VALUE"));
            values.put("SNOW_TEXT",resultSnowMorning.get("TEXT"));
            values.put("SNOW_DESCRIPTION",resultSnowMorning.get("DESCRIPTION"));
            tableTypesMorning.put(TariffTableTypes.SNOW_TARIFF,values);

            values = new HashMap<String, String>();
            //values.put("CLOUDINESS_VALUE", cloudinessMorning.value);
            values.put("CLOUDINESS_TEXT", cloudinessMorning.text);
            values.put("CLOUDINESS_DESCRIPTION", cloudinessMorning.description);
            tableTypesMorning.put(TariffTableTypes.CLOUDINESS_TARIFF,values);

            values = gameSuggestionHandler(morningGames);
            tableTypesMorning.put(TariffTableTypes.GAME_TARIFF,values);


            /**
             * Putting temp, wind, rain, snow, cloudiness afternoon values inside Hashmap
             */
            values = new HashMap<String, String>();
            values.put("TEMP", tempAfternoon);
            values.put("TEMP_VALUE",resultTempAfternoon.get("VALUE"));
            values.put("TEMP_TEXT",resultTempAfternoon.get("TEXT"));
            values.put("TEMP_DESCRIPTION",resultTempAfternoon.get("DESCRIPTION"));
            tableTypesAfternoon.put(TariffTableTypes.TEMP_TARIFF,values);


            values = new HashMap<String, String>();
            values.put("WIND", windAfternoon);
            values.put("WIND_VALUE",resultWindAfternoon.get("VALUE"));
            values.put("WIND_TEXT",resultWindAfternoon.get("TEXT"));
            values.put("WIND_DESCRIPTION",resultWindAfternoon.get("DESCRIPTION"));
            tableTypesAfternoon.put(TariffTableTypes.WIND_TARIFF,values);


            values = new HashMap<String, String>();
            values.put("RAIN", rainAfternoon);
            values.put("RAIN_VALUE",resultRainAfternoon.get("VALUE"));
            values.put("RAIN_TEXT",resultRainAfternoon.get("TEXT"));
            values.put("RAIN_DESCRIPTION",resultRainAfternoon.get("DESCRIPTION"));
            tableTypesAfternoon.put(TariffTableTypes.RAIN_TARIFF,values);

            values = new HashMap<String, String>();
            values.put("SNOW", snowAfternoon);
            values.put("SNOW_VALUE",resultSnowAfternoon.get("VALUE"));
            values.put("SNOW_TEXT",resultSnowAfternoon.get("TEXT"));
            values.put("SNOW_DESCRIPTION",resultSnowAfternoon.get("DESCRIPTION"));
            tableTypesAfternoon.put(TariffTableTypes.SNOW_TARIFF,values);

            values = new HashMap<String, String>();
            values.put("CLOUDINESS_VALUE", cloudinessAfternoon.value);
            values.put("CLOUDINESS_TEXT", cloudinessAfternoon.text);
            values.put("CLOUDINESS_DESCRIPTION", cloudinessAfternoon.description);
            tableTypesAfternoon.put(TariffTableTypes.CLOUDINESS_TARIFF,values);

            values = gameSuggestionHandler(afternoonGames);
            tableTypesAfternoon.put(TariffTableTypes.GAME_TARIFF,values);

            /**
             * Putting temp, wind, rain, cloudiness evening values inside Hashmap
             */
            values = new HashMap<String, String>();
            values.put("TEMP", tempEvening);
            values.put("TEMP_VALUE",resultTempEvening.get("VALUE"));
            values.put("TEMP_TEXT",resultTempEvening.get("TEXT"));
            values.put("TEMP_DESCRIPTION",resultTempEvening.get("DESCRIPTION"));
            tableTypesEvening.put(TariffTableTypes.TEMP_TARIFF,values);

            values = new HashMap<String, String>();
            values.put("WIND", windEvening);
            values.put("WIND_VALUE",resultWindEvening.get("VALUE"));
            values.put("WIND_TEXT",resultWindEvening.get("TEXT"));
            values.put("WIND_DESCRIPTION",resultWindEvening.get("DESCRIPTION"));
            tableTypesEvening.put(TariffTableTypes.WIND_TARIFF,values);

            values = new HashMap<String, String>();
            values.put("RAIN", rainEvening);
            values.put("RAIN_VALUE",resultRainEvening.get("VALUE"));
            values.put("RAIN_TEXT",resultRainEvening.get("TEXT"));
            values.put("RAIN_DESCRIPTION",resultRainEvening.get("DESCRIPTION"));
            tableTypesEvening.put(TariffTableTypes.RAIN_TARIFF,values);

            values = new HashMap<String, String>();
            values.put("SNOW", snowEvening);
            values.put("SNOW_VALUE",resultSnowEvening.get("VALUE"));
            values.put("SNOW_TEXT",resultSnowEvening.get("TEXT"));
            values.put("SNOW_DESCRIPTION",resultSnowEvening.get("DESCRIPTION"));
            tableTypesEvening.put(TariffTableTypes.SNOW_TARIFF,values);

            values = new HashMap<String, String>();
            values.put("CLOUDINESS_VALUE", cloudinessEvening.value);
            values.put("CLOUDINESS_TEXT", cloudinessEvening.text);
            values.put("CLOUDINESS_DESCRIPTION", cloudinessEvening.description);
            tableTypesEvening.put(TariffTableTypes.CLOUDINESS_TARIFF,values);

            values = gameSuggestionHandler(eveningGames);
            tableTypesEvening.put(TariffTableTypes.GAME_TARIFF,values);

            /**
             * Adding the morning values from tableTypesMorning, as place 0, 1 and 2 in the array: dayForcasts
             */

            dayForcasts.add(tableTypesMorning);
            dayForcasts.add(tableTypesAfternoon);
            dayForcasts.add(tableTypesEvening);

            /**
             * Adding the dayForcasts array, to the fourDays array.
             */
            fourDays.add(dayForcasts);
        }

        return fourDays;
    }
    private static HashMap<String,String> gameSuggestionHandler (ArrayList<HashMap<String, Object>> gameList){
        HashMap<String, String> gameSuggestion = new HashMap<String, String>();

        /**
         * If there is no game suggestions and no warnings.
         * Return empty map.
         */
        if (gameList.isEmpty()){
            return gameSuggestion;
        }
        /**
         * First loop, for finding warnings. If warning exist, show the first warning.
         */
        for (int i = 0; i < gameList.size(); i++){
            HashMap<String, Object> row = gameList.get(i);
            if ((boolean)row.get("WARNING")){
                gameSuggestion.put("GAME_NAME" , row.get("GAME_NAME").toString());
                gameSuggestion.put("GAME_DESCRIPTION", row.get("GAME_DESCRIPTION").toString());
                return gameSuggestion;
            }
        }
        /**
         * No warning exist. Return random game suggestion.
         */
        Random r = new Random();
        int randomGame = r.nextInt(gameList.size());
        gameSuggestion.put("GAME_NAME", gameList.get(randomGame).get("GAME_NAME").toString());
        gameSuggestion.put("GAME_DESCRIPTION", gameList.get(randomGame).get("GAME_DESCRIPTION").toString());

        return gameSuggestion;
    }
}
