package bookmyshow;

public class Customer {

	private int Id;
	private String name;
	private String email;
	private int phn;
	private String password;
	
	public Customer(int Id, String name, String email, int phone, String password) {
		this.Id=Id;
		this.name=name;
		this.email=email;
		this.phn=phone;
		this.password=password;
	}
	public int getId() {
		return Id;
	}
	public String getname() {
		return name;
	}
	public String getemail() {
		return email;
	}
	public int getphn() {
		return phn;
	}
	public String getpassword() {
		return password;
	}

	
}






