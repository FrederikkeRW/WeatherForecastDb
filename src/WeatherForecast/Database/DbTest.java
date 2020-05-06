package WeatherForecast.Database;

import java.sql.Connection;

public class DbTest {
    public static boolean testConnection(){
        Connection conn = DbAccess.openDBConnection();
        System.out.println("conn: " + conn);
        if( conn != null) {
            DbAccess.closeDbConnection(conn);
            return true;
        } else {
            return false;
        }
    }
}
