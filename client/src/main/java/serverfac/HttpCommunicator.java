package serverfac;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Map;

public class HttpCommunicator {
    public void run(String reqmeth, String json) throws Exception{
        // Specify the desired endpoint
        URI uri = new URI("http://localhost:8080/name");
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod("GET");

        // Make the request
        http.connect();

        // Specify that we are going to write out data
        http.setDoOutput(true);

        // Write out a header
        http.addRequestProperty("Content-Type", "application/json");

        // Write out the body
        var body = Map.of("name", "joe", "type", "cat");
        try (var outputStream = http.getOutputStream()) {
            var jsonBody = new Gson().toJson(body);
            outputStream.write(jsonBody.getBytes());
        }
    }
}
