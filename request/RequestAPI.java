package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


public class RequestAPI {
    
    public final String API_KEY = "146d54d3d50a01d02779b66d1a6fede2";


    public int getTotalPages() {
        try {
            URL url = new URL("https://api.themoviedb.org/3/trending/movie/day?api_key=" + API_KEY);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                JsonElement jsonResponse = JsonParser.parseString(response.toString());

                int totalPages = jsonResponse.getAsJsonObject().get("total_pages").getAsInt();
                return totalPages;
            } else {
                System.out.println("Request Failed. Response Code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
