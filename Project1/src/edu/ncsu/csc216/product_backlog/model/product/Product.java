package edu.ncsu.csc216.product_backlog.model.product;

import java.util.ArrayList;


import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.task.Task;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;


/**
 * 
 * Holds the context, abstract state, and the concrete states of the Product State
 * design pattern as part of the Product Backlog System
 * 
 * @author Veerendra Gottiveeti
 */

public class Product {

	/** String constant for the name of the product*/
	private String productName;
	/** int constant for a counter starting at 0*/
	private int counter = 0; 
	/** list of tasks using ArrayList*/
	private static ArrayList<Task> tasks;
	
	/**
	 * Constructor for the Product object. 
	 * @param productName productName is the name of the given product
	 */
	
	public Product(String productName) {
		
		setProductName(productName);
		
		tasks = new ArrayList<Task>();
		
		setTaskCounter();
		
	}
	
	/**
	 * Setting Method to count the Tasks
	 */
	private void setTaskCounter() {
		//field to store the size of the tasks' array
		int size = tasks.size();
		// if size is 0, set counter to 1
		if(size == 0) {
			counter = 1;
		}
		
		for (int i = 0; i < size; i++) {
			//if tasks of the taskId is greater than the existing counter, set counter to max + 1
			if (tasks.get(i).getTaskId() >= counter) {
				
				counter = tasks.get(i).getTaskId() + 1;
			}
		}
	
	}
	
	/**
	 * Generates an empty list
	 */
	@SuppressWarnings({ "static-access", "unused" })
	private void emptyList() {
		
		this.tasks = new ArrayList<Task>();
	}
			
	/**
	 * Getter method for the Product Name
	 * @return null
	 */
	 
	public String getProductName() {
		
		return productName;
	}
	 
	/**
	 * Setter method for the Product Name
	 * @param productName productName name of Product
	 */ 
	public void setProductName(String productName) {
		if(productName == null){
			throw new IllegalArgumentException("Invalid product name.");
		}
		if(productName.isEmpty()){
			throw new IllegalArgumentException("Invalid product name.");
		}
		
		this.productName = productName;
	
	}
		
	/**
	 * Adds the task to the product backlog
	 * 
	 * @param task task is the Task Object
	 */
	public void addTask(Task task) {
		//sets the size of the tasks Array
		int index = tasks.size();
		
		//if the size of tasks is 0, call the task counter and add the tasks with the proper fields
		if(index == 0) {
			
			tasks.add(index, task);
			
			setTaskCounter();
		}
		
		//else
		else {
			for (int i = 0; i < tasks.size(); i++) {
				
				Task apart = tasks.get(i);
				
				if(apart.getTaskId() == task.getTaskId()) {
					throw new IllegalArgumentException("Task cannot be added.");
				}
				
				else if (apart.getTaskId() > task.getTaskId()) {
					index = i;
					break;
				}
			}
		}
		
		tasks.add(index, task);
		
		setTaskCounter();
				
		}
	 
	/**
	 * Responsible for adding the tasks using the given inside parameters
	 * @param title title of the task 
	 * @param type type of the task
	 * @param creator creator of the task
	 * @param note note of the task
	 */
	public void addTask(String title, Type type, String creator, String note) {
		//Creates a new Task with the proper fields
		Task newTask = new Task(counter, title, type, creator, note);
		// add a task to the list of tasks
		tasks.add(newTask);
		//increase task counter
		setTaskCounter();

	}
	
	/**
	 * Getter method for the Tasks organized in a list format
	 * 
	 * @return tasks tasks with arrays
	 */
	
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	/**
	 * Getter method for the Task based on one's Id
	 * 
	 * @param taskId taskId represents the id for the task
	 * 
	 * @return null
	 */
	
	public Task getTaskById(int taskId) {
		
		//for loop to get task by its id
		for(int i = 0; i < tasks.size(); i++) {
			
			if(taskId == tasks.get(i).getTaskId()) {
				
				return tasks.get(i);
			}
		}
		
		//return null
		return null;
		
	}
	
	/**
	 * Responsible for executing the Command based on the id of the task
	 * @param taskId taskId is the unique Id for the task
	 * @param c command is set for the Command Object
	 */
	
	public void executeCommand(int taskId, Command c) {
		
		//for loop that gets the task by id and matches and update it based on the proper cycle
		for (int i = 0; i < tasks.size(); i++) {
			//matches the taskId
			if (tasks.get(i).getTaskId() == taskId) {
				//update with the same id of tasks
				tasks.get(i).update(c);
			}
		}
	}
	
	/**
	 * Responsible for deleting tasks by Id
	 * 
	 * @param taskId taskId represents the id for the task
	 */
	
	public void deleteTaskById(int taskId) {
		//for loop to iterate through the task size
		for (int i = 0; i < tasks.size();  i++) {
			//check if tasks matches a taskId of another
			if (tasks.get(i).getTaskId() == taskId) {
				//remove tasks and subtract counter by 1
				tasks.remove(i);
				counter--;
			}
		}
	}
	
}
