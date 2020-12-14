import java.util.*;
import java.io.*;
import java.time.*;
import java.lang.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

abstract class Hotel implements Serializable { // hotel class
	static Scanner in = new Scanner(System.in);
	String hotel_name;
	String city;
	String room_type;
	String hotel_category;
	LocalDate cin;
	LocalDate cout;
	long fare;

	abstract public void bill();
}

class User extends Hotel { // user class inherits properties of hotel class also
	String name;
	String id_type;
	int id_no;
	int rno;
	long bill;

	public User() // constructor
	{
		System.out.println("Enter name of the city:(hyderabad, chennai or banglore)\t");
		city = in.nextLine();
		Project.cls();
	}

	public void bill() // method to calculate the number of days the customer has stayed in the hotel
	{
		long days;
		days = ChronoUnit.DAYS.between(cin, cout);
		System.out.print("\nThe number of days you stay in the hotel are:\t" + days);
		bill = days * fare;
	}
}

class Project { // main class project

	public static int room_no = 100;
	static ArrayList<User> details = new ArrayList<User>(); // array list to store all the objects/users
	static ArrayList<User> read = new ArrayList<User>(); // array list to store the objects read from the file

	public static void cls() // method to clear the cmd console
	{
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception e) {
		}
	}

	public static void search() // method to search details of all the hotel where a person with a particular id
								// stayed on a particular date.
	{
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		Scanner i = new Scanner(System.in);
		int gid;
		LocalDate date;
		String date1;
		System.out.println("\n enter the id of the person:\t");
		gid = i.nextInt();
		i.nextLine();
		System.out.println("\n enter check-in date:\t");
		date1 = i.nextLine();
		date = LocalDate.parse(date1, format1);
		
		try { // reading objects from the file and storing in array list read.
			FileInputStream fis = new FileInputStream("data.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			boolean flag = true;
			while (flag) {
				User o = null;
				o = (User) ois.readObject();
				if (o != null) {
					read.add(o);
				} else
					flag = false;
			}
			fis.close();
			ois.close();
		} catch (Exception e) {
			System.out.println("\n");
		}

		for (User al : read) {
			if ((al.id_no == gid) && (date.compareTo(al.cin) == 0)) {
				System.out.println("Person name:\t" + al.name);
				System.out.println("Hotel name:\t" + al.hotel_name);
				System.out.println("Hotel category:\t" + al.hotel_category);
				System.out.println("Room type:\t" + al.room_type);
				System.out.println("Room no:\t" + al.rno);
				System.out.println("City:\t" + al.city);
				System.out.println("\n\n");
			}
		}
	}

	public static void save() // method to write all the objects/users/customers into a file.
	{
		try {

			FileOutputStream fos = new FileOutputStream("data.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (User object : details) {
				oos.writeObject(object);
			}
			oos.close();
			fos.close();
		} catch (IOException e) {
			System.out.print("error" + " " + e);
		}
	}

	public static void main(String[] args) // main method
	{
		while (true) {

			Scanner input = new Scanner(System.in);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			int choice, choice1;
			String checkin;
			String checkout;
			int p;
			System.out.println("\t\t\t*****HOTEL MANAGEMENT SYSTEM*****");
			System.out.println("1.Exit:");
			System.out.println("2.Hotel booking");
			System.out.println("3.Search details");
			System.out.println("4.save details");
			p = input.nextInt();
			input.nextLine();
			if (p == 1)
				System.exit(0);
			else if (p == 3) {
				search();
				System.exit(0);
			} else if (p == 4) {
				save();
				// System.exit(0);
				main(null);
			}

			User obj = new User();
			switch (obj.city) {
				case "hyderabad": {
					System.out.println("1.Novotel Hyderabad ***** 5000 rupees per day per person");
					System.out.println("2.Taj Deccan **** 4500 rupees per day per person");
					System.out.println("3.ITC Kakatiya *** 4000 rupees per day per person");
					choice = input.nextInt();
					switch (choice) {
						case 1: {
							obj.hotel_name = "Novotel Hyderabad";
							obj.hotel_category = "5 star";
							obj.fare = 5000; // the value of fare is different for different hotels in a city...the
												// value of fare changes again according to the number of persons in a
												// room
							break;
						}
						case 2: {
							obj.hotel_name = "Taj Deccan";
							obj.hotel_category = "4 star";
							obj.fare = 4500;
							break;
						}
						case 3: {
							obj.hotel_name = "ITC Kakatiya";
							obj.hotel_category = "3 star";
							obj.fare = 4000;
							break;
						}
					}
					break;
				}
				case "chennai": {
					System.out.println("1.Marigold by Greenpark **** 2000 rupees per day per person");
					System.out.println("2.Hyatt Chennai ***** 4000 rupees per day per person");
					System.out.println("3.Radisson Chennai *** 1000 rupees per day person");
					choice = input.nextInt();
					cls();
					switch (choice) {
						case 1: {
							obj.hotel_name = "Marigold by Greenpark";
							obj.hotel_category = "4 star";
							obj.fare = 2000;
							break;
						}
						case 2: {
							obj.hotel_name = "Hyatt Chennai";
							obj.hotel_category = "5 star";
							obj.fare = 4000;
							break;
						}
						case 3: {
							obj.hotel_name = "Radisson Chennai";
							obj.hotel_category = "3 star";
							obj.fare = 1000;
							break;
						}
					}
					break;
				}
				case "banglore": {
					System.out.println("1.Manasarovar The Fern *** 4000 rupees per day per person");
					System.out.println("2.Sheraton Banglore Hotel ***** 4500 rupees per day per person");
					System.out.println("3.Hotel AVASA *** 3000 rupees per day per person");
					choice = input.nextInt();
					cls();
					switch (choice) {
						case 1: {
							obj.hotel_name = "Manasarovar The Fern";
							obj.hotel_category = "3 star";
							obj.fare = 4000;
							break;
						}
						case 2: {
							obj.hotel_name = "Sheraton Banglore Hotel";
							obj.hotel_category = "5 star";
							obj.fare = 4500;
							break;
						}
						case 3: {
							obj.hotel_name = "Hotel AVASA";
							obj.hotel_category = "3 star";
							obj.fare = 3000;
							break;
						}
					}
					break;
				}
				default: {
					System.out.println(".");
				}
			}
			System.out.println("choose the type of room:\t");
			System.out.println("1.Single room(Total 1P in a room)");
			System.out.println("2.Double room(Total 2P in a room)");
			System.out.println("3.Triple room(Total 3P in a room)");
			System.out.println("4.Quad room(Total 4P in a room)");
			choice1 = input.nextInt();
			input.nextLine();
			cls();
			if (choice1 == 1) {
				obj.room_type = "single room";
				obj.fare = obj.fare * 1; // here the value of fare changes as per the type of room.
			} else if (choice1 == 2) {
				obj.room_type = "double room";
				obj.fare = obj.fare * 2;
			} else if (choice1 == 3) {
				obj.room_type = "triple room";
				obj.fare = obj.fare * 3;
			} else if (choice1 == 4) {
				obj.room_type = "quad room";
				obj.fare = obj.fare * 4;
			} else
				System.out.println(".");

			System.out.println("enter your name:\t");
			obj.name = input.nextLine();
			cls();
			System.out.println("enter the type of id card:\t");
			obj.id_type = input.nextLine();
			cls();
			System.out.println("enter id number:\t");
			int id = input.nextInt();
			cls();
			obj.id_no = id;
			input.nextLine();
			System.out.println("enter check-in date:(dd-MM-yyyy)\t");
			checkin = input.nextLine();
			cls();
			obj.cin = LocalDate.parse(checkin, format);
			System.out.println("enter check-out date:(dd-MM-yyyy)\t");
			checkout = input.nextLine();
			obj.cout = LocalDate.parse(checkout, format);
			cls();
			System.out.println("Your room is booked and room number is:\t");
			System.out.print(room_no);
			obj.rno = room_no;
			obj.bill();
			System.out.println("\nYour bill is:\t");
			System.out.print(obj.bill);
			room_no++;
			details.add(obj);
			

		}
	}
}