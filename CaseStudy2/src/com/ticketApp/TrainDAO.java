package com.ticketApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ticket.Train;

public class TrainDAO {

	private static final String  DRIVER_NAME = "oracle.jdbc.OracleDriver";
	private static final  String DB_URL = "jdbc:oracle:thin:@localhost/XE";
	private static final  String USERNAME = "hr";
	private static final String PASSWORD = "hr" ;
	
	

	public Train findTrain(int trainNo) throws ClassNotFoundException, SQLException
	{
		Train train = new Train();
		Class.forName(DRIVER_NAME);
		Connection connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
		Statement statement = connection.createStatement();

		String query = "select * from trains where Train_No =" + trainNo;
		
		ResultSet resultSet = statement.executeQuery(query);
		

		while(resultSet.next())
		{
			int no = resultSet.getInt(1);
			String name = resultSet.getString(2);
			String source = resultSet.getString(3);
			String destination = resultSet.getString(4);
			double price = resultSet.getDouble(5);
			
			train.setTrainNo(no);
			train.setTrainName(name);
			train.setSource(source);
			train.setDestination(destination);
			train.setTicketPrice(price);
		   
						
		}
		
		connection.close();
		return train;
		
	}

}
