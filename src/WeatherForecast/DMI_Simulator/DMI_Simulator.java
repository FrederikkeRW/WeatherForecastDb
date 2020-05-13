package WeatherForecast.DMI_Simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DMI_Simulator {
    /**
     * Returning a list of the weather forecast for today and the next 3 days.
     * Each day contains a list of three different forecast, through out the day: Morning, afternoon and evening.
     * Each forecast contains:
     *      Temp:       c
     *      Wind:       m/s
     *      Cloudiness: Enum Text Description
     *      Rain:       mm
     *      Snow:       cm
     */

    public static ArrayList<ArrayList> fourDaysWeatherForecast(){
        ArrayList<ArrayList> fourDays = new ArrayList<ArrayList>();

        //fourDays.add(summerDay());
        fourDays.add(summerDayRandom());
        fourDays.add(summerDayRandom());
        fourDays.add(summerDayRandom());
        fourDays.add(summerDayRandom());

        return fourDays;
    }
    /*
    private static ArrayList<Map<WeatherTypes,Object>> summerDay(){
        ArrayList<Map<WeatherTypes,Object>> day = new ArrayList<Map<WeatherTypes,Object>>();

        HashMap<WeatherTypes,Object> morning = daySection("18", "2", CloudinessTypes.MOSTLY_CLOUDY, "0", "0");
        day.add(morning); // morning is added at index 0

        HashMap<WeatherTypes,Object> afternoon = daySection("25", "4", CloudinessTypes.PARTLY_CLOUDY, "0", "0");
        day.add(afternoon); // afternoon is added at index 1

        HashMap<WeatherTypes,Object> evening = daySection("14", "4", CloudinessTypes.CLOUDY, "0", "0");
        day.add(evening); // evening is added at index 2

        return day;
    }

     */
    private static CloudinessTypes cloudHandler(boolean rain) {

        Random random = new Random();
       // boolean rain = random.nextBoolean();
        /**
         * Default cloudiness type is PARTLY_CLOUDY
         */
        CloudinessTypes cloudinessType = CloudinessTypes.PARTLY_CLOUDY;
        int cloudType = random.nextInt(2);
        if (rain) {
            switch (cloudType) {
                case 0:
                    cloudinessType = CloudinessTypes.CLOUDY;
                    break;
                case 1:
                    cloudinessType = CloudinessTypes.MOSTLY_CLOUDY;
                    break;
                case 2:
                    cloudinessType = CloudinessTypes.PARTLY_CLOUDY;
                    break;
            }
        } else {

            switch (cloudType) {
                case 0:
                    cloudinessType = CloudinessTypes.PARTLY_SUNNY;
                    break;
                case 1:
                    cloudinessType = CloudinessTypes.MOSTLY_SUNNY;
                    break;
                case 2:
                    cloudinessType = CloudinessTypes.SUNNY;
                    break;
            }
        }
        return cloudinessType;
    }

    private static ArrayList<Map<WeatherTypes,Object>> summerDayRandom(){
        ArrayList<Map<WeatherTypes,Object>> day = new ArrayList<Map<WeatherTypes,Object>>();
        Random random = new Random();


        boolean rain = random.nextBoolean();
        Integer rainMM = Integer.valueOf(0);
        if (rain){
            // It can rain from 1-11 mm
            rainMM = random.nextInt(10) + 1;
        }
        CloudinessTypes cloudinessType = cloudHandler(rain);

        /**
         * On a summer day morning, the temp can be between 10-20 degrees
         */
        Integer temp = random.nextInt(10) + 10;

        Integer wind = random.nextInt(10);


        HashMap<WeatherTypes,Object> morning = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(morning); // morning is added at index 0
        /**
         * On a summer day afternoon, the temp can be between 10-40 degrees
         */
        temp = random.nextInt(30) + 10;
        HashMap<WeatherTypes,Object> afternoon = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(afternoon); // afternoon is added at index 1
        /**
         * On a summer day evening, the temp can be between 10-25 degrees
         */
        temp = random.nextInt(15) + 10;
        HashMap<WeatherTypes,Object> evening = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(evening); // evening is added at index 2

        return day;
    }


        private static HashMap<WeatherTypes,Object> daySection(String temp, String wind, CloudinessTypes cloudiness, String rain, String snow){
        HashMap<WeatherTypes,Object> daySection = new HashMap<WeatherTypes,Object>();
        daySection.put(WeatherTypes.Temp,temp);
        daySection.put(WeatherTypes.Wind,wind);
        daySection.put(WeatherTypes.Cloudiness,cloudiness);
        daySection.put(WeatherTypes.Rain,rain);
        daySection.put(WeatherTypes.Snow,snow);
        return daySection;
    }


}
