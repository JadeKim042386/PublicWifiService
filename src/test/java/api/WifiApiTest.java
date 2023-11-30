package api;

import config.Config;
import okhttp3.FormBody;
import org.junit.jupiter.api.Test;

import java.util.Properties;

class WifiApiTest {
    private final WifiApi api = new WifiApi();
    private Properties properties;

    @Test
    void getWifi() {
        properties = Config.setProperties();
        String latitude = "37.2747488";
        String longitude = "127.018885";
        String distance = "0.5";
        FormBody body = new FormBody.Builder()
                .add("searchLat", latitude)
                .add("searchLon", longitude)
                .add("searchDistance", distance)
                .add("apiAuthKey", properties.getProperty("wifi_api.key"))
                .build();
        String response = api.post(properties.getProperty("wifi_api.url"), body);
        System.out.println(response);
    }
}