/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.product.Product;

/**
 * Class that reads the Products From Files
 * @author Veerendra Gottiveeti
 *
 */
class ProductsReaderTest {
	
	/** test file */
	private final String validTestFile = "test-files/exp_tasks.txt";

	/**
	 * Set Up Method that throws exception
	 */

	@Test
	@SuppressWarnings("static-access")
	void testValidProductReading() {
 
		ProductsReader reader = new ProductsReader();
		
		ArrayList<Product> tester = assertDoesNotThrow(() -> reader.readProductsFile(validTestFile), "Should not throw");
				
		Product pantherSimulation = tester.get(0);
		
		assertEquals("Test Product", pantherSimulation.getProductName());
	}
	

	/**
	 * Set Up Method that throws exception
	 */

	@Test
	@SuppressWarnings("static-access")
	public void testReadProductsFile2() {
 
		ProductsReader reader = new ProductsReader();
		
		ArrayList<Product> tester = assertDoesNotThrow(() -> reader.readProductsFile(validTestFile), 
				"Should not throw exception.");
		
		Product test = tester.get(0);
		
		assertEquals("Test Product", test.getProductName());
		
	}
	

}
