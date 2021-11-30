package edu.ncsu.csc216.product_backlog.model.io;

import java.io.FileInputStream;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.product_backlog.model.product.Product;
import edu.ncsu.csc216.product_backlog.model.task.Task;

/**
 * 
 * Responsible for reading through the list of products and selecting
 * the ones that are valid to furthur on to the write portion
 * 
 * @author Veerendra Gottiveeti
 *
 */

public class ProductsReader {
	
	/**
	 * Reader for the various products and tasks from a given file
	 */
	
	public ProductsReader() {
		//Leave empty constructor
	}
	
	/**
	 * Read the Products and Sets Them Using An ArrayList with a Product Object
	 * @param inputFile inputFile represents the name for the given file
	 * @return null if the file is not found
	 */
	
	public static ArrayList<Product> readProductsFile(String inputFile) {
		
		try {
			
			Scanner fileReader = new Scanner(new FileInputStream(inputFile));

			ArrayList<Product> products = new ArrayList<Product>(); //Create an empty array of Course objects
			
			//checks if the first letter of the file is proper #
			
			if(fileReader.next().charAt(0) != '#') {
				products = new ArrayList<Product>();
				return products;
			
			}
			//delimiter that seperates the products
			fileReader.useDelimiter("\\r?\\n?[#]");
			
			//uses the processProduct to read the file
	        try { 
	        	while (fileReader.hasNext()) {
	        		
	        		Product value = processProduct(fileReader.next());
	        		System.out.println(value);
	        		//if the values match the requirements, add
	        		if (value != null) {
	        			products.add(value);
		        		System.out.println(products);
	        		}
	        	}
	        }
	        catch (IllegalArgumentException e) {
	        	return products;
	        }
	          
	        //Close the Scanner b/c we're responsible with our file handles
	        fileReader.close();
	        //Return the SortedList with all the courses we read!
	        return products;
		}
		// if file not found, throw exception
	    catch (FileNotFoundException e) {
	    	throw new IllegalArgumentException("Unable to load file.");
	    }
	}
	
	
	/**
	 * Processes the Products using the Product Object
	 * @param product product represents a specific product for the given file
	 * @return null
	 */
	
	private static Product processProduct(String product) {
		
       String line = product.trim();
       
       if (line.charAt(0) == '*' || line.charAt(0) == '-') {
    	   throw new IllegalArgumentException("Unable to load file.");
       }
       
       Scanner scanner = new Scanner(line);
       scanner.useDelimiter("\\r?\\n?[*]");
       
       try {
    	   String productName = scanner.next();
   			System.out.println(productName);

    	   
    	   Product secondProduct = new Product(productName);
    	   
    	   while (scanner.hasNext()) {
    		   Task secondTask = processTask(scanner.next());
    		   
    		   if (secondTask != null) {
    			   try {
    				   secondProduct.addTask(secondTask);
    			   }
    			   catch (Exception e) {
    				   //empty
    			   }
    		   }
    		   
    		   else {
    			   scanner.close();
    			   return null;
    		   }
    	   }
    	   
    	   ArrayList<Task> tasks = secondProduct.getTasks();
    	   
    	   int i = tasks.size();
    	  
    	   if (i == 0) {
    		
    		   scanner.close();
    		   return null;
    	   
    	   }
    	   
    	   scanner.close();
    	   return secondProduct;
       }
       catch (Exception e) {
    	   scanner.close();
    	   return null;
       }
        
	}
	
	/**
	 * Processes the Tasks using the Task Object
	 * @param task task represents a specific task for the given file
	 * @return null
	 */
	
	private static Task processTask(String task) {
		
		Task newTask;
		
		String blank = task.trim();
		
		Scanner scanner = new Scanner(blank);
		
		scanner.useDelimiter(",");
		
		try {
			
			int taskId = scanner.nextInt();
			
			String state = scanner.next().trim();
			
			String title = scanner.next().trim();
			
			String type = scanner.next().trim();
			
			String creator = scanner.next().trim();
			
			String owner = scanner.next().trim();
			
			scanner.useDelimiter("\\r?\\n?[-]");
			
			String verified = scanner.next();
			
			verified = verified.substring(1);
			
			ArrayList<String> notes = new ArrayList<String>();
			
			while(scanner.hasNext()) {
				String exampleOne = scanner.next().replaceAll("\r", "");
				notes.add(exampleOne.trim());
			}
			
			if (notes.size() == 0) {
				scanner.close();
				return null;
			}
			
			scanner.close();
			
			newTask = new Task(taskId, state, title, type, creator, owner, verified, notes);
			
			return newTask;
			
		}
		
		catch (Exception e) {
			scanner.close();
			return null;
		}
        
	}
}
