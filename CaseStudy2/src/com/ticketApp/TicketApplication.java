package com.ticketApp;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import com.ticket.Ticket;

public class TicketApplication {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

		Scanner scanner = new Scanner(System.in);
		TrainDAO t1 = new TrainDAO();

		System.out.println("Enter Train Number : ");
		int trainNo = scanner.nextInt();

		if(t1.findTrain(trainNo)!=null)
		{
			System.out.println("Enter Date in yyyymmdd format :");
			int y = scanner.nextInt();
			int m = scanner.nextInt();
			int d = scanner.nextInt();
			LocalDate date = LocalDate.of(y, m, d);

			System.out.println("Enter Number of Passengers");
			int np = scanner.nextInt();
			
			Ticket ticket = new Ticket(date, t1.findTrain(trainNo));
		
			for(int i=0;i<np;i++)
			{
				System.out.println("Enter Passenger Name");
				String name = scanner.next();

				System.out.println("Enter Age");
				int age = scanner.nextInt();

				System.out.println("Enter Gender(M/F)");
				char gender = scanner.next().charAt(0);	
				
				ticket.addPassenger(name, age, gender);
			}
			//System.out.println(ticket.generatepnr());
			System.out.println(ticket.generateTicket());
			ticket.writeTicket();
		}
		else
		{
			System.out.println("Train with given number does not exists");
		}

	}

}
