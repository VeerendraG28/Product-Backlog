package edu.ncsu.csc216.product_backlog.model.command;

/**
 * 
 * The Command Class Creates Objects that Encapsulates user actions or inputs that 
 * cause transitions to State of a task to update. 
 * 
 * @author Veerendra Gottiveeti
 *
 */

public class Command {

	/**Owner of the given command */
	private String owner;
	/**Note Text given with each command*/
	private String note;
	/** value of the each command*/
	private CommandValue c;
	
	/**String message for when error occurs during command*/
	private static final String COMMAND_ERROR_MESSAGE = "Invalid command.";
	
	
	/**
	 * 
	 * Serves as a ENUM class for a variety of fields including
	 * Backlog Claim Process, Verify, Complete, Reject
	 */
	public enum CommandValue { 
		
		/**
		 *
		 *Different fields:
		 *Backlog
		 *Claim
		 *Process
		 *Verify
		 *Complete
		 *Reject
		 *
		 */
		BACKLOG, CLAIM, PROCESS, VERIFY, COMPLETE, REJECT 
	}
	
	/**
	 * Constructor for the Command object. 
	 * @param c The CommandValue enumerator that will update the state of the 
	 * Task Item
	 * @param owner owner of the given command
	 * @param noteText note given during the command
	 */ 
	
	public Command(CommandValue c, String owner, String noteText) {
		
		this.c = c;
		
		//if c is null, throw error message
		if(c == null) {
			throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
		}
				
		this.owner = owner; 

		if (c == CommandValue.CLAIM) {
			//if owner is null, throw error message
			if(owner == null) {
				throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
			}
			//if owner is empty, throw error message
			if(owner.isEmpty()) {
				throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
			}
		}
			
		if(owner != null && c != CommandValue.CLAIM) {
			throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
		}
		
		this.note = noteText;
		
		//if noteText is null or empty, throw error message
		if(noteText == null || "".equals(noteText)) {
			throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
		}
				
			//if owner is null, throw error message
		if(owner != null && c != CommandValue.CLAIM) {
				throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
		}
		
				
		//switch statement that initializes each of the given states
		switch(c) {
			//case and settor for backlog
			case BACKLOG:
				this.c = CommandValue.BACKLOG;
				break;
			//case and settor for claim
			case CLAIM:
				this.c = CommandValue.CLAIM;
				break;
			//case and settor for process
			case PROCESS:
				this.c = CommandValue.PROCESS;
				break;
			//case and settor for verify
			case VERIFY:
				this.c = CommandValue.VERIFY;
				break;
			//case and settor for complete
			case COMPLETE:
				this.c = CommandValue.COMPLETE;
				break;
			//case and settor for reject
			case REJECT:
				this.c = CommandValue.REJECT;
				break;
			//else, throw exception
			default:
				throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
		}
	}
	
	/**
	 * Getter method that returns the value from the Command Object
	 * @return c command
	 */
	
	public CommandValue getCommand() {
		return this.c;
	}
	
	/**
	 * Getter method that returns the value from the Note Object
	 * @return note note of Task
	 */
	
	public String getNoteText() {
		return this.note;
		
	}
	
	/**
	 * Getter method that returns the value from the Owner Object
	 * @return owner of Task
	 */
	public String getOwner() {
		return this.owner;
		
	}
}
