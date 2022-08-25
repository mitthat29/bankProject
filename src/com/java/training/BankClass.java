package com.java.training;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BankClass {
	static Connection conn = DatabaseConnection.connect();
	static String str = "";
	static PreparedStatement st;
	static ResultSet rs;
	String fname;
	String lname;
	String gender;
	String city;
	String DOB;
	String mobileno;
	String email;
	int acc_no;
	String acc_type;
	float balance;
	String pass;
	String sql = "";

	Scanner sc = new Scanner(System.in);

	// CREATE ACCOUNT-OPTION 1 OF MAIN MENU
	public int createAccount() {
		System.out.println("Please enter your First Name: ");
		fname = sc.nextLine();
		System.out.println("Please enter your Last Name: ");
		lname = sc.nextLine();
		System.out.println("Please enter your Gender: ");
		gender = sc.nextLine();
		System.out.println("Please enter your City: ");
		city = sc.nextLine();
		System.out.println("Please enter your DOB: ");
		DOB = sc.nextLine();
		System.out.println("Please enter your Email: ");
		email = sc.nextLine();
		System.out.println("Please enter your Mobile Number: ");
		mobileno = sc.nextLine();
		System.out.println("Please enter your Account Type(Salary/Savings/Current): ");
		acc_type = sc.nextLine();
		System.out.println("Please enter your Opening Balance: ");
		balance = sc.nextFloat();
		sc.nextLine();
		System.out.println("Please enter your Password: ");
		pass = sc.nextLine();

		try {
			str = "INSERT INTO bank values('" + fname + "','" + lname + "','" + gender + "','" + city + "','" + DOB
					+ "'," + mobileno + ",seq.nextval,'" + acc_type + "'," + balance + ",'" + pass + "','" + email
					+ "')";
			st = conn.prepareStatement(str); // creating a statement object
			if (st.executeUpdate() == 1) {
				System.out.println("Account created Successfully!");
				rs = st.executeQuery("select acc_no from bank where fname='" + fname + "' and lname='" + lname
						+ "' and pass='" + pass + "'");
				rs.next(); //this would set the pointer/cursor to the first row(from the default position)
				acc_no = rs.getInt("acc_no");
			} else {
				System.out.println("OOPS!Your account couldn't be created. Try again.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return acc_no;
	}

	// LOGIN-OPTION 2 OF MAIN MENU
	public void login() {
		System.out.println("Please enter your Email: ");
		email = sc.nextLine();
		System.out.println("Please enter your Password: ");
		pass = sc.nextLine();
		try {
			st = conn.prepareStatement("select * from bank where email='" + email + "' and pass='" + pass + "'");
			rs = st.executeQuery();
			if (rs.next()) {
				while (true) {
					acc_no = rs.getInt("acc_no");
					System.out.println("Welcome " + rs.getString("fname") + " " + rs.getString("lname"));
					System.out.println("1. Check Balance");
					System.out.println("2. Deposit Money");
					System.out.println("3. Withdraw Money");
					System.out.println("4. Logout");
					System.out.println("Please select an option");
					int choice = sc.nextInt();
					System.out.println("------------------------------------------------");
					switch (choice) {
					case 1:
						System.out.println("Your account balance is: " + LoginMenu.checkBalance(acc_no));
						break;
					case 2:
						LoginMenu.deposit(acc_no);
						break;
					case 3:
						LoginMenu.withdraw(acc_no);
						break;
					case 4:
						System.out.println("Thank You!");
						System.exit(0);
						break;
					default:
						System.out.println("OOPS! Invalid choice.");
					}
					System.out.println("------------------------------------------------");
				}
			} else {
				System.out.println("OOPS! Invalid credentials.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}