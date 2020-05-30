/*
	This program obtains data from tickets.txt in "E:\eclipse-workspace\OOPHW2\bin",
		parses the data to find the count, max, min and average values,
		then outputs data in a specified format to output.txt in "E:\eclipse-workspace\OOPHW2\bin".
	Author: Darryl Karney
	Course: CPSC24500
 */

package HW2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HW2Main {
	
	static DecimalFormat mydf = new DecimalFormat("000.00");

	public static void main(String[] args) {
		//Open file
		Scanner input = null;
		
		try {
			input = new Scanner(new File("E:\\eclipse-workspace\\OOPHW2\\bin\\tickets.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open import file!");
			System.exit(1);
		}
		
		//Import values
		ArrayList<Double> ticketPrices = new ArrayList<>();
		
		while(input.hasNext()) {
			//Bypass headers
			String ticket = input.next();
			if (ticket.equals("Ticket") || ticket.equals("Price")) {
				continue;
			}
			
			//Obtain price
			double price = input.nextDouble();
			
			//Add values to ArrayLists
			ticketPrices.add(price);
		}
		
		//Get ticket count, max ticket price, min ticket price and average ticket price
		int ticketCount = ticketPrices.size();
		double maxTicketPrice = Collections.max(ticketPrices),
			minTicketPrice = Collections.min(ticketPrices),
			averageTicketPrice;
		
		double sum = 0;
		for(Double e : ticketPrices) {
			sum += e;
		}
		
		averageTicketPrice = sum / ticketCount;
		
		//Create and open output file
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("E:\\eclipse-workspace\\OOPHW2\\bin\\output.txt"));
		} catch (IOException e1) {
			System.out.println("Unable to create output file!");
			System.exit(2);
		}
		
		//Output headers
		writeToFile(writer, "*******************************************");
		writeToFile(writer, "	TICKET REPORT");
		writeToFile(writer, "*******************************************\n");
		//Output content
		writeToFile(writer, "There are " + ticketCount + " tickets in the database.\n");
		writeToFile(writer, "Maximum Ticket price is $" + maxTicketPrice + ".");
		writeToFile(writer, "Minimum Ticket price is $" + minTicketPrice + ".");
		writeToFile(writer, "Average Ticket Price is $" + mydf.format(averageTicketPrice) + ".\n");
		//Output footer
		writeToFile(writer, "Thank you for using out ticket system!");
		writeToFile(writer, "*******************************************");
		
		//Close the writer
		try {
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private static void writeToFile(BufferedWriter bw, String s) {
		try {
			bw.append(s);
			bw.newLine();
		} catch (IOException e) {
			System.out.println("Unable to write to output file!");
			System.exit(3);
		}
	}

}
