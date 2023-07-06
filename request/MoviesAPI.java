package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import modal.Movie;
import modal.MovieInfos;


import modal.MovieInfos;

public class MoviesAPI {
    
    public final String API_KEY = "146d54d3d50a01d02779b66d1a6fede2";


    public List<MovieInfos> getMoviesFromAPI(int page) {
        List<MovieInfos> movies = new ArrayList<>();

        try {
            URL url = new URL("https://api.themoviedb.org/3/trending/movie/day?api_key=" + API_KEY + "&page=" + page);

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

                JsonArray results = jsonResponse.getAsJsonObject().get("results").getAsJsonArray();

                for (JsonElement result : results) {
                    JsonObject movie = result.getAsJsonObject();
                    int id = movie.get("id").getAsInt();
                    String title = movie.get("title").getAsString();
                    String overview = movie.get("overview").getAsString();
                    String releaseDate = movie.get("release_date").getAsString();
                    double voteAverage = movie.get("vote_average").getAsDouble();

                    MovieInfos movieObj = new MovieInfos(id, title, overview, releaseDate, voteAverage);
                    movies.add(movieObj);                }
            } else {
                System.out.println("Request Failed. Response Code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public MovieInfos getMovieById(int id) {
        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/" + id + "?api_key=" + API_KEY);

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

                String title = jsonResponse.getAsJsonObject().get("title").getAsString();
                String overview = jsonResponse.getAsJsonObject().get("overview").getAsString();
                String releaseDate = jsonResponse.getAsJsonObject().get("release_date").getAsString();
                double voteAverage = jsonResponse.getAsJsonObject().get("vote_average").getAsDouble();

                return new MovieInfos(id, title, overview, releaseDate, voteAverage);
            } else {
                System.out.println("Request Failed. Response Code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
