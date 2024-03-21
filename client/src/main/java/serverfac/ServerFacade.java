package serverfac;
import com.google.gson.Gson;
import java.util.*;
import java.io.*;
import java.net.*;

public class ServerFacade {

    public void register(String username, String password, String email) throws Exception{
        URI uri = new URI("http://localhost:8080/name");
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod("POST");

        // Make the request
        http.connect();

        // Specify that we are going to write out data
        http.setDoOutput(true);

        // Write out a header
        http.addRequestProperty("Content-Type", "application/json");

        // Write out the body
        var body = Map.of("username", username, "password", password, "email", email);
        try (var outputStream = http.getOutputStream()) {
            var jsonBody = new Gson().toJson(body);
            outputStream.write(jsonBody.getBytes());
        }
    }
}
