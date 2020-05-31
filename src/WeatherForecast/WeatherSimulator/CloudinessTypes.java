package WeatherForecast.WeatherSimulator;

public enum CloudinessTypes {
    CLOUDY ("Cloudy", "Cloudy, no sun", "A"),
    MOSTLY_CLOUDY ("Mostly Cloudy", "Many clouds, with little sun", "B"),
    PARTLY_CLOUDY ("Partly Cloudy", "Predominantly cloudy", "C"),
    PARTLY_SUNNY ("Partly sunny", "Predominantly sunny", "D"),
    MOSTLY_SUNNY ("Mostly sunny", "Blue sky, with some clouds", "E"),
    SUNNY ("Sunny", "Blue sky, no clouds to see", "F")
    ;

    public final String text;
    public final String description;
    public final String value;

    CloudinessTypes (final String text, final String description, final String value){
        this.text = text;
        this.description = description;
        this.value = value;
    }

    @Override
    public String toString() {
        return text + ", " + description;
    }

}
