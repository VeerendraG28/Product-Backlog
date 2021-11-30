/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.task;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;


/**
 * 
 * Includes the test methods for Task Class
 * @author Veerendra Gottiveeti
 *
 */
class TaskTest {
	
	/** Task ID */
	private static final int TASKID = 12;
	
	/** Task State */
	private static final String STATE = "Backlog";
	
	/** Task Title */
	private static final String TITLE = "Carolina Panthers";
		
	/** Task Creator */
	private static final String CREATOR = "Rhule";
	
	/** Task Owner */
	private static final String OWNER = "Tepper";
	
	/** Task Verified */
	private static final String VERIFIED = "false";
	
	/** Task ArrayList Notes */
	private static final ArrayList<String> NOTES = new ArrayList<String>();
	
	/** Task constant of unowned*/
	private static final String UNOWNED = "unowned";
	
	/** Task constant of notes*/
	private static final String NOTE = "Carolina Panthers";
	
	/** ArrayList of Strings of example NOTE*/
	ArrayList<String> noteArray = new ArrayList<String>();
	
	
	/**
	 * Test method for first Constructor
	 */
	@Test
	void testTaskIntStringTypeStringString() {
		
		Task newTask = new Task(TASKID, TITLE, Type.FEATURE, CREATOR, NOTE);
		
		assertAll("Task", 
				() -> assertEquals(TASKID, newTask.getTaskId(), "Invalid task information."),
				() -> assertEquals(TITLE, newTask.getTitle(), "Invalid task information."),
				() -> assertEquals(Type.FEATURE, newTask.getType(), "Invalid task information."),
				() -> assertEquals(CREATOR, newTask.getCreator(), "Invalid task information."),
				() -> assertEquals(UNOWNED, newTask.getOwner(), "Invalid task information."),
				() -> assertFalse(newTask.isVerified(), "Invalid task information."));
		
		Task newTask2 = new Task(TASKID, TITLE, Type.BUG, CREATOR, NOTE);
		
		assertAll("Task", 
				() -> assertEquals(TASKID, newTask2.getTaskId(), "Invalid task information."),
				() -> assertEquals(TITLE, newTask2.getTitle(), "Invalid task information."),
				() -> assertEquals(Type.BUG, newTask2.getType(), "Invalid task information."),
				() -> assertEquals(CREATOR, newTask2.getCreator(), "Invalid task information."),
				() -> assertEquals(UNOWNED, newTask2.getOwner(), "Invalid task information."),
				() -> assertFalse(newTask2.isVerified(), "Invalid task information."));
		
		Task newTask3 = new Task(TASKID, TITLE, Type.TECHNICAL_WORK, CREATOR, NOTE);
		
		assertAll("Task", 
				() -> assertEquals(TASKID, newTask3.getTaskId(), "Invalid task information."),
				() -> assertEquals(TITLE, newTask3.getTitle(), "Invalid task information."),
				() -> assertEquals(Type.TECHNICAL_WORK, newTask3.getType(), "Invalid task information."),
				() -> assertEquals(CREATOR, newTask3.getCreator(), "Invalid task information."),
				() -> assertEquals(UNOWNED, newTask3.getOwner(), "Invalid task information."),
				() -> assertFalse(newTask3.isVerified(), "Invalid task information."));
		
		Task newTask4 = new Task(TASKID, TITLE, Type.KNOWLEDGE_ACQUISITION, CREATOR, NOTE);
		
		assertAll("Task", 
				() -> assertEquals(TASKID, newTask4.getTaskId(), "Invalid task information."),
				() -> assertEquals(TITLE, newTask4.getTitle(), "Invalid task information."),
				() -> assertEquals(Type.KNOWLEDGE_ACQUISITION, newTask4.getType(), "Invalid task information."),
				() -> assertEquals(CREATOR, newTask4.getCreator(), "Invalid task information."),
				() -> assertEquals(UNOWNED, newTask4.getOwner(), "Invalid task information."),
				() -> assertFalse(newTask4.isVerified(), "Invalid task information."));
		
		assertThrows(IllegalArgumentException.class, 
				() -> new Task(TASKID, TITLE, null, CREATOR, NOTE));
		assertThrows(IllegalArgumentException.class, 
				() -> new Task(TASKID, TITLE, null, CREATOR, ""));
		assertThrows(IllegalArgumentException.class, 
				() -> new Task(TASKID, TITLE, null, CREATOR, null));
		
		
		//noteArray.add("[Backlog] constructorTest"); 
		//Task t1 = assertDoesNotThrow (
			//() -> new Task(taskId, "Backlog", title, "F", creator, unowned, "false", note),  
			//"Should not throw exception");
		//assertEquals("* 3,Backlog,Implement classes,F,cebunch,unowned,false\n- [Backlog] test", t1.toString());
		
	
	}

	/** 
	 * Test method for second Constructor
	 */
	@Test
	void testTaskIntStringStringStringStringStringStringArrayListOfString() {
		
		String noteIntroduction = "Hi";
		String noteTwo = " My name is";
		ArrayList<String> notesAsAArray = new ArrayList<String>();
		NOTES.add(noteIntroduction);
		NOTES.add(noteTwo);
		
		Task validExample = new Task(8, Task.DONE_NAME, "Special Carts", Task.T_FEATURE, "hrc", "owner", "true", notesAsAArray);
		assertEquals(8, validExample.getTaskId());
		assertEquals(Task.DONE_NAME, validExample.getStateName());
		assertEquals("Special Carts", validExample.getTitle());
		assertEquals(Task.T_FEATURE, validExample.getTypeShortName());
		assertEquals("hrc", validExample.getCreator());
		assertEquals("owner", validExample.getOwner());
		assertTrue(validExample.isVerified());
		assertEquals(notesAsAArray, validExample.getNotes());
		
		Task validExample2 = new Task(8, Task.DONE_NAME, "Special Carts", Task.T_BUG, "hrc", "owned", "true", notesAsAArray);
		assertEquals(8, validExample2.getTaskId());
		assertEquals(Task.DONE_NAME, validExample2.getStateName());
		assertEquals("Special Carts", validExample2.getTitle());
		assertEquals(Task.T_BUG, validExample2.getTypeShortName());
		assertEquals("hrc", validExample2.getCreator());
		assertEquals("owned", validExample2.getOwner());
		assertTrue(validExample2.isVerified());
		assertEquals(notesAsAArray, validExample2.getNotes());

		Task validExample3 = new Task(8, Task.DONE_NAME, "Special Carts", Task.T_TECHNICAL_WORK, "hrc", "owned", "true", notesAsAArray);
		assertEquals(8, validExample3.getTaskId());
		assertEquals(Task.DONE_NAME, validExample3.getStateName());
		assertEquals("Special Carts", validExample3.getTitle());
		assertEquals(Task.T_TECHNICAL_WORK, validExample3.getTypeShortName());
		assertEquals("hrc", validExample3.getCreator());
		assertEquals("owned", validExample3.getOwner());
		assertTrue(validExample3.isVerified());
		assertEquals(notesAsAArray, validExample3.getNotes());
		
		Task validExample4 = new Task(8, Task.DONE_NAME, "Special Carts", Task.T_KNOWLEDGE_ACQUISITION, "hrc", "owned", "false", notesAsAArray);
		assertEquals(8, validExample4.getTaskId());
		assertEquals(Task.DONE_NAME, validExample4.getStateName());
		assertEquals("Special Carts", validExample4.getTitle());
		assertEquals(Task.T_KNOWLEDGE_ACQUISITION, validExample4.getTypeShortName());
		assertEquals("hrc", validExample4.getCreator());
		assertEquals("owned", validExample4.getOwner());
		assertFalse(validExample4.isVerified());
		assertEquals(notesAsAArray, validExample4.getNotes());
	}

	/** 
	 * Tests the method for setting an invalid task Id
	 */
	@Test
	public void testSetTaskIdInvalid() {
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Task(0, TITLE, Type.FEATURE, CREATOR, NOTE));
		assertEquals("Invalid task information.", e1.getMessage());
	}
	
	/** 
	 * Testing method for Rejected States that Fail
	 */
	@Test
	public void testingRejectedStates() {
		
		assertThrows(IllegalArgumentException.class, 
				() -> new Task(TASKID, "Rejected", null, "F", CREATOR, "unowned", "false", NOTES));
		assertThrows(UnsupportedOperationException.class, 
				() -> new Task(TASKID, "Done", TITLE, "F", CREATOR, OWNER, "false", NOTES));
		assertThrows(IllegalArgumentException.class, 
				() -> new Task(TASKID, "PROCESSING", TITLE, "F", CREATOR, null, "false", NOTES));
		assertThrows(IllegalArgumentException.class, 
				() -> new Task(TASKID, "Rejected", null, "F", CREATOR, "", "false", NOTES));
		assertThrows(IllegalArgumentException.class, 
				() -> new Task(TASKID, "Rejected", null, "F", CREATOR, "unowned", "false", NOTES));
		assertThrows(IllegalArgumentException.class, 
				() -> new Task(TASKID, "Rejected", TITLE, "A", CREATOR, "unowned", "false", NOTES));
		assertThrows(UnsupportedOperationException.class, 
				() -> new Task(TASKID, "Done", TITLE, "F", CREATOR, "unowned", "true", NOTES));
		assertThrows(IllegalArgumentException.class, 
				() -> new Task(TASKID, "hello", TITLE, "F", CREATOR, "unowned", "false", NOTES));
		assertThrows(UnsupportedOperationException.class, 
				() -> new Task(TASKID, "Processing", TITLE, "F", CREATOR, "unowned", "false", NOTES));
		
	}
	

	/** 
	 * Tests the method for setting an invalid task Id
	 */
	@Test
	public void testSetTypeInvalid() {
		
		Exception exceptionOne = assertThrows(IllegalArgumentException.class, () -> new Task(6, TITLE, null, CREATOR, NOTE));
		assertEquals("Invalid task information.", exceptionOne.getMessage());
	}
	
	/** 
	 * Tests the method for setting an invalid title Id
	 */
	@Test
	public void testSetTitleInvalid() {
		
		Exception exceptionOne = assertThrows(IllegalArgumentException.class, () -> new Task(TASKID, "", Type.FEATURE, CREATOR, NOTE));
		assertEquals("Invalid task information.", exceptionOne.getMessage());
		Exception exceptionTwo = assertThrows(IllegalArgumentException.class, () -> new Task(TASKID, null, Type.FEATURE, CREATOR, NOTE));
		assertEquals("Invalid task information.", exceptionTwo.getMessage());
	}
	
	/** 
	 * Tests the method for setting an invalid creator Id
	 */
	@Test
	public void testSetCreatorInvalid() {
		
		Exception exceptionOne = assertThrows(IllegalArgumentException.class, () -> new Task(TASKID, TITLE, Type.FEATURE, "", NOTE));
		assertEquals("Invalid task information.", exceptionOne.getMessage());
		Exception exceptionTwo = assertThrows(IllegalArgumentException.class, () -> new Task(TASKID, TITLE, Type.FEATURE, null, NOTE));
		assertEquals("Invalid task information.", exceptionTwo.getMessage());
	}
	
	/** 
	 * Tests the method for setting an invalid owner
	 */
	@Test
	public void testSetOwnerInvalid() {
		
		Exception exceptionTwo = assertThrows(UnsupportedOperationException.class, () -> new Task(TASKID, "Processing", TITLE, Task.T_FEATURE, CREATOR, "unowned", VERIFIED, NOTES));
		assertEquals("Invalid task information.", exceptionTwo.getMessage());
		
	}
	
	/** 
	 * Tests the method for setting an invalid verified
	 */
	@Test
	public void testSetVerifiedInvalid() {
		
		Exception exceptionOne = assertThrows(IllegalArgumentException.class, () -> new Task(TASKID, STATE, TITLE, Task.T_FEATURE, CREATOR, OWNER, "weew", NOTES));
		assertEquals("Invalid task information.", exceptionOne.getMessage());
		
		Exception exceptionTwo = assertThrows(IllegalArgumentException.class, () -> new Task(TASKID, STATE, TITLE, Task.T_FEATURE, CREATOR, OWNER, "ewe", NOTES));
		assertEquals("Invalid task information.", exceptionTwo.getMessage());
	}
	
	/** 
	 * Tests the method for setting an invalid notes
	 */
	@Test
	public void testSetNotesInvalid() {
		
		Exception exceptionOne = assertThrows(IllegalArgumentException.class, () -> new Task(TASKID, STATE, TITLE, Task.T_FEATURE, CREATOR, OWNER, VERIFIED, null));
		assertEquals("Invalid task information.", exceptionOne.getMessage());

	}
	
	/** 
	 * Tests the State interface and the valid pathways
	 */
	@Test
	public void testUpdateStateValidOne() {
		
		Task newTaskSet = new Task(TASKID, TITLE, Type.TECHNICAL_WORK, CREATOR, NOTE);
		
		Command command = new Command(CommandValue.CLAIM, "owned", "Rhule claimed this task");
		newTaskSet.update(command);
		assertEquals("owned", newTaskSet.getOwner());
		Task task = new Task(TASKID, TITLE, Type.TECHNICAL_WORK, CREATOR, NOTE);
		Command commandOne = new Command(CommandValue.CLAIM, "Rhule", "Rhule claimed this task.");
		task.update(commandOne);
		Command commandTwo = new Command(CommandValue.PROCESS, null, "Working on task.");
		task.update(commandTwo);
		Command commandThree = new Command(CommandValue.VERIFY, null, "Verify the task.");
		task.update(commandThree);
		Command commandFour = new Command(CommandValue.COMPLETE, null, "Complete the task.");
		task.update(commandFour);
		Command commandFive = new Command(CommandValue.BACKLOG, null, "Sent back to Backlog.");
		task.update(commandFive);
		
		String[] word = assertDoesNotThrow(() -> new String[task.getNotes().size()], 
				"Should not throw an exception.");
		word[0] = "[Backlog] Carolina Panthers";
		word[1] = "[Owned] Rhule claimed this task.";
		word[2] = "[Processing] Working on task.";
		word[3] = "[Verifying] Verify the task.";
		word[4] = "[Done] Complete the task.";
		word[5] = "[Backlog] Sent back to Backlog.";
		
		String [] testArray = task.getNotesArray();
		for(int i = 0; i < word.length; i++) {
			assertEquals(word[i], testArray[i]);
		}
	}
	
	/** 
	 * Tests the State interface and the valid pathways
	 */
	@Test
	public void testUpdateStateValidTwo() {
		
		Task task = new Task(TASKID, TITLE, Type.TECHNICAL_WORK, CREATOR, NOTE);
		Command command = new Command(CommandValue.REJECT, null, "This task was rejected");
		task.update(command);
		
		String[] word = assertDoesNotThrow(() -> new String[task.getNotes().size()], 
				"Should not throw an exception.");
		word[0] = "[Backlog] Carolina Panthers";
		word[1] = "[Rejected] This task was rejected";
		
		String [] testArray = task.getNotesArray();
		for(int i = 0; i < word.length; i++) {
			assertEquals(word[i], testArray[i]);
		}
	}
	
	/** 
	 * Tests the State interface and the valid pathways
	 */
	@Test
	public void testUpdateStateValidThree() {
		
		Task task = new Task(TASKID, TITLE, Type.TECHNICAL_WORK, CREATOR, NOTE);
		Command command = new Command(CommandValue.REJECT, null, "This task was rejected.");
		task.update(command);
		
		String[] word = assertDoesNotThrow(() -> new String[task.getNotes().size()], 
				"Should not throw an exception.");
		word[0] = "[Backlog] Carolina Panthers";
		word[1] = "[Rejected] This task was rejected.";
		
		String [] testArray = task.getNotesArray();
		for(int i = 0; i < word.length; i++) {
			assertEquals(word[i], testArray[i]);
		}
	}
	
	/** 
	 * Tests the State interface and the valid pathways
	 */
	@Test
	public void testUpdateStateValidsFour() {
		
		Task task = new Task(TASKID, TITLE, Type.BUG, CREATOR, NOTE);
		
		Command command = new Command(CommandValue.CLAIM, "Rhule", "Rhule claimed this task.");
		task.update(command);
		Command commandTwo = new Command(CommandValue.PROCESS, null, "Working on this task.");
		task.update(commandTwo);
		Command commandThree = new Command(CommandValue.VERIFY, null, "Sent for Verificiation.");
		task.update(commandThree);
		Command commandFour = new Command(CommandValue.PROCESS, null, "Working On Task.");
		task.update(commandFour);
		
		String[] word = assertDoesNotThrow(() -> new String[task.getNotes().size()], 
				"Should not throw an exception.");
		word[0] = "[Backlog] Carolina Panthers";
		word[1] = "[Owned] Rhule claimed this task.";
		word[2] = "[Processing] Working on this task.";
		word[3] = "[Verifying] Sent for Verificiation.";
		word[4] = "[Processing] Working On Task.";
		
		String [] testArray = task.getNotesArray();
		for(int i = 0; i < word.length; i++) {
			assertEquals(word[i], testArray[i]);
		}
	}
	
	/** 
	 * Tests the State interface and the valid pathways
	 */
	@Test
	public void testUpdateStateValidFive() {
		
		Task task = new Task(TASKID, TITLE, Type.KNOWLEDGE_ACQUISITION, CREATOR, NOTE);
		
		Command command = new Command(CommandValue.CLAIM, "Rhule", "Rhule claimed this task.");
		task.update(command);
		Command commandTwo = new Command(CommandValue.PROCESS, null, "Working on this task.");
		task.update(commandTwo);
		Command commandThree = new Command(CommandValue.PROCESS, null, "Working on this task.");
		task.update(commandThree);
		Command commandFour = new Command(CommandValue.COMPLETE, null, "Completed.");
		task.update(commandFour);
		
		String[] word = assertDoesNotThrow(() -> new String[task.getNotes().size()], 
				"Should not throw an exception.");
		assertEquals("* 12,Done,Carolina Panthers,KA,Rhule,Rhule,false\n- [Backlog] Carolina Panthers\n- [Owned] Rhule claimed this task.\n- [Processing] Working on this task.\n- [Processing] Working on this task.\n- [Done] Completed.", task.toString());
		word[0] = "[Backlog] Carolina Panthers";
		word[1] = "[Owned] Rhule claimed this task.";
		word[2] = "[Processing] Working on this task.";
		word[3] = "[Processing] Working on this task.";
		word[4] = "[Done] Completed.";
		
		String [] testArray = task.getNotesArray();
		for(int i = 0; i < word.length; i++) {
			assertEquals(word[i], testArray[i]);
		}
	}
	
	/** 
	 * Tests the State interface and the valid pathways
	 */
	@Test
	public void testUpdateStateValidSix() {
		
		Task task = new Task(TASKID, TITLE, Type.TECHNICAL_WORK, CREATOR, NOTE);
		
		Command command = new Command(CommandValue.CLAIM, "Rhule", "Rhule claimed this task.");
		task.update(command);
		Command commandTwo = new Command(CommandValue.PROCESS, null, "Working on this task.");
		task.update(commandTwo);
		Command commandThree = new Command(CommandValue.VERIFY, null, "Attempt to verify.");
		task.update(commandThree);
		Command commandFour = new Command(CommandValue.PROCESS, null, "Working on task.");
		task.update(commandFour);
		
		assertEquals("* 12,Processing,Carolina Panthers,TW,Rhule,Rhule,false\n- [Backlog] Carolina Panthers\n- [Owned] Rhule claimed this task.\n- [Processing] Working on this task.\n- [Verifying] Attempt to verify.\n- [Processing] Working on task.", task.toString());
		
		String[] word = assertDoesNotThrow(() -> new String[task.getNotes().size()], 
				"Should not throw an exception.");
		word[0] = "[Backlog] Carolina Panthers";
		word[1] = "[Owned] Rhule claimed this task.";
		word[2] = "[Processing] Working on this task.";
		word[3] = "[Verifying] Attempt to verify.";
		word[4] = "[Processing] Working on task.";
		
		String [] testArray = task.getNotesArray();
		for(int i = 0; i < word.length; i++) {
			assertEquals(word[i], testArray[i]);
		}
	}
	
	/** 
	 * Tests the State interface and the valid pathways
	 */
	@Test
	public void testUpdateStateValidSeven() {
		
		Task task = new Task(TASKID, TITLE, Type.TECHNICAL_WORK, CREATOR, NOTE);
		
		Command command = new Command(CommandValue.CLAIM, "Rhule", "Rhule claimed this task.");
		task.update(command);
		Command commandTwo = new Command(CommandValue.BACKLOG, null, "Sent to the backlog.");
		task.update(commandTwo);
		
		assertEquals("* 12,Backlog,Carolina Panthers,TW,Rhule,null,false\n- [Backlog] Carolina Panthers\n- [Owned] Rhule claimed this task.\n- [Backlog] Sent to the backlog.", task.toString());
		
		String[] word = assertDoesNotThrow(() -> new String[task.getNotes().size()], 
				"Should not throw an exception.");
		word[0] = "[Backlog] Carolina Panthers";
		word[1] = "[Owned] Rhule claimed this task.";
		word[2] = "[Backlog] Sent to the backlog.";
		
		String [] testArray = task.getNotesArray();
		for(int i = 0; i < word.length; i++) {
			assertEquals(word[i], testArray[i]);
		}
	}
	
	/** 
	 * Tests the State interface and the valid pathways
	 */
	@Test
	public void testUpdateStateValidEight() {
		
		Task task = new Task(TASKID, TITLE, Type.TECHNICAL_WORK, CREATOR, NOTE);
		
		Command command = new Command(CommandValue.CLAIM, "Rhule", "Rhule claimed this task.");
		task.update(command);
		Command commandTwo = new Command(CommandValue.REJECT, null, "Rhule rejected this task.");
		task.update(commandTwo);
		Command commandThree = new Command(CommandValue.BACKLOG, null, "Sent to the backlog.");
		task.update(commandThree);
		
		assertEquals("* 12,Backlog,Carolina Panthers,TW,Rhule,Rhule,false\n- [Backlog] Carolina Panthers\n- [Owned] Rhule claimed this task.\n- [Rejected] Rhule rejected this task.\n- [Backlog] Sent to the backlog.", task.toString());
		
		String[] word = assertDoesNotThrow(() -> new String[task.getNotes().size()], 
				"Should not throw an exception.");
		word[0] = "[Backlog] Carolina Panthers";
		word[1] = "[Owned] Rhule claimed this task.";
		word[2] = "[Rejected] Rhule rejected this task.";
		word[3] = "[Backlog] Sent to the backlog.";
		
		String [] testArray = task.getNotesArray();
		for(int i = 0; i < word.length; i++) {
			assertEquals(word[i], testArray[i]);
		}
	}
	
}

