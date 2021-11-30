package edu.ncsu.csc216.product_backlog.model.backlog;

import java.util.ArrayList;



import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.io.ProductsReader;
import edu.ncsu.csc216.product_backlog.model.io.ProductsWriter;
import edu.ncsu.csc216.product_backlog.model.product.Product;
import edu.ncsu.csc216.product_backlog.model.task.Task;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;

/**backlogmanager singleton class */
public class BacklogManager {
	
	/**productArrya is an array list of products*/
	private ArrayList<Product> products = new ArrayList<Product>();
	
	/**currentProduct is the currentProduct being selected or edited*/
	private Product currentProduct; 
	
	/**Backlog Manager implements singleton pattern design*/
	private static BacklogManager singleton;
	
	private BacklogManager(){
		//reponsible for clearing the products
		clearProducts();
	}
	
	/**
	 * Get instance of BacklogManager
	 * 
	 * @return singleton instance of a particular situation
	 */
	
	public static BacklogManager getInstance() {
		// if instance is null, set instance to new BacklogManager
		 if (singleton == null) {
				singleton = new BacklogManager();
			}
		 	//return original instance
			return singleton;
		
	}
	/**
	 * saveToFile call writeProductsToFile from ProductWriter and
	 * 
	 * @param fileName is file name being set
	 * */ 
	public void saveToFile (String fileName)  {
		//if current product is null, throw exception
		if (currentProduct == null) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		//save to file by using the write products to file method in products writer
		ProductsWriter.writeProductsToFile(fileName, products);
	}
	
	 /** 
	  * loadFromFile call readProductsFile from ProductReader to load files 
	 * @param fileName is file being used to load
	 */
	public void loadFromFile (String fileName)  {
		//using the read products file to load a file
		try {
			ArrayList<Product> load = ProductsReader.readProductsFile(fileName);
			
			products.addAll(load);
			
			currentProduct = load.get(0);
	
		}
		//else, catch an exception 
		catch (Exception e){
			throw new IllegalArgumentException("Not able to load file.");
		}
	
	}
	
	/**
	 * Load product to check if product name equals file
	 * @param loadName is file being loaded from
	 * */
	public void loadProduct(String loadName) {
				
		//boolean is set to false
		boolean appears = true;
		
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			//if product's product name equals loadName, the current Product equals the item of each products
			if (product.getProductName().equals(loadName)) {
				appears = true;
				currentProduct = products.get(i);
			}
		}
		//if appears is true, throw exception
		if(!appears) {
			throw new IllegalArgumentException("Product not available.");
		}
	}
		
	
	/**
	 * Check if two products are duplicates
	 * 
	 * @param productName is the name of a certain Product
	 */
	
	private void isDuplicateProduct(String productName) {
		for (int i = 0; i < products.size(); i++ ) {
			//if one products's product name equals another productName product name, throw exception
			if (products.get(i).getProductName().equalsIgnoreCase(productName)) {
				throw new IllegalArgumentException("Product is a duplicate.");
			}
		}	
	}
	
	/**
	 * Get 2d Dimensional String Array w/ Tasks
	 
	 * @return newTasksArray represents the tasks as an array

	 * */
	
	public String[][] getTasksAsArray(){ 
		//if current product is null, return null
		if(currentProduct == null) {
			return null;
		}
		//sets the columns and rows of the tasks array
		int columns = 4;
		int rows = currentProduct.getTasks().size();
		
		String[][] newTasksArray = new String[rows][columns];
		
		for(int i = 0; i < currentProduct.getTasks().size(); i++) {
			//position 0 gets the taskId
			newTasksArray[i][0] = Integer.toString(currentProduct.getTasks().get(i).getTaskId());
			//position 0 gets the stateName
			newTasksArray[i][1] = currentProduct.getTasks().get(i).getStateName();
			//position 0 gets the long task name
			newTasksArray[i][2] = currentProduct.getTasks().get(i).getTypeLongName();
			//position 0 gets the title
			newTasksArray[i][3] = currentProduct.getTasks().get(i).getTitle();
		}    
		
		return newTasksArray;
		
	}
	
	/**
	 * Gets A Task By taskId
	 * @param taskId is taskId to get a specific task
	 * @return currentProduct based on the certain Id
	 * */
	
	public Task getTaskById(int taskId) {
		return currentProduct.getTaskById(taskId);
	}	

	/**execute currentProduct through command
	 * 
	 * @param taskId is id to get task
	 * @param c is command being run through
	 * 
	 * */
	public void executeCommand (int taskId, Command c) {
		//execute command using getting task and update methods
		currentProduct.getTaskById(taskId).update(c);
		
	}
	
	/** 
	 * Deleting A Task By Id
	 * 
	 * @param taskId is the taskId of a particular task
	 * 
	 */
	
	public void deleteTaskById(int taskId) {
		//if current product is null, throw exception
		if (currentProduct == null) {
			throw new IllegalArgumentException("No product selected");
		}
		//delete task by id
		currentProduct.deleteTaskById(taskId);
	}
	
	/**
	 * Add a task to Product
	 * 
	 * @param title is title to get task
	 * @param type type of task
	 * @param creator title of task
	 * @param note note of task
	 * 
	 * */
	public void addTaskToProduct(String title, Type type, String creator, String note) {
		//if current product is null, throw exception
		if (currentProduct == null) {
			throw new IllegalArgumentException("No Product Selected.");
		}
		//else, add task with the certain fields
		currentProduct.addTask(title, type, creator, note);
	}
	
	/**
	 * Get's the name of a product
	 * @return currentProduct's get Product name
	 * 
	 * */
	public String getProductName() {
		//if the current product is null, return null
		if(currentProduct == null) {
			return null;
		}
		return currentProduct.getProductName();
		
	}
	
	/**
	 * 
	 * Get a list of products
	 * @return productList list of product
	 * 
	 * */
	public String[] getProductList() {
		//String array with a list of products
		String[] productList = new String[products.size()];
		
		//for loop to get product name of each product
		for (int i = 0; i < products.size(); i++) {
			productList[i] = products.get(i).getProductName();
		}
		//return list of product
		return productList;
	}
	
	/**
	 * clears CurrentProduct
	 */
	public void clearProducts() {
		//new list of products
		products = new ArrayList<Product>();
		//current product is null
		currentProduct = null;
	}
	
	/**
	 * Edits the product
	 * 
	 * @param updateName name update
	 *  
	 */
	
	public void editProduct(String updateName) {
		//if current product is null, throw exception
		if(currentProduct == null) {
			throw new IllegalArgumentException("No product selected.");
		}
		//check for duplicate product
		isDuplicateProduct(updateName);
		//set product name for current product
		currentProduct.setProductName(updateName);
	}
	
	/**
	 * Add products to with array of products
	 * 
	 * @param updateProduct is product that is updated
	 */
	
	public void addProduct(String updateProduct) {
		//check for duplicates
		isDuplicateProduct(updateProduct);
		//generate new product
		Product newProduct = new Product(updateProduct);
		//add product
		products.add(newProduct);
		//load product with pproduct with name
		loadProduct(newProduct.getProductName());
		
	}
	
	/**
	 * 
	 * Delete a product from the Product Array
	 * 
	 */
	
	public void deleteProduct() {
		//if current product is null, throw exception
		if (currentProduct == null) {
			throw new IllegalArgumentException("No Product selected.");
		}
		//remove product
		products.remove(currentProduct);
		//if size of product is null, set current product to null
		if (products.size() == 0) {
			currentProduct = null;
		}
		//else get first section of product
		else {
			currentProduct = products.get(0);
		}
		
	}
	
	/**
	 * Resets the Manager
	 */
	protected void resetManager() {
		//set instance to null
		singleton = null;
	}
}