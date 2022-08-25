package com.java.training;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginMenu {
	static Connection conn = DatabaseConnection.connect();
	static ResultSet rs;
	static String str;
	static PreparedStatement st;
	static int acc_no;
	static float balance;
	static float amount;
	static Scanner sc = new Scanner(System.in);
	static float result;

	// CHECK BALANCE-OPTION 1 OF LOGIN MENU
	public static float checkBalance(int acc_no) {
		try {
			str = "SELECT * from BANK where acc_no="+acc_no;
			st = conn.prepareStatement(str);
			rs = st.executeQuery();
			rs.next();
			result = rs.getInt("balance");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// DEPOSIT MONEY-OPTION 2 OF LOGIN MENU
	public static void deposit(int acc_no) {
		try {
			System.out.println("Enter the amount you want to deposit");
			amount = sc.nextFloat();
			str = "update bank set balance=balance+" + amount + "where acc_no=" + acc_no;
			st = conn.prepareStatement(str);
			if (st.executeUpdate() == 1) {
				System.out.println("Amount credited successfully!");
				System.out.println("Your updated balance is " + checkBalance(acc_no));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// WITHDRAW MONEY-OPTION 3 OF LOGIN MENU
	public static void withdraw(int acc_no) {
		try {
			System.out.println("Enter the amount you want to withraw");
			amount = sc.nextFloat();
			if (amount > checkBalance(acc_no)) {
				System.out.println("Insufficient Balance!");
			}
			str = "update bank set balance=balance-" + amount + "where acc_no=" + acc_no;
			st = conn.prepareStatement(str);
			if (st.executeUpdate() == 1) {
				System.out.println("Amount debited successfully!");
				System.out.println("Your updated balance is " + checkBalance(acc_no));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
