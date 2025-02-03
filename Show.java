package bookmyshow;

public class Show {  

    private int id;
    private Movie movie;
    private String time;
    private int availableseats;

   
    public Show(int id, Movie movie, String time, int availableseats) {
        this.id = id;
        this.movie = movie;
        this.time = time;
        this.availableseats = availableseats;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

   

    public void setAvailableSeats(int availableseats) {
        this.availableseats = availableseats;
    }

   
    public boolean bookSeat(int count) {
        if (availableseats >= count) {
            availableseats -= count;
            return true;
        } else {
            return false;
        }
    }

   
    public String toString() {  
        return "Movie: "+movie.getTitle() + " | ID: "+id + " | Time: "+time+" | Available Seats: "+ availableseats; 
    }
    


	public int getavailableseats() {
		
		return availableseats;
	}
}



