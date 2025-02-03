package bookmyshow;

import java.sql.*;

public class CustomerService {

	public boolean registerCustomer(String name, String email, String phone, String password) {
        String insertQuery = "INSERT INTO Customers (name, email, phone, password) VALUES (?, ?, ?, ?)";
        try (
        		 Connection conn = DatabaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, password); 
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	 public Customer loginCustomer(String email, String password) {
	        String selectQuery = "SELECT * FROM Customers WHERE email = ? AND password = ?";
	        try ( Connection conn = DatabaseManager.connect();
	             PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
	            stmt.setString(1, email);
	            stmt.setString(2, password);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                int phone = rs.getInt("phone");
	                return new Customer(id, name, email, phone, password);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null; 
	    }

	
}

