package serverfac;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Map;

public class HttpCommunicator {
    public void run(String path, String reqmeth, String header, String json) throws Exception{
        // Specify the desired endpoint
        URI uri = new URI("http://localhost:8080/"+path);
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod(reqmeth);

        // Make the request
        http.connect();

        if(header != null) {
            http.addRequestProperty("authorization", header);
        }

        // Specify that we are going to write out data
        if(json != null) {
            http.setDoOutput(true);

            // Write out a header
            http.addRequestProperty("Content-Type", "application/json");

            // Write out the body
            try (var outputStream = http.getOutputStream()) {
                outputStream.write(json.getBytes());
            }
        }
    }
}
