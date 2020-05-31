package WeatherForecast.WeatherSimulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeatherSimulator {
    /**
     * Returning a list of the weather forecast for 4 days: a summer, autumn, winter and spring day.
     * Each day contains a list of three different forecast, through out the day: Morning, afternoon and evening.
     * Each forecast contains:
     *      Temp:       c
     *      Wind:       m/s
     *      Cloudiness: Enum Text Description
     *      Rain:       mm
     *      Snow:       cm
     */

    public static ArrayList<ArrayList> fourSeasonsWeatherForecast(){
        ArrayList<ArrayList> fourSeasons = new ArrayList<ArrayList>();

       fourSeasons.add(summerDayRandom());
       fourSeasons.add(autumnDayRandom());
       fourSeasons.add(winterDayRandom());
       fourSeasons.add(springDayRandom());

        return fourSeasons;
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
    private static CloudinessTypes cloudHandler(boolean rainOrSnow) {

        Random random = new Random();

        /**
         * Default cloudiness type is PARTLY_CLOUDY
         */
        CloudinessTypes cloudinessType = CloudinessTypes.PARTLY_CLOUDY;
        int cloudType = random.nextInt(2);
        if (rainOrSnow) {
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
        /**
         * 50/50 if it rains or not - either true or false
         */
        boolean rain = random.nextBoolean();

        Integer rainMM = Integer.valueOf(0);
        if (rain){
            /**
             * On a summer day morning, it can rain from up to 8 mm
             */
            rainMM = random.nextInt(8);
        }
        CloudinessTypes cloudinessType = cloudHandler(rain);

        /**
         * On a summer day morning, the temp can be between 10-20 degrees
         */
        Integer temp = random.nextInt(10) + 10;

        /**
         * On a summer morning, the wind can blow up till 5 ms
         */
        Integer wind = random.nextInt(5);

/**
 * SUMMER DAY MORNING
 */
        HashMap<WeatherTypes,Object> morning = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(morning); // morning is added at index 0

        /**
         * On a summer day afternoon, the temp can be between 14-38 degrees
         */
        temp = random.nextInt(24) + 14;

        /**
         * On a summer afternoon, the wind can blow up till 3 ms
         */
        wind = random.nextInt(3);

        /**
         * On a summer day afternoon, it can rain up to 11 mm
         */
        if (rain){
            rainMM = random.nextInt(11);
        }
/**
 * SUMMER DAY AFTERNOON
 */
        HashMap<WeatherTypes,Object> afternoon = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(afternoon); // afternoon is added at index 1

        /**
         * On a summer day evening, the temp can be between 10-25 degrees
         */
        temp = random.nextInt(15) + 10;

        /**
         * On a summer evening, the wind can blow up till 5 ms
         */
        wind = random.nextInt(5);

        /**
         * On a summer day evening, it can rain up to 11 mm
         */
        if (rain){
            rainMM = random.nextInt(10);
        }
/**
 * SUMMER DAY EVENING
 */
        HashMap<WeatherTypes,Object> evening = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(evening); // evening is added at index 2

        return day;
    }


    private static ArrayList<Map<WeatherTypes, Object>> autumnDayRandom(){
        ArrayList<Map<WeatherTypes, Object>> day = new ArrayList<Map<WeatherTypes, Object>>();
        Random random = new Random();

        /**
         * 50/50 if it rains or not - either true or false
         */
        Boolean rain = random.nextBoolean();

        Integer rainMM = Integer.valueOf(0);
        if (rain){
            /**
             * On a autumn day morning, it can rain up to 8 mm
             */
            rainMM = random.nextInt(8);
        }
        CloudinessTypes cloudinessType = cloudHandler(rain);

        /**
         * On a autumn day morning, the temp can be between 5-15 degrees
         */
        Integer temp = random.nextInt(10) + 5;

        /**
         * On a autumn morning, the wind can blow up till 5 ms
         */
        Integer wind = random.nextInt(5);
/**
 * AUTUMN DAY MORNING
 */
        HashMap<WeatherTypes,Object> morning = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(morning); // morning is added at index 0

        /**
         * On a autumn day afternoon, the temp can be between 8-15 degrees
         */
        temp = random.nextInt(7) + 8;

        /**
         * On a autumn day afternoon, the wind can blow up till 8 ms
         */
        wind = random.nextInt(8);

        /**
         * On a autumn day afternoon, it can rain up to 6 mm
         */
        if (rain){
            rainMM = random.nextInt(6);
        }
/**
 * AUTUMN DAY AFTERNOON
 */
        HashMap<WeatherTypes,Object> afternoon = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(afternoon); // afternoon is added at index 1

        /**
         * On a autumn day evening, the temp can be between 5-10 degrees
         */
        temp = random.nextInt(5) + 5;

        /**
         * On a autumn day evening, the wind can blow up till 21 ms
         */
        wind = random.nextInt(21);

        /**
         * On a autumn day evening, it can rain up to 16 mm
         */
        if (rain){
            rainMM = random.nextInt(16);
        }
/**
 * AUTUMN DAY EVENING
 */
        HashMap<WeatherTypes,Object> evening = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(evening); // evening is added at index 2


        return day;
    }

    private static ArrayList<Map<WeatherTypes, Object>> winterDayRandom(){
        ArrayList<Map<WeatherTypes, Object>> day = new ArrayList<Map<WeatherTypes, Object>>();
        Random random = new Random();
        /**
         * 50/50 if it snows or not - either true or false
         */
        Boolean snow = random.nextBoolean();

        Integer snowCM = Integer.valueOf(0);
        if (snow){
            /**
             * On a winter day morning, it can snow up to 4 cm
             */
            snowCM = random.nextInt(4);
        }
        CloudinessTypes cloudinessType = cloudHandler(snow);

        /**
         * On a winter day morning, the temp can be between -10 - 5 degrees
         */
        Integer temp = random.nextInt(15) - 10;


        /**
         * On a winter day morning, the wind can blow up till 8 ms
         */
        Integer wind = random.nextInt(8);
/**
 * WINTER DAY MORNING
 */
        HashMap<WeatherTypes,Object> morning = daySection(temp.toString(), wind.toString(), cloudinessType, "0", snowCM.toString());
        day.add(morning); // morning is added at index 0

        /**
         * On a winter day afternoon, the temp can be between -5 - 5 degrees
         */
         temp = random.nextInt(10) - 5;


        /**
         * On a winter day afternoon, the wind can blow up till 10 ms
         */
        wind = random.nextInt(10);

        /**
         * On a winter day afternoon, it can snow up to 9 cm
         */
        if (snow){
            snowCM = random.nextInt(9);
        }
/**
 * WINTER DAY AFTERNOON
 */
        HashMap<WeatherTypes,Object> afternoon = daySection(temp.toString(), wind.toString(), cloudinessType, "0", snowCM.toString());
        day.add(afternoon); // afternoon is added at index 1

        /**
         * On a winter day evening, the temp can be between -10 - 4 degrees
         */
        temp = random.nextInt(14) - 10;

        /**
         * On a winter day afternoon, the wind can blow up till 12 ms
         */
        wind = random.nextInt(12);

        /**
         * On a winter day afternoon, it can snow up to 3 cm
         */
        if (snow){
            snowCM = random.nextInt(3);
        }
/**
 * WINTER DAY EVENING
 */
        HashMap<WeatherTypes,Object> evening = daySection(temp.toString(), wind.toString(), cloudinessType, "0", snowCM.toString());
        day.add(evening); // evening is added at index 2


        return day;
    }




    private static ArrayList<Map<WeatherTypes,Object>> springDayRandom(){
        ArrayList<Map<WeatherTypes,Object>> day = new ArrayList<Map<WeatherTypes,Object>>();
        Random random = new Random();
        /**
         * 50/50 if it rains or not - either true or false
         */
        boolean rain = random.nextBoolean();

        Integer rainMM = Integer.valueOf(0);
        if (rain){
            /**
             * On a spring day morning. it can rain up to 9 mm
             */
            rainMM = random.nextInt(9);
        }
        CloudinessTypes cloudinessType = cloudHandler(rain);

        /**
         * On a spring day morning, the temp can be between 6 - 16 degrees
         */
        Integer temp = random.nextInt(10) + 6;

        /**
         * On a spring day morning, the wind can blow up till 5 ms
         */
        Integer wind = random.nextInt(5);

/**
 * SPRING DAY MORNING
 */
        HashMap<WeatherTypes,Object> morning = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(morning); // morning is added at index 0

        /**
         * On a spring day afternoon, the temp can be between 6 - 18 degrees
         */
        temp = random.nextInt(12) + 6;

        /**
         * On a spring day afternoon, the wind can blow up till 10 ms
         */
        wind = random.nextInt(10);

        /**
         * On a spring day afternoon. it can rain up to 11 mm
         */
        if (rain){
            rainMM = random.nextInt(11);
        }
/**
 * SPRING DAT AFTERNOON
 */
        HashMap<WeatherTypes,Object> afternoon = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(afternoon); // afternoon is added at index 1

        /**
         * On a spring day evening, the temp can be between 6 - 16 degrees
         */
        temp = random.nextInt(10) + 6;

        /**
         * On a spring day afternoon, the wind can blow up till 7 ms
         */
        wind = random.nextInt(7);

        /**
         * On a spring day afternoon. it can rain up to 5 mm
         */
        if (rain){
            rainMM = random.nextInt(5);
        }
/**
 * SPRING DAY EVENING
 */
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
