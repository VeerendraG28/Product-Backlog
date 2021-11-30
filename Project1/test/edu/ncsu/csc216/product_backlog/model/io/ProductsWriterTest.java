/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//import static org.junit.jupiter.api.Assertions.*;

//import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.product.Product;
import edu.ncsu.csc216.product_backlog.model.task.Task;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;


/**
 * Test that prints the read products onto the specific file
 * @author Veerendra Gottiveeti
 *
 */
class ProductsWriterTest {
	
		
	/**
	 * Set Up Method
	 */
	@BeforeEach
	void setUp() throws Exception {
		//not needed
	}

	@Test
	void test() {
		ArrayList<Product> products = new ArrayList<Product>();
		Product product = new Product("Panther Nation");
		Task task = new Task(3, "Skydiving", Type.FEATURE, "Pouncey", "Express carts always choose the shortest line. If there are multiple shortest lines, an express cart chooses the one with the smallest index.");
		product.addTask(task);
		products.add(product);
		
		try (Scanner expScanner = new Scanner(new File("test-files/tasks1.txt"));
				 Scanner actScanner = new Scanner(new File("test-files/sameWriterFile.txt"));) {
				
				while (expScanner.hasNextLine()) {
					assertEquals(expScanner.nextLine(), actScanner.nextLine());
				}
				
				expScanner.close();
				actScanner.close();
			} catch (IOException e) {
				fail();
		}
	}

}
	
