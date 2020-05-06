package WeatherForecast.DMI_Simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DMI_Simulator {
    /*
    Returning a list of the weather forecast for today and the next 3 days.
    Each day contains a list of three different forecast, through out the day: Morning, afternoon and evening.
    Each forecast contains:
        Temp:       c
        Wind:       m/s
        Cloudiness: Enum Text Description
        Rain:       mm
        Snow:       cm

     */
    public static ArrayList<ArrayList> fourDaysWeatherForecast(){
        ArrayList<ArrayList> fourDays = new ArrayList<ArrayList>();

        ArrayList<Map<WeatherTypes,Object>> dayForcasts = new ArrayList<Map<WeatherTypes, Object>>();

        HashMap<WeatherTypes,Object> morning = new HashMap<WeatherTypes,Object>();
        HashMap<WeatherTypes,Object> afternoon = new HashMap<WeatherTypes,Object>();
        HashMap<WeatherTypes,Object> evening = new HashMap<WeatherTypes,Object>();

        morning.put(WeatherTypes.Temp,"18");
        morning.put(WeatherTypes.Wind,"4");
        morning.put(WeatherTypes.Cloudiness,CloudinessTypes.MOSTLY_CLOUDY);
        morning.put(WeatherTypes.Rain, "0");
        morning.put(WeatherTypes.Snow, "0");
        dayForcasts.add(morning); // cell nr 0

        afternoon.put(WeatherTypes.Temp,"25");
        afternoon.put(WeatherTypes.Wind,"4");
        afternoon.put(WeatherTypes.Cloudiness,CloudinessTypes.PARTLY_CLOUDY);
        afternoon.put(WeatherTypes.Rain, "0");
        afternoon.put(WeatherTypes.Snow, "0");
        dayForcasts.add(afternoon); // cell nr 1

        evening.put(WeatherTypes.Temp,"14");
        evening.put(WeatherTypes.Wind,"4");
        evening.put(WeatherTypes.Cloudiness,"Partly Cloudy");
        evening.put(WeatherTypes.Rain, "0");
        evening.put(WeatherTypes.Snow, "0");
        dayForcasts.add(evening); // cell nr 2

        fourDays.add(dayForcasts);
        fourDays.add(dayForcasts);
        fourDays.add(dayForcasts);
        fourDays.add(dayForcasts);

        return fourDays;
    }
}
