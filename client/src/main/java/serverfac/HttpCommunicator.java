package serverfac;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Map;

public class HttpCommunicator {
    public static int port = 8080;
    public String run(String path, String reqmeth, String header, String json) throws Exception{
        // Specify the desired endpoint
        System.out.println(port);
        URI uri = new URI("http://localhost:" + Integer.toString(HttpCommunicator.port) + "/"+path);
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod(reqmeth);

        // Make the request
//        try {
//            http.connect();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            System.out.println(":(((((");
//        }

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

        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // Get HTTP response headers, if necessary
            // Map<String, List<String>> headers = http.getHeaderFields();
            // OR
            //connection.getHeaderField("Content-Length");

            InputStream responseBody = http.getInputStream();
            // Read and process response body from InputStream ...
            String res = new String(responseBody.readAllBytes());
            http.disconnect();
            return res;
        } else {
            // SERVER RETURNED AN HTTP ERROR
            InputStream responseBody = http.getErrorStream();
            // Read and process error response body from InputStream ...
        }
        http.disconnect();
        return null;
    }
}
