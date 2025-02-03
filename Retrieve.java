package bookmyshow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Retrieve {

    private String name;
    private Map<String, List<Show>> genreShowsMap = new HashMap<>(); 

    public Retrieve(String name) {
        this.name = name;
    }

    public void loadShows() {
        try {
            Connection conn = DatabaseManager.connect();
            Statement stmt = conn.createStatement();

            String query = "SELECT s.id, m.id AS movie_id, m.title, m.duration, m.genre, s.time, s.available_seats " +
                           "FROM shows s JOIN movies m ON s.movie_id = m.id";
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                int movieId = result.getInt("movie_id");
                String title = result.getString("title");
                int duration = result.getInt("duration");
                String genre = result.getString("genre");

                
                System.out.println("Movie ID: " + movieId + ", Title: " + title + ", Duration: " + duration + ", Genre: " + genre);

                
                if (title == null || genre == null) {
                    System.out.println("Warning: Missing data for movie ID: " + movieId);
                    continue;  
                }

                Movie movie = new Movie(movieId, title, duration, genre);

               
                Show show = new Show(result.getInt("id"), movie, result.getString("time"), result.getInt("available_seats"));
                genreShowsMap.computeIfAbsent(genre, k -> new ArrayList<>()).add(show);
            }


            result.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error loading shows: " + e.getMessage());
        }
    }

    

    public void displayShows() {
        System.out.println("Available Shows in " + name + ":");
        if (genreShowsMap.isEmpty()) {
            System.out.println("No shows available.");
        } else {
            
            for (String genre : genreShowsMap.keySet()) {
                System.out.println("\nGenre: " + genre);
                List<Show> showsByGenre = genreShowsMap.get(genre);
                for (int i = 0; i < showsByGenre.size(); i++) {
                    Show show = showsByGenre.get(i);
                   
                    System.out.println((i + 1) + ". Movie: " + show.getMovie().getTitle() + " | ID: " + show.getId() + 
                                       " | Time: " + show.getTime() + " | Available Seats: " + show.getavailableseats());
                }
            }
        }
    }



    public Show getShow(int index) {
        
        int count = 0;
        for (List<Show> showsByGenre : genreShowsMap.values()) {
            if (index <= count + showsByGenre.size()) {
                return showsByGenre.get(index - count - 1);  
            }
            count += showsByGenre.size();
        }
        return null;
    }

    public void updateSeats(Show show) {
        try {
            Connection conn = DatabaseManager.connect();
            String query = "UPDATE shows SET available_seats = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, show.getavailableseats());
            stmt.setInt(2, show.getId());

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error updating seats: " + e.getMessage());
        }
    }

    public Map<String, List<Show>> getGenreShowsMap() {
        return genreShowsMap;  
    }
	}



