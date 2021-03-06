package WeatherForecast.ForcastHandler;

import WeatherForecast.WeatherSimulator.CloudinessTypes;
import WeatherForecast.WeatherSimulator.WeatherSimulator;
import WeatherForecast.WeatherSimulator.WeatherTypes;
import WeatherForecast.Database.DbAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ForecastHandler {
    /**
     * Gets the forecast from DMI, and convertes the data, according to the diffenitions from the tariff tables
     */
    public static ArrayList<ArrayList> fourSeasonsWeatherForecast(){

        /**
         * ArrayList of all four seasons.
         */
        ArrayList<ArrayList> fourSeasons = new ArrayList<ArrayList>();

        ArrayList<ArrayList> dmiData = WeatherSimulator.fourSeasonsWeatherForecast();
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
            ArrayList<HashMap<String, String>> morningGames = DbAccess.getGameValues(resultWindMorning.get("VALUE"), resultTempMorning.get("VALUE"), resultSnowMorning.get("VALUE"), resultRainMorning.get("VALUE"));
            ArrayList<HashMap<String, String>> afternoonGames = DbAccess.getGameValues(resultWindAfternoon.get("VALUE"), resultTempAfternoon.get("VALUE"), resultSnowAfternoon.get("VALUE"), resultRainAfternoon.get("VALUE"));
            ArrayList<HashMap<String, String>> eveningGames = DbAccess.getGameValues(resultWindEvening.get("VALUE"), resultTempEvening.get("VALUE"), resultSnowEvening.get("VALUE"), resultRainEvening.get("VALUE"));


            ArrayList<HashMap<String, String>> morningCloth = DbAccess.getClothValues(resultWindMorning.get("VALUE"), resultTempMorning.get("VALUE"), resultSnowMorning.get("VALUE"), resultRainMorning.get("VALUE"));
            ArrayList<HashMap<String, String>> afternoonCloth = DbAccess.getClothValues(resultWindAfternoon.get("VALUE"), resultTempAfternoon.get("VALUE"), resultSnowAfternoon.get("VALUE"), resultRainAfternoon.get("VALUE"));
            ArrayList<HashMap<String, String>> eveningCloth = DbAccess.getClothValues(resultWindEvening.get("VALUE"), resultTempEvening.get("VALUE"), resultSnowEvening.get("VALUE"), resultRainEvening.get("VALUE"));


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


            boolean warning = false;
            //System.out.println("morning values: " + values);
            if ("TRUE".equals(values.get("WARNING"))){
                //System.out.println("morning warning is true: ");
                warning = true;
            }
            values = clothSuggestionHandler(morningCloth, warning);
            tableTypesMorning.put(TariffTableTypes.CLOTH_TARIFF,values);


            /**
             * Putting temp, wind, rain, snow, cloudiness afternoon values inside Hashmap
             */
            values = new HashMap<String, String>();
            values.put("TEMP", tempAfternoon);
            //values.put("TEMP_VALUE",resultTempAfternoon.get("VALUE"));
            values.put("TEMP_TEXT",resultTempAfternoon.get("TEXT"));
            values.put("TEMP_DESCRIPTION",resultTempAfternoon.get("DESCRIPTION"));
            tableTypesAfternoon.put(TariffTableTypes.TEMP_TARIFF,values);


            values = new HashMap<String, String>();
            values.put("WIND", windAfternoon);
            //values.put("WIND_VALUE",resultWindAfternoon.get("VALUE"));
            values.put("WIND_TEXT",resultWindAfternoon.get("TEXT"));
            values.put("WIND_DESCRIPTION",resultWindAfternoon.get("DESCRIPTION"));
            tableTypesAfternoon.put(TariffTableTypes.WIND_TARIFF,values);


            values = new HashMap<String, String>();
            values.put("RAIN", rainAfternoon);
            //values.put("RAIN_VALUE",resultRainAfternoon.get("VALUE"));
            values.put("RAIN_TEXT",resultRainAfternoon.get("TEXT"));
            values.put("RAIN_DESCRIPTION",resultRainAfternoon.get("DESCRIPTION"));
            tableTypesAfternoon.put(TariffTableTypes.RAIN_TARIFF,values);

            values = new HashMap<String, String>();
            values.put("SNOW", snowAfternoon);
            //values.put("SNOW_VALUE",resultSnowAfternoon.get("VALUE"));
            values.put("SNOW_TEXT",resultSnowAfternoon.get("TEXT"));
            values.put("SNOW_DESCRIPTION",resultSnowAfternoon.get("DESCRIPTION"));
            tableTypesAfternoon.put(TariffTableTypes.SNOW_TARIFF,values);

            values = new HashMap<String, String>();
            //values.put("CLOUDINESS_VALUE", cloudinessAfternoon.value);
            values.put("CLOUDINESS_TEXT", cloudinessAfternoon.text);
            values.put("CLOUDINESS_DESCRIPTION", cloudinessAfternoon.description);
            tableTypesAfternoon.put(TariffTableTypes.CLOUDINESS_TARIFF,values);

            values = gameSuggestionHandler(afternoonGames);
            tableTypesAfternoon.put(TariffTableTypes.GAME_TARIFF,values);

            warning = false;
            if ("TRUE".equals(values.get("WARNING"))){
                warning = true;
            }
            values = clothSuggestionHandler(afternoonCloth, warning);
            tableTypesAfternoon.put(TariffTableTypes.CLOTH_TARIFF,values);


            /**
             * Putting temp, wind, rain, cloudiness evening values inside Hashmap
             */
            values = new HashMap<String, String>();
            values.put("TEMP", tempEvening);
            //values.put("TEMP_VALUE",resultTempEvening.get("VALUE"));
            values.put("TEMP_TEXT",resultTempEvening.get("TEXT"));
            values.put("TEMP_DESCRIPTION",resultTempEvening.get("DESCRIPTION"));
            tableTypesEvening.put(TariffTableTypes.TEMP_TARIFF,values);

            values = new HashMap<String, String>();
            values.put("WIND", windEvening);
            //values.put("WIND_VALUE",resultWindEvening.get("VALUE"));
            values.put("WIND_TEXT",resultWindEvening.get("TEXT"));
            values.put("WIND_DESCRIPTION",resultWindEvening.get("DESCRIPTION"));
            tableTypesEvening.put(TariffTableTypes.WIND_TARIFF,values);

            values = new HashMap<String, String>();
            values.put("RAIN", rainEvening);
            //values.put("RAIN_VALUE",resultRainEvening.get("VALUE"));
            values.put("RAIN_TEXT",resultRainEvening.get("TEXT"));
            values.put("RAIN_DESCRIPTION",resultRainEvening.get("DESCRIPTION"));
            tableTypesEvening.put(TariffTableTypes.RAIN_TARIFF,values);

            values = new HashMap<String, String>();
            values.put("SNOW", snowEvening);
            //values.put("SNOW_VALUE",resultSnowEvening.get("VALUE"));
            values.put("SNOW_TEXT",resultSnowEvening.get("TEXT"));
            values.put("SNOW_DESCRIPTION",resultSnowEvening.get("DESCRIPTION"));
            tableTypesEvening.put(TariffTableTypes.SNOW_TARIFF,values);

            values = new HashMap<String, String>();
            //values.put("CLOUDINESS_VALUE", cloudinessEvening.value);
            values.put("CLOUDINESS_TEXT", cloudinessEvening.text);
            values.put("CLOUDINESS_DESCRIPTION", cloudinessEvening.description);
            tableTypesEvening.put(TariffTableTypes.CLOUDINESS_TARIFF,values);

            values = gameSuggestionHandler(eveningGames);
            tableTypesEvening.put(TariffTableTypes.GAME_TARIFF,values);

            warning = false;
            if ("TRUE".equals(values.get("WARNING"))){
                warning = true;
            }
            values = clothSuggestionHandler(eveningCloth, warning);
            tableTypesEvening.put(TariffTableTypes.CLOTH_TARIFF, values);

            /**
             * Adding the morning values from tableTypesMorning, as place 0, 1 and 2 in the array: dayForcasts
             */

            dayForcasts.add(tableTypesMorning);
            dayForcasts.add(tableTypesAfternoon);
            dayForcasts.add(tableTypesEvening);

            /**
             * Adding the dayForcasts array, to the fourSeasons array.
             */
            fourSeasons.add(dayForcasts);
        }

        return fourSeasons;
    }

    /**
     * If we have a WARNING, we should print one of these 3 cloth suggestions
     */
    private static String getRandomClothForWarnings() {
        //System.out.println("getRandomClothForWarnings INIT");
        String randomCloth = "Wear whatever you want!";
        Random r = new Random();
        int randomClothForWarning = r.nextInt(3);
        switch (randomClothForWarning){
            case 0:
                randomCloth = "Wear whatever you want!";
                break;

            case 1:
                randomCloth = "You can wear your PJ's all day if you want!";
                break;

            case 2:
                randomCloth = "Put on your slippers and rope. The weather is not for outside playing today! ";
                break;
        }
        //System.out.println("getRandomClothForWarnings returns: "+randomCloth);
        return randomCloth;
    }


    private static HashMap<String,String> gameSuggestionHandler (ArrayList<HashMap<String, String>> gameList){
        HashMap<String, String> gameSuggestion = new HashMap<String, String>();

        /**
         * If there is no game suggestions and no warnings.
         * Return empty map.
         */
        if (gameList.isEmpty()){
            System.out.println("ERROR: gameList is empty");
            return gameSuggestion;
        }
        /**
         * First loop, for finding warnings. If warning exist, show the first warning.
         */
        for (int i = 0; i < gameList.size(); i++){
            HashMap<String, String> row = gameList.get(i);
            //System.out.println("row: "+ row);

            if ("TRUE".equals(row.get("WARNING"))) {
                //System.out.println("warning in row: ");
                gameSuggestion.put("GAME_NAME" , row.get("NAME").toString());
                gameSuggestion.put("GAME_DESCRIPTION", row.get("DESCRIPTION").toString());
                gameSuggestion.put("WARNING", row.get("WARNING").toString());


                return gameSuggestion;
            }
        }
        /**
         * No warning exist. Return random game suggestion.
         */
        Random r = new Random();
        int randomGame = r.nextInt(gameList.size());
        gameSuggestion.put("GAME_NAME", gameList.get(randomGame).get("NAME").toString());
        gameSuggestion.put("GAME_DESCRIPTION", gameList.get(randomGame).get("DESCRIPTION").toString());

        return gameSuggestion;
    }

    private static HashMap<String, String> clothSuggestionHandler (ArrayList<HashMap<String, String>> clothList, boolean warning){
        //System.out.println("clothSuggesionHandler INIT: warning: "+ warning);
        HashMap<String, String> clothSuggestion = new HashMap<String, String>();

        /**
         * If there is no cloth,
         * Return empty map.
         */

        if (warning) {
            clothSuggestion.put("CLOTH_NAME", "Stay inside!");
            clothSuggestion.put("CLOTH_DESCRIPTION", getRandomClothForWarnings());

        } else if (clothList.isEmpty()){
                System.out.println("ERROR: clostList is empty");
                return clothSuggestion;

        } else {

            /**
             * Return random cloth suggestion.
             */
            Random r = new Random();
            int randomCloth = r.nextInt(clothList.size());
            clothSuggestion.put("CLOTH_NAME", clothList.get(randomCloth).get("NAME").toString());
            clothSuggestion.put("CLOTH_DESCRIPTION", clothList.get(randomCloth).get("DESCRIPTION").toString());

        }
        //system.out.println("clothSuggesionHandler returns: " + clothSuggestion);
        return clothSuggestion;
    }

}
