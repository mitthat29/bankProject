package com.java.training;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		int choice = 0;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated method stub
		BankClass b = new BankClass();
		while (true) {
			System.out.println("---------------WELCOME TO DOXBANK---------------");
			System.out.println("1. Create a new account.");
			System.out.println("2. Login into your account.");
			System.out.println("Please select an option.");
			choice = sc.nextInt();
			System.out.println("------------------------------------------------");
			switch (choice) {
			case 1:
				//CREATE A NEW ACCOUNT
				System.out.println("Your account number is " + b.createAccount());
				System.out.println("Login to explore.");
				break;
			case 2:
				//LOGIN TO YOUR ACCOUNT
				b.login();
				break;
			default:
				System.out.println("Invalid choice.");
			}
		}

	}

}
