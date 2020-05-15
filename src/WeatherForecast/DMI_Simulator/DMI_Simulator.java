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
        //fourDays.add(summerDayRandom());
        //fourDays.add(summerDayRandom());
        //fourDays.add(summerDayRandom());
        fourDays.add(summerDayRandom());
        fourDays.add(autumnDayRandom());
        fourDays.add(winterDayRandom());
        fourDays.add(springDayRandom());

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
            // It can rain from 1-11 mm
            rainMM = random.nextInt(10) + 1;
        }
        CloudinessTypes cloudinessType = cloudHandler(rain);

        /**
         * On a summer day morning, the temp can be between 10-20 degrees
         */
        Integer temp = random.nextInt(10) + 10;

        /**
         * The wind can blow up till 10 ms, all summer
         */
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

    private static ArrayList<Map<WeatherTypes, Object>> autumnDayRandom(){
        ArrayList<Map<WeatherTypes, Object>> day = new ArrayList<Map<WeatherTypes, Object>>();
        Random random = new Random();

        /**
         * 50/50 if it rains or not - either true or false
         */
        Boolean rain = random.nextBoolean();

        Integer rainMM = Integer.valueOf(0);
        if (rain){
            // It can rain from 1-11 mm
            rainMM = random.nextInt(10) + 1;
        }
        CloudinessTypes cloudinessType = cloudHandler(rain);

        /**
         * On a autumn day morning, the temp can be between 5-15 degrees
         */
        Integer temp = random.nextInt(10) + 5;

        /**
         * The wind can blow up till 10 ms, all autumn
         */
        Integer wind = random.nextInt(10);

        HashMap<WeatherTypes,Object> morning = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(morning); // morning is added at index 0

        /**
         * On a autumn day afternoon, the temp can be between 8-15 degrees
         */
        temp = random.nextInt(7) + 8;
        HashMap<WeatherTypes,Object> afternoon = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(afternoon); // afternoon is added at index 1

        /**
         * On a autumn day evening, the temp can be between 5-10 degrees
         */
        temp = random.nextInt(5) + 5;
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
            // It can snow from 1-20 cm
            snowCM = random.nextInt(19) + 1;
        }
        CloudinessTypes cloudinessType = cloudHandler(snow);

        /**
         * On a winter day morning, the temp can be between -10 - 5 degrees
         */
        Integer temp = random.nextInt(15) - 10;

        /**
         * The wind can blow up till 10 ms, all winter
         */
        Integer wind = random.nextInt(10);

        HashMap<WeatherTypes,Object> morning = daySection(temp.toString(), wind.toString(), cloudinessType, "0", snowCM.toString());
        day.add(morning); // morning is added at index 0

        /**
         * On a winter day afternoon, the temp can be between -5 - 5 degrees
         */
        temp = random.nextInt(10) - 5;
        HashMap<WeatherTypes,Object> afternoon = daySection(temp.toString(), wind.toString(), cloudinessType, "0", snowCM.toString());
        day.add(afternoon); // afternoon is added at index 1

        /**
         * On a winter day evening, the temp can be between -10 - 4 degrees
         */
        temp = random.nextInt(14) - 10;
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
            // It can rain from 1-11 mm
            rainMM = random.nextInt(10) + 1;
        }
        CloudinessTypes cloudinessType = cloudHandler(rain);

        /**
         * On a spring day morning, the temp can be between 6 - 16 degrees
         */
        Integer temp = random.nextInt(10) + 6;

        /**
         * The wind can blow up till 10 ms, all summer
         */
        Integer wind = random.nextInt(10);


        HashMap<WeatherTypes,Object> morning = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(morning); // morning is added at index 0

        /**
         * On a spring day afternoon, the temp can be between 6 - 18 degrees
         */
        temp = random.nextInt(12) + 6;
        HashMap<WeatherTypes,Object> afternoon = daySection(temp.toString(), wind.toString(), cloudinessType, rainMM.toString(), "0");
        day.add(afternoon); // afternoon is added at index 1

        /**
         * On a spring day evening, the temp can be between 6 - 16 degrees
         */
        temp = random.nextInt(10) + 6;
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
