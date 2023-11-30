package api;

import okhttp3.*;

import java.io.IOException;

/**
 * @deprecated
 */
public class WifiApi {
    OkHttpClient client = new OkHttpClient();

    public String post(String url, FormBody body) {

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
