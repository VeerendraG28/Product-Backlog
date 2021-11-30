package edu.ncsu.csc216.product_backlog.model.product;

import static org.junit.jupiter.api.Assertions.*;

//import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.product_backlog.model.task.Task;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;

class ProductTest {
	
	@BeforeEach
	void setUp() throws Exception {
		//needed to pass Jenkins testing
	}

	/**
	 * Test Method for Product Constructor w/ Other Supporting Methods
	 */ 
	@Test
	void testProduct() {
		Product productValidExample = new Product("Football");
		assertEquals("Football", productValidExample.getProductName());
	}

	@Test
	void testGetProductName() {
		Product productName = new Product("Football");
		assertEquals("Football", productName.getProductName());
		
		try {
			Product invalidExampleTestProduct = new Product(null);
			invalidExampleTestProduct.getProductName();
			fail();
		} catch(Exception e) {
			//catch exception
		}
		
		try {
			Product invalidExampleTestProduct = new Product("");
			invalidExampleTestProduct.getProductName();
			fail();
		} catch(Exception e) {
			//catch exception
		}
		
	}

	@Test
	void testAddTaskTask() {
		
		Task tasksOne = new Task(4, "Win", Type.BUG, "Brian", "Football DE");
		Task tasksTwo = new Task(2, "Loss", Type.BUG, "Taylor", "Football RT");
		Task tasksThree = new Task(5, "Tie", Type.BUG, "Donte", "Football CB");
		Task tasksFour = new Task(7, "Superbowl", Type.BUG, "Christian", "Football RB");
		
		Product tasks = new Product("Panthers");
		assertEquals(0, tasks.getTasks().size());
		tasks.addTask(tasksFour);
		tasks.addTask(tasksThree);
		tasks.addTask(tasksTwo);
		tasks.addTask(tasksOne);
		
		assertEquals(5, tasks.getTasks().size());
		
	}

	@Test
	void testAddTaskStringTypeStringString() {
		
		Product tasks = new Product("Panthers");
		assertEquals(0, tasks.getTasks().size());
		tasks.addTask("a", Type.BUG, "Brian", "Football DE");
		assertEquals(1, tasks.getTasks().size());
	
	}

	@Test
	void testGetTasks() {
		Product tasks = new Product("Panthers");
		tasks.addTask("a", Type.BUG, "Brian", "Football DE");
		tasks.addTask("b", Type.FEATURE, "JEREMY", "Football S");
		tasks.addTask("c", Type.FEATURE, "KUECHLY", "Football LB");
		
		assertEquals(3, tasks.getTasks().size());
		
		assertEquals(tasks.getTasks(), tasks.getTasks());
	}

	@Test
	void testGetTaskById() {
		
		Product tasks = new Product("Panthers");
		tasks.addTask("a", Type.BUG, "Brian", "Football DE");
		tasks.addTask("b", Type.FEATURE, "JEREMY", "Football S");
		tasks.addTask("c", Type.FEATURE, "KUECHLY", "Football LB");
		
		assertEquals(3, tasks.getTasks().size());
		
		assertNull(tasks.getTaskById(0));
		
		assertEquals(tasks.getTaskById(1).getTitle(), "a");
		assertEquals(tasks.getTaskById(2).getCreator(), "JEREMY");

		
	}

	@Test
	void testExecuteCommand() {
		Product tasks = new Product("Panthers");
		tasks.addTask("a", Type.BUG, "Brian", "Football DE");
		tasks.addTask("b", Type.FEATURE, "JEREMY", "Football S");
		tasks.addTask("c", Type.FEATURE, "KUECHLY", "Football LB");
		
		assertEquals(3, tasks.getTasks().size());
		Command substitute = new Command(CommandValue.REJECT, null, "NFL");
		tasks.executeCommand(2, substitute);
		assertSame(tasks.getTaskById(2).getStateName(), Task.REJECTED_NAME);
	}

	@Test
	void testDeleteTaskById() {
		Product tasks = new Product("Panthers");
		tasks.addTask("a", Type.BUG, "Brian", "Football DE");
		tasks.addTask("b", Type.FEATURE, "JEREMY", "Football S");
		tasks.addTask("c", Type.FEATURE, "KUECHLY", "Football LB");
		
		assertEquals(3, tasks.getTasks().size());
		tasks.deleteTaskById(2);
		assertEquals(2, tasks.getTasks().size());
		
	}

}
