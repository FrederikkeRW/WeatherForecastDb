package WeatherForecast.DMI_Simulator;

public enum CloudinessTypes {
    CLOUDY ("Cloudy"),
    MOSTLY_CLOUDY ("Mostly Cloudy"),
    PARTLY_CLOUDY ("Partly Cloudy"),
    PARTLY_SUNNY ("Partly sunny"),
    MOSTLY_SUNNY ("Mostly sunny"),
    SUNNY ("Sunny")
    ;

    private final String text;

    CloudinessTypes (final String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
