package com.ticket;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Ticket  {

	private int counter=100;
	private String pnr;
	private LocalDate travelDate;
	private Train train;

	Map<Passenger , Integer>  passengers = new TreeMap<>();

	public Ticket(LocalDate travelDate, Train train) {
		if(travelDate.isBefore(LocalDate.now()))
		{
			System.out.println("Travel Date is before current Date ");
			System.exit(1);
		}
		this.travelDate = travelDate;
		this.train = train;
	}
	
	
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Map<Passenger, Integer> getPassengers() {
		return passengers;
	}

	public void setPassengers(Map<Passenger, Integer> passengers) {
		this.passengers = passengers;
	} 
	
	
	
	public String generatepnr()
	{
		char s = train.getSource().charAt(0);
		char d = train.getDestination().charAt(0);
		int i=0;
		
		while(i<passengers.size())
		{
			pnr = s +""+ d + "_"+ travelDate.getYear()+ travelDate.getMonthValue() +travelDate.getDayOfMonth()+"_"+ counter;
			++counter;
			i++;
		}
		return pnr;	
	}
	
	public double calPassengerFare(Passenger passenger)
	{
		double fare = 0 ;
		if(passenger.getAge()<=12)
		{
			fare = train.getTicketPrice() * 0.5;
		}
		else if(passenger.getAge()>=60){
			fare = train.getTicketPrice() *0.6;	
		}
		else if(passenger.getGender()=='F')
		{
			fare = train.getTicketPrice()*0.75;
		}
		else
		{
			fare = train.getTicketPrice();
		}
		return fare ;
		
	}
	
	public void addPassenger(String name ,int age ,char gender)
	{
		passengers.put( new Passenger(name,age,gender),(int) calPassengerFare(new Passenger(name,age,gender)));	
	}
	
	
	public double calculateTotalTicketPrice()
	{
		double totalPrice=0;
		for(Passenger key :passengers.keySet() )
		{
			totalPrice = totalPrice +passengers.get(key);
		}
		return totalPrice ;		
	}
	
	public StringBuilder generateTicket()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("PNR :" +generatepnr());
		sb.append("Train Number : " +train.getTrainNo()+"\n");
		sb.append("Train Name : " +train.getTrainName()+"\n");
		sb.append("From : " + train.getSource()+"\n");
		sb.append("To : " + train.getDestination()+"\n");
		sb.append("Travel Date : " + travelDate+"\n");
		sb.append("Passengers :\n");
		sb.append("Name \t Age \t Gender\tFare\n");
		
		for(Passenger key :passengers.keySet() )
		{
			sb.append(key.getName()+" \t "+key.getAge()+ " \t "+key.getGender() +"\t"+ passengers.get(key)+"\n");
		}
		sb.append("Total Price :  " + calculateTotalTicketPrice());
		
		
		return sb;
		
	}
	
	public void writeTicket() throws IOException
	{
		
		String str = this.generateTicket().toString();
		String filename = this.generatepnr() + ".txt";
		File file = new File(filename);
		FileOutputStream fos = new FileOutputStream(file,true);
		byte[] text = str.getBytes();
		
		fos.write(text);
		fos.close();
	}


}
