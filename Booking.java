package bookmyshow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Booking {
    
    String name;

    public Booking(String name) {
        this.name = name;
    }

    public void bookTickets(Retrieve retrieve, int showIndex, int seats) {
        
        Show show = getShowByIndex(retrieve, showIndex);

        if (show != null) {
            if (show.bookSeat(seats)) {
            	System.out.println("");
                System.out.println(name + " successfully booked " + seats + " seats for " + show.getMovie().getTitle());
                saveBookingToDatabase(show.getId(), seats);
                retrieve.updateSeats(show);  
            } 
            else {
                System.out.println("Not enough seats available.");
            }
        } else {
            System.out.println("Invalid show selection.");
        }
    }

    private Show getShowByIndex(Retrieve retrieve, int showIndex) {
        int count = 0;
        
        for (List<Show> showsByGenre : retrieve.getGenreShowsMap().values()) {
            if (showIndex <= count + showsByGenre.size()) {
                return showsByGenre.get(showIndex - count - 1);  
            }
            count += showsByGenre.size();
        }
        return null; 
    }

    private void saveBookingToDatabase(int showId, int seats) {
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO bookings (user_name, show_id, seats) VALUES (?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setInt(2, showId);
            stmt.setInt(3, seats);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

