package edu.ncsu.csc216.product_backlog.model.io;

import java.io.File;

import java.io.PrintStream;
import java.util.ArrayList;


import edu.ncsu.csc216.product_backlog.model.product.Product;
import edu.ncsu.csc216.product_backlog.model.task.Task;


/**
 * 
 * Responsible for printing and writing the valid files onto another given file
 * 
 * @author Veerendra Gottiveeti
 *
 */
public class ProductsWriter {

	/**
	 * Writer for the various products onto a seperate file
	 */
	
	public ProductsWriter() {
		//nothing is needed for this constructor
	}
	
	/**
	 * Writes the products onto a seperate file
	 * @param fileName fileName represents the name for the given file
	 * @param product product represents the List Object within the List of Products
	 */
	
	public static void writeProductsToFile(String fileName, ArrayList<Product> product) {
	 
		try {    
		//Setting the PrintStream fileWriter for the specific file
		PrintStream fileWriter = new PrintStream(new File(fileName));
		//Initializes the Product writing to product
		for (Product writing : product) {
			//Adds the product with the concatenated string with the product name
			fileWriter.println("# " + writing.getProductName());
			
			ArrayList<Task> tasks = writing.getTasks();
			
			// if the size of the tasks is 0, throw exception
			if (tasks.size() == 0) {
				
				fileWriter.close();
				
				throw new IllegalArgumentException("Unable to save file");
			
			}
			
			//for loop that is reponsible for writing the tasks and notes onto the file
			for (int i = 0; i < tasks.size(); i++) {
				//gets the notes and products
				if (i == tasks.size() - 1 && writing == product.get(product.size() - 1)) {
				
					Task reading = tasks.get(i);
					//sets the notes and products with their designated seperations and initializes
					fileWriter.println("* " + reading.getTaskId() + "," + reading.getStateName() + "," + reading.getTitle() + "," + reading.getTypeShortName() + "," + reading.getCreator() + "," + reading.getOwner() + "," + reading.isVerified());
					
					ArrayList<String> notes = reading.getNotes();
					//for loop that prints the concatenated string with the tasks and products
					for (int m = 0; m < notes.size(); m++) {
						
						if (m == notes.size() - 1) {
							fileWriter.print("- " + notes.get(m));
						}
						else {
							fileWriter.println("- " + notes.get(m));
						}
						}
				}
				//gets the proper string format for the writer
				else {
					Task toWord = tasks.get(i);
					fileWriter.print(toWord.toString());
			}
		}
		}
		
		fileWriter.close();
	  }
		//if cannot find file throw exception
	   catch (Exception e) {
		   throw new IllegalArgumentException("Unable to save file.");
	   }
	}
}
