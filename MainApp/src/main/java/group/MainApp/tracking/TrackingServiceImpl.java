package group.MainApp.tracking;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TrackingServiceImpl implements TrackingService {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    @Override
    public void save(Event event) {
        try {
            HttpPost httpPost = new HttpPost("http://localhost:10010/tracking/event");
            httpPost.setEntity(new StringEntity(jsonSerialize(event)));
            httpClient.execute(httpPost);
        } catch (Exception ignore) {

        }
    }

    @Override
    public List<Event> get() {
        try {
            HttpGet httpGet = new HttpGet("http://localhost:10010/tracking/logs");
            String response = EntityUtils.toString(httpClient.execute(httpGet).getEntity());
            return jsonDeserialize(response);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    static String jsonSerialize(Event event) {
        return new Gson().toJson(event);
    }

    static List<Event> jsonDeserialize(String response) {
        Type type = new TypeToken<ArrayList<Event>>() {
        }.getType();
        return new Gson().fromJson(response, type);
    }
}
