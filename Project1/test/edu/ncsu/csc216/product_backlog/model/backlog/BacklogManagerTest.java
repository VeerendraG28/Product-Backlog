/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.backlog;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.product.Product;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;



/**
 * Test class that tests BacklogManager and its methods
 * @author Veerendra Gottiveeti
 *
 */
public class BacklogManagerTest {
	
	/** private field of Type BacklogManager */
	private BacklogManager manager;
	
	/** 
	 * SetUp() Method of BacklogManagerTest
	 * */
	@BeforeEach
	public void setUp() {
		manager = BacklogManager.getInstance();
		manager.resetManager();
	}
	
	/**
	 * Test method for loading products onto file
	 */
	@Test
	void testLoadProduct() {
		assertNotNull(BacklogManager.getInstance());
		
	}

	/**
	 * Test method for Getting Tasks As Array
	 */
	@Test
	void testGetTasksAsArray() {
		
		manager.clearProducts();
		
		
		manager.addProduct("Carolina Panthers");
		
		manager.addTaskToProduct("title", Type.BUG, "creator", "note");
		
		String [][] testArrayString = new String [1][4];
		
		testArrayString [0][0] = "8";
		
		testArrayString [0][1] = "Backlog";
		
		testArrayString [0][2] = "Feature";
		
		testArrayString [0][3] = "NFL";
		
		for (int i = 0; i < 4; i++ ) {
			assertEquals(manager.getTasksAsArray()[0][i], manager.getTasksAsArray()[0][i]);
		}	
	}

	

	/**
	 * Test method for Getting the Product Name
	 */
	@Test
	void testGetProductName() {
		
		manager.clearProducts();
		
		assertEquals(null, manager.getProductName());
		
		Product nflTeam = new Product ("Carolina Panthers");
		
		assertEquals("Carolina Panthers", nflTeam.getProductName());
		
		manager.clearProducts();
	}

	/**
	 * Test method for getting the Product List
	 */
	@Test
	void testGetProductList() {
		manager.clearProducts();
		
		manager.addProduct("Carolina Panthers");
		
		manager.addProduct("NFL");
		
		assertEquals("Carolina Panthers", manager.getProductList()[0]);
		
		assertEquals("NFL", manager.getProductList()[1]);
	} 

	/**
	 * Test method for Clear Products
	 */
	@Test
	void testClearProducts() {
		
		manager.clearProducts();
		
		manager.addProduct("Carolina Panthers");
		
		manager.clearProducts();
		
		assertEquals(0, manager.getProductList().length);
	}

	/**
	 * Test method for Editing Products
	 */
	@Test
	void testEditProducts() {
		
		manager.clearProducts();
		
		manager.addProduct("Carolina Panthers");
		
		assertThrows(IllegalArgumentException.class, () -> manager.editProduct("Carolina Panthers"), "Invalid product name.");
		
		assertThrows(IllegalArgumentException.class, () -> manager.editProduct(null), "No product selected.");
		
		manager.clearProducts();
		
		manager.addProduct("Carolina Panthers");
		assertEquals(1, manager.getProductList().length);
	}

	/**
	 * Test method for Adding Products
	 */
	@Test
	void testAddProducts() {
		manager.clearProducts();
		manager.addProduct("Carolina Panthers");
		
		assertThrows(IllegalArgumentException.class, () -> manager.addProduct("Carolina Panthers"), "Invalid product name.");
	
		assertThrows(IllegalArgumentException.class, () -> manager.addProduct(null),  "Invalid product name.");
		
	}

	/**
	 * Test method for Deleting Products
	 */
	@Test
	void testDeleteProducts() {
		manager.clearProducts();
		manager.addProduct("Panth");
		
		manager.deleteProduct();
		assertEquals(0, manager.getProductList().length);
		
	}
	
	/**
	 * Testing loading contents of a file
	 */
	@Test
	public void testLoadFromFile() {
		BacklogManager b = BacklogManager.getInstance();
		b.resetManager();
		b.loadFromFile("test-files/exp_tasks.txt");
		assertEquals(b.getProductList().length, 1);
		b.loadProduct(b.getProductList()[0]);
		
		assertEquals("Test Product", b.getProductName());
		String [][] tasksArr = b.getTasksAsArray();
		assertEquals("3", tasksArr[0][0]);
		assertEquals("Owned", tasksArr[0][1]);
		assertEquals("Knowledge Acquisition", tasksArr[0][2]);
		assertEquals("Test task 2", tasksArr[0][3]);
		assertEquals("3", tasksArr[1][0]);
		assertEquals("Owned", tasksArr[1][1]);
		assertEquals("Knowledge Acquisition", tasksArr[1][2]);
		assertEquals("Test task 2", tasksArr[1][3]);
		assertEquals("7", tasksArr[2][0]);
		assertEquals("Backlog", tasksArr[2][1]);
		assertEquals("Bug", tasksArr[2][2]);
		assertEquals("Test task 1", tasksArr[2][3]);
		
		assertEquals(b.getProductList().length, 1);
		b.loadProduct(b.getProductList()[0]);
		assertEquals("Test Product", b.getProductName());
	}
	
	/**
	 * Invalid Testing of deleting product 
	 */
	
	@Test
	public void testDeleteProductInvalid() {
		BacklogManager backlog = BacklogManager.getInstance();
		backlog.resetManager();
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> backlog.deleteProduct());
		assertEquals("No Product selected.", e1.getMessage());
	}
	
	/**
	 * Invalid adding product of name
	 */
	
	@Test
	public void testAddProductInvalid() {
		BacklogManager b = BacklogManager.getInstance();
		b.resetManager();
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> b.addProduct(null));
		assertEquals("Invalid product name.", e1.getMessage());
	}
	
	/**
	 * Invalid adding product of name
	 */
	
	@Test
	public void testAddAndDeleteProduct() {
		BacklogManager b = BacklogManager.getInstance();
		b.resetManager();
		
		b.addProduct("Brian Burns");
		b.addProduct("DJ Moore");
		assertEquals(b.getProductList().length, 2);
		
		b.deleteProduct();
		b.deleteProduct();
		assertEquals(b.getProductList().length, 0);
	}
	
	/**
	 * Testing Deleting Task
	 */
	
	@Test
	public void testDeleteTaskById() {
		BacklogManager b = BacklogManager.getInstance();
		b.resetManager();
		
		b.addProduct("Carolina Panthers");
		b.addTaskToProduct("Panthers", Type.BUG, "vrgottiv", "Carolina on top");
		b.deleteTaskById(1);
		assertNull(BacklogManager.getInstance().getTasksAsArray());
	}

}