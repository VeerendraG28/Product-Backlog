package edu.ncsu.csc216.product_backlog.model.task;

import java.util.ArrayList;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue;

/**
 * 
 * A Task Class represents a task tracked by the system. Task has six inner classes that 
 * each implement TaskState and each concrete state handles updating when given a command.
 * 
 * @author Veerendra Gottiveeti
 *
 */

public class Task {
	
	/** ID number of the TaskID */
	private int taskId;
	/** Title of the Task */
	private String title;
	/** Creator of the Task */
	private String creator;
	/** Owner of the Task */
	private String owner;
	/** Verification of the Task */  
	private boolean isVerified; 
	/** ArrayList Object of the notes presented */
	private ArrayList<String> notes = new ArrayList<String>();
	
	/** Backlog State Object for ScrumBacklog FSM */
	public final TaskState backlogState = new BacklogState();
	/** Owned State Object for ScrumBacklog FSM */
	public final TaskState ownedState = new OwnedState();
	/** Processing State Object for ScrumBacklog FSM */
	public final TaskState processingState = new ProcessingState();
	/** Verifying State Object for ScrumBacklog FSM */
	public final TaskState verifyingState = new VerifyingState();
	/** Done State Object for ScrumBacklog FSM */
	public final TaskState doneState = new DoneState();
	/** Rejected State Object for ScrumBacklog FSM */
	public final TaskState rejectedState = new RejectedState();
		
	//Constants for State Names
	
	/** A constant string for the Backlog State */
	public static final String BACKLOG_NAME = "Backlog";
	/** A constant string for the Owned State */
	public static final String OWNED_NAME = "Owned";
	/** A constant string for the Processing State */
	public static final String PROCESSING_NAME = "Processing";
	/** A constant string for the Verifying State */
	public static final String VERIFYING_NAME = "Verifying";
	/** A Constant string for the Done State */
	public static final String DONE_NAME = "Done";
	/** A constant string for the Rejected State */
	public static final String REJECTED_NAME = "Rejected";
	
	//Constants for Type Long Names
	
	/** A constant String of the long Feature Type */
	public static final String FEATURE_NAME = "Feature";
	/** A constant String of the long Bug Type */
	public static final String BUG_NAME = "Bug";
	/** A constant String of the long Technical Work Type */
	public static final String TECHNICAL_WORK_NAME = "Technical Work";
	/** A constant String of the long Knowledge Acquisition Type */
	public static final String KNOWLEDGE_ACQUISITION_NAME = "Knowledge Acquisition";
	
	//Constants for Type Short Names
	
	/** A constant String of the short Feature Type */
	public static final String T_FEATURE = "F";
	/** A constant String of the short Bug Type */
	public static final String T_BUG = "B";
	/** A constant String of the short Technical Work Type */
	public static final String T_TECHNICAL_WORK = "TW";
	/** A constant String of the short Knowledge Acquisition Type */
	public static final String T_KNOWLEDGE_ACQUISITION = "KA";
	
	/** A constant String for a Task that is unowned */
	public static final String UNOWNED = "unowned";
	
	/** Type enumerator for the Type Object including FEATURE, BUG, TECHNICAL_WORK, KNOWLEDGE_ACQUISITION */
	public enum Type { FEATURE, BUG, TECHNICAL_WORK, KNOWLEDGE_ACQUISITION }
	
	/** Type of task of type Type */
	private Type type;
	/** Type of currentState of type String */
	private TaskState currentState;
	
	/**
	 * First Constructor for the Task object. 
	 * @param taskId taskId for each unique and individual task
	 * @param title title of the task
	 * @param type type of the task
	 * @param creator creator of the task
	 * @param note note of each unique task
	 */
	
	public Task(int taskId, String title, Type type, String creator, String note) {
		
		setTaskId(taskId);
		
		currentState = backlogState;
				
		setTitle(title);
		
		setType(type);
		
		setCreator(creator);
		
		this.notes = new ArrayList<String>();
		
		addNoteToList(note);
		
		setOwner(UNOWNED);
		
		setVerified("false");
		
		
	}
	
	/**
	 * Second Constructor for the Task object with values of all fields. 
	 * @param taskId taskId for each unique and individual task
	 * @param state state of each task
	 * @param title title of the task
	 * @param type type of the task
	 * @param creator creator of the task
	 * @param owner owner of each unique task
	 * @param verified verified of each unique task
	 * @param notes notes of task in an ArrayList Form
	 */

	public Task(int taskId, String state, String title, String type, String creator, String owner, String verified, ArrayList<String> notes) {
		
		setTaskId(taskId);
		
		setTitle(title);
		
		setTypeFromString(type);
		
		setCreator(creator);
		
		setOwner(owner);
		
		setVerified(verified);
		
		setNotes(notes);
		
		setState(state);
						
	}
	 
	/**
	 * Setter for getting the taskID
	 * @param taskId Id of a task
	 * 
	 * @throws IllegalArgumentException if taskId is less than or equal to 0
	 */
	private void setTaskId(int taskId) {
		
		//if task is less than or equal to 0, throw exception
		if(taskId <= 0) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		//Else, the task will be set
		this.taskId = taskId;
	}
	
	/** 
	 * Setter for getting the title
	 * 
	 * @param title title of a task
	 */
	private void setTitle(String title) {
		
		//if title is null, throw exception
		if(title == null) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		//if title is empty throw exception
		if("".equals(title)) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		//Else, set the title
		this.title = title;
	}
	
	/**
	 * setter for getting the type
	 * 
	 * @param type type of a task
	 */
	private void setType(Type type) {
		
		//if type is null throw exception
		if(type == null) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		
		//Else, sets the type	
		this.type = type;
	
	}
	
	/**
	 * setter for getting the creator
	 * @param creator creator of a task
	 * @throws IllegalArgumentException if creator is null or empty
	 */
	private void setCreator(String creator) {
		
		//if creator is null, throw exception
		if(creator == null) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		//if creator is an empty string, throw exception
		if(creator.isEmpty()) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		
		this.creator = creator;
	}
	
	/**
	 *Setter for getting the owner of a task
	 * 
	 * @param owner owner of a task
	 */
	private void setOwner(String owner) {
		//the owner is set
		this.owner = owner;
	}
	
	/**
	 * setter for getting the verified method
	 * @param verified verification of a task
	 */
	private void setVerified(String verified) {

		
		if("true".equals(verified)) {
			this.isVerified = true;
		}
		
		else if("false".equals(verified)) {
			this.isVerified = false;
		}
		else {
			throw new IllegalArgumentException("Invalid task information.");
		}
		
	}
	
	/**
	 * Sets the Notes Type from an ArrayList
	 * 
	 * @param notes notes declaring each individual notes with arraylists
	 * 
	 */
	
	private void setNotes(ArrayList<String> notes) {		
		
		//if the notes are null, throw exception
		if (notes == null) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		//Else, sets the notes
		this.notes = notes;
	}
	
	/**
	 * Add the note to list based on the given note String
	 * 
	 * @param note note declaring each individual note with integer
	 * 
	 * @return size of notes to add
	 */
	
	public int addNoteToList(String note) {
		
		if (note == null || "".equals(note)) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		
		int sizeOfNotes = notes.size();
		//if note is null or empty, throw exception
		//format of the string with the concatenated string with getstatename with brackets w/ a note
		String stateString = "[" + currentState.getStateName() + "] " + note;
		//add the version of stateString onto the notes List
		notes.add(stateString);
		sizeOfNotes++;
		
		return sizeOfNotes;
	
	}
	
	/**
	 * Getter method for the Task's ID number.
	 * 
	 * @return taskId id of the task
	 */
	
	public int getTaskId() {
	
		return taskId;

	}
	
	/**
	 * Getter method for the Task's state name.
	 * 
	 * @return current state name
	 */
	
	public String getStateName() {
		return currentState.getStateName();
	}
	
	/**
	 * Sets the State Type using a String parameter and each of the given states.
	 * 
	 * @param state state of each individual task
	 */
	
	private void setState(String state) {
		
		//if state equals "Backlog" execute:
		if (state.equals(BACKLOG_NAME)){
			//if is Verified is true and owner is not Unowned, throw exception
			if (isVerified() || !owner.equals(UNOWNED)) {
				throw new UnsupportedOperationException("Invalid task information.");
			}
			//sets the backlogState
			this.currentState = backlogState;
		}
		//if state equals "Owned" execute:
		else if (state.equals(OWNED_NAME)){
			//if is Verified is true and owner is not Unowned, throw exception
			if (isVerified() || owner.equals(UNOWNED)) {
				throw new UnsupportedOperationException("Invalid task information.");
			}
			//sets the ownedState
			this.currentState = ownedState;
		}
		//if state equals "Processing" execute:
		else if (state.equals(PROCESSING_NAME)){
			//if is Verified is true and owner is not Unowned, throw exception
			if (isVerified() || owner.equals(UNOWNED)) {
				throw new UnsupportedOperationException("Invalid task information.");
			}
			//sets the processingState
			this.currentState = processingState;
		}
		//if state equals "Verifying" execute:
		else if (state.equals(VERIFYING_NAME)){
			//if is Verified is true and owner is not Unowned, throw exception
			if (isVerified() || owner.equals(UNOWNED)) {
				throw new UnsupportedOperationException("Invalid task information.");
			}
			//sets the verifyingState
			this.currentState = verifyingState;
		}
		//if state equals "Done" execute:
		else if (state.equals(DONE_NAME)){
			if (owner.equals(UNOWNED)) {
				throw new UnsupportedOperationException("Invalid task information.");
			}
			//if is Verified is true and owner is not Unowned, throw exception
			if (isVerified() && type == Type.KNOWLEDGE_ACQUISITION || !isVerified() && type != Type.KNOWLEDGE_ACQUISITION) {
				throw new UnsupportedOperationException("Invalid task information.");
			}
			//sets the doneState
			this.currentState = doneState;
		}
		//if state equals "Rejected" execute:
		else if (state.equals(REJECTED_NAME)){
			//if is Verified is true and owner is not Unowned, throw exception
			if (isVerified() || !owner.equals(UNOWNED)) {
				throw new UnsupportedOperationException("Invalid task information.");
			}
			//sets the rejectedState
			this.currentState = rejectedState;
		}
		//else throw argument exception
		else {
			throw new IllegalArgumentException("Invalid task information");
	}
		
	}
	
	/**
	 * Sets the State Type using a String parameter with each of the Short State Cases.
	 * 
	 * @param typeConstant typeConstant of each individual task
	 */
	
	private void setTypeFromString(String typeConstant) {
					
		switch(typeConstant) {
		//case for Feature setting
		case T_FEATURE:
			this.type = Type.FEATURE;
			break;
		//case for Bug setting
		case T_BUG:
			this.type = Type.BUG;
			break;
		//case for Technical work Setting	
		case T_TECHNICAL_WORK:
			this.type = Type.TECHNICAL_WORK;
			break;
		//case for Knowledge acquistion setting
		case T_KNOWLEDGE_ACQUISITION:
			this.type = Type.KNOWLEDGE_ACQUISITION;
			break;
		//else throw exception
		default:
			throw new IllegalArgumentException("Invalid task information.");
		}
	}
	
	/**
	 * Type Object for the Task's type.
	 * 
	 * @return the type of Task
	 */
	 
	public Type getType() {
		return type;
	}
	
	/**
	 * Getter method for the Task's type short name.
	 * 
	 * @return the short names of the types: feature, bug, technical work, knowledge acquisition
	 */
	
	public String getTypeShortName() {
		//switch statement for type short names 
		switch(type) {
		//case Feature to return short version
		case FEATURE:
			return T_FEATURE;
		//case Bug to return short version
		case BUG:
			return T_BUG;
		//case Technical Work to return short version
		case TECHNICAL_WORK:
			return T_TECHNICAL_WORK;
		//case Knowledge Acquisition to return short version
		case KNOWLEDGE_ACQUISITION:
			return T_KNOWLEDGE_ACQUISITION;
		//else, throw exception
		default:
			throw new IllegalArgumentException("Invalid task information.");
		}
	}
	
	/**
	 * Getter method for the Task's type long name.
	 * 
	 * @return the long names of the types: feature, bug, technical work, knowledge acquisition
	 */
	
	public String getTypeLongName() {
		
		switch(type) {
		
		case FEATURE:
			return FEATURE_NAME;
		
		case BUG:
			return BUG_NAME;
			
		case TECHNICAL_WORK:
			return TECHNICAL_WORK_NAME;
			
		case KNOWLEDGE_ACQUISITION:
			return KNOWLEDGE_ACQUISITION_NAME;
		
		default:
			throw new IllegalArgumentException("Invalid task information.");
		}	
	}
	
	/**
	 * Getter method for the Task's owner.
	 * @return null
	 */
	public String getOwner() {
		return owner;	
	}
	
	/**
	 * Getter method for the Task's title.
	 * @return null
	 */
	public String getTitle() {
		return title;	
	}
	
	/**
	 * Getter method for the Task's creator.
	 * @return creator
	 */
	public String getCreator() {
		return creator;	
	}
	
	/**
	 * To check if the tasks are verified with certain parameters
	 * @return false 
	 */
	public boolean isVerified() {
	
		return isVerified;
		
	}
	
	/** 
	 * Getter method for the Task's notes using Arraylist.
	 * @return null
	 */
	public ArrayList<String> getNotes() {
		return notes;
	}
	
	/**
	 * Getter method for the Task's notes list.
	 * @return list
	 */
	public String getNotesList() {

		String list = "";
		for (int i = 0; i < notes.size(); i++) {
			if(i < notes.size() - 1) {
				list += "- " + notes.get(i) + "\n";
			}
			else {
				list += "- " + notes.get(i);
			}
		}
		
		return list;
	}
	
	/**
	 * Representation of how the string is layed out.
	 * @return null
	 */
	public String toString() {
	
		return "* " + getTaskId() + "," + getStateName() + "," + getTitle() + "," + getTypeShortName() + "," + getCreator() + "," + getOwner() + "," + isVerified() + "\n" + getNotesList();
		
	}
	
	/**
	 * The updating using the specific Command object 
	 * @param c command of the individual task
	 */
	public void update(Command c) {
		currentState.updateState(c);
	}
	
	/**
	 * Returns a string array for Notes within each Task
	 * @return null
	 */
	public String[] getNotesArray() {
		
		String[] notesArray = new String[notes.size()];
		
		for(int i = 0; i < notes.size(); i++) {
			notesArray[i] = notes.get(i);
		}
		
		return notesArray;
		
	}
	

	/**
	 * Interface for states in the Task State Pattern.  All 
	 * concrete task states must implement the TaskState interface.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface TaskState {
		
		/**
		 * Update the Task based on the given Command
		 * An UnsupportedOperationException is thrown if the Command is not a
		 * is not a valid action for the given state.  
		 * @param c Command describing the action that will update the Task
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		 void updateState(Command c);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		
		 String getStateName();
	
	}
	
	/**
	 * 
	 * A Concrete class that presents the Backlog State of the Product Backlog
	 * @author Veerendra Gottiveeti
	 *
	 */
	private class BacklogState implements TaskState {
		
		/**
		 * Constructor for Backlog State
		 */ 
		
		private BacklogState() {
			getStateName();
		}
		
		/**
		 * Needed to update for the backlog state
		 * @param c command to update the specific state
		 */
		
		public void updateState(Command c) {
			if(c.getCommand() == CommandValue.CLAIM) {
				currentState = ownedState;
				setOwner(c.getOwner());
				addNoteToList(c.getNoteText());
			}
			
			else if(c.getCommand() == CommandValue.REJECT) {
				currentState = rejectedState;
				addNoteToList(c.getNoteText());
			}
			
			else {
				throw new UnsupportedOperationException("Invalid transition.");
			}
		}
		
		/**
		 * Presents the name of the String.
		 * @return the name of the String.
		 */
		
		public String getStateName() {
			return BACKLOG_NAME;
		}
	}
	
	/**
	 * 
	 * Concrete class that presents the Owned State of the Product Backlog
	 * @author Veerendra Gottiveeti
	 *
	 */
	
	private class OwnedState implements TaskState {
		
		/**
		 * Constructor for Owned State
		 */
		
		private OwnedState() {
			getStateName();
		}
		
		/**
		 * Needed to update for the owned state
		 * @param c command to update the specific state
		 */
		public void updateState(Command c) {
			if(c.getCommand() == CommandValue.PROCESS) {
				currentState = processingState;
				addNoteToList(c.getNoteText());
				
			}
			
			if(c.getCommand() == CommandValue.REJECT) {
				currentState = rejectedState;
				addNoteToList(c.getNoteText());
			}
			
			if(c.getCommand() == CommandValue.BACKLOG) {
				owner = null;
				currentState = backlogState;
				addNoteToList(c.getNoteText());
			}
			
			if (c.getCommand() != CommandValue.PROCESS && c.getCommand() != CommandValue.REJECT && c.getCommand() != CommandValue.BACKLOG) {
				throw new UnsupportedOperationException("Invalid transition.");
			}
			
		}
		
		/**
		 * Presents the name of the String.
		 * @return the name of the String.
		 */
		
		public String getStateName() {
			return Task.OWNED_NAME;
		}
	}
	
	/**
	 * 
	 * Concrete class that presents the Processing State of the Product Backlog
	 * @author Veerendra Gottiveeti
	 *
	 */
	private class ProcessingState implements TaskState {
		
		/**
		 * Constructor for Processing State
		 */
		
		private ProcessingState() {
			getStateName();
		}
		
		/**
		 * Needed to update for the processing state
		 * @param c command to update the specific state
		 */
		public void updateState(Command c) {
			
			if(c.getCommand() == CommandValue.PROCESS) {
				currentState = processingState;
				addNoteToList(c.getNoteText());
			}
			
			else if(c.getCommand() == CommandValue.VERIFY && type != Type.KNOWLEDGE_ACQUISITION) {
				currentState = verifyingState;
				addNoteToList(c.getNoteText());
			}
			
			else if(c.getCommand() == CommandValue.COMPLETE && type == Type.KNOWLEDGE_ACQUISITION) {
				currentState = doneState;
				addNoteToList(c.getNoteText());
			}
			
			else if(c.getCommand() == CommandValue.BACKLOG) {
				owner = null;
				currentState = backlogState;
				addNoteToList(c.getNoteText());
			}
			
			else {
				throw new UnsupportedOperationException("Invalid transition.");
			}
			
		}
		
		/**
		 * Presents the name of the String.
		 * @return the name of the String.
		 */
		
		public String getStateName() {
			return PROCESSING_NAME;
		}
	}
	
	/**
	 * 
	 * Concrete class that presents the Verifying State of the Product Backlog
	 * @author Veerendra Gottiveeti
	 *
	 */
	
	private class VerifyingState implements TaskState {
		
		/**
		 * Constructor for Verifying State
		 */
		
		private VerifyingState() {
			getStateName();
		}
		
		/**
		 * Needed to update for the verifying state
		 * @param c command to update the specific state
		 */
		public void updateState(Command c) {
			
			if(c.getCommand() == CommandValue.COMPLETE) {
				setVerified("true");
				currentState = doneState;
				addNoteToList(c.getNoteText());
			}
			
			else if(c.getCommand() == CommandValue.PROCESS) {
				currentState = processingState;
				addNoteToList(c.getNoteText());
			}
			
			else if (c.getCommand() != CommandValue.PROCESS && c.getCommand() != CommandValue.COMPLETE) {
				throw new UnsupportedOperationException("Invalid transition.");
			}
	
		}
		
		/**
		 * Presents the name of the String.
		 * @return the name of the String.
		 */
		
		public String getStateName() {
			return VERIFYING_NAME;
		}
	}
	
	/**
	 * 
	 * Concrete class that presents the Done State of the Product Backlog
	 * @author Veerendra Gottiveeti
	 *
	 */
	
	private class DoneState implements TaskState {
		
		/**
		 * Constructor for Done State
		 */
		
		private DoneState() {
			getStateName();
		}
		
		/**
		 * Needed to update for the done state
		 * @param c command to update the specific state
		 */
		public void updateState(Command c) {
			if(c.getCommand() == CommandValue.PROCESS) {
				setVerified("false");
				currentState = processingState;
				addNoteToList(c.getNoteText());
			}
			
			else if(c.getCommand() == CommandValue.BACKLOG) {
				owner = null;
				setVerified("false");
				currentState = backlogState;
				addNoteToList(c.getNoteText());
			}
			
			else {
				throw new UnsupportedOperationException("Invalid transition.");
			}
		
		}
		
		/**
		 * Presents the name of the String.
		 * @return the name of the String.
		 */
		@Override
		public String getStateName() {
			return Task.DONE_NAME;
		}
	}
	
	/**
	 * 
	 * Concrete class that presents the Rejected State of the Product Backlog
	 * @author Veerendra Gottiveeti
	 *
	 */
	
	private class RejectedState implements TaskState {
		
		/**
		 * Constructor for Rejected State
		 */
		
		private RejectedState() {
			getStateName();
		}
		
		/**
		 * Needed to update for the rejected state
		 * @param c command to update the specific state
		 */
		public void updateState(Command c) {
			if(c.getCommand() == CommandValue.BACKLOG) {
				currentState = backlogState;
				addNoteToList(c.getNoteText());
			}
			else {
				throw new UnsupportedOperationException("Invalid transition.");
			}
		}
		
		/**
		 * Presents the name of the String.
		 * @return the name of the String.
		 */
		
		public String getStateName() {
			return REJECTED_NAME;
		}
	}
}
