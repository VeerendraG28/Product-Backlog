package edu.ncsu.csc216.product_backlog.model.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue;

/**
 * 
 * Includes the test methods for Command Class
 *
 */
class CommandTest {

	/**
	 * Set up before running the tests
	 */
	@BeforeEach
	void setUp() throws Exception {
		//Set Up For CommandTest
	}

	/**
	 * Constructor + Other Methods for Command Class
	 */
	@Test
	void testCommand() {
		// a valid constructor
		
		Command command = new Command(CommandValue.CLAIM, "Mike", "Safety");
		assertEquals(CommandValue.CLAIM, command.getCommand());
		assertEquals("Mike", command.getOwner());
		assertEquals("Safety", command.getNoteText());
				
		//testing when Command Value is null
		
		try {
			@SuppressWarnings("unused")
			Command secondExample = new Command(null, "Luke", "Football");
			fail();
		}
		catch(IllegalArgumentException e) {			
			//throws exception due to null
		}
		
		//testing when Command Value is CLAIM and owner is empty String
		try {
			@SuppressWarnings("unused")
			Command thirdExample = new Command(CommandValue.CLAIM, "", "Football Player");
			fail();
		}
		catch(IllegalArgumentException e) {
			//throws exception due to empty string
		}
		
		//testing when Command Value is CLAIM and owner is null
		try {
			@SuppressWarnings("unused")
			Command fourthExample = new Command(CommandValue.CLAIM, null, "NFL");
			fail();
		}
		catch(IllegalArgumentException e) {
			//throws exception due to null
		}
		
		//testing when Command Value is BACKLOG and has an owner
		try {
			@SuppressWarnings("unused")
			Command fifthExample = new Command(CommandValue.BACKLOG, "Cam", "Auburn");
			fail();
		}
		catch(IllegalArgumentException e) {
			//throws exception due to having an owner when not CLAIM
		}
		
		//testing when Command Value is VERIFY and has an owner
		try {
			@SuppressWarnings("unused")
			Command sixthExample = new Command(CommandValue.VERIFY, "Brian", "Soccer");
			fail();
		}
		catch(IllegalArgumentException e) {
			//throws exception due to having an owner when not CLAIM
		}
		
		//testing when Command Value is PROCESS and has an owner
		try {
			@SuppressWarnings("unused")
			Command seventhExample = new Command(CommandValue.PROCESS, "Burns", "Football");
			fail();
		}
		catch(IllegalArgumentException e) {
			//throws exception due to having an owner when not CLAIM
		}
		
		//testing when noteText is ""
		try {
			@SuppressWarnings("unused")
			Command eighthExample = new Command(CommandValue.CLAIM, "Bob", "");
			fail();
		}
		catch(IllegalArgumentException e) {
			//throws exception due to empty string
		}
		
		//testing when noteText is null
		try {
			@SuppressWarnings("unused")
			Command ninthExample = new Command(CommandValue.CLAIM, "Bob", null);
			fail();
		}
		catch(IllegalArgumentException e) {
			//throws exception due to null
		}
		
		//checks the state of BACKLOG
		Command backlogExample = new Command(CommandValue.BACKLOG, null, "Coach");
		assertEquals(CommandValue.BACKLOG, backlogExample.getCommand());
		assertEquals(null, backlogExample.getOwner());
		assertEquals("Coach", backlogExample.getNoteText());
		
		assertEquals(CommandValue.BACKLOG, CommandValue.valueOf("BACKLOG"));
		
		//checks the state of CLAIM
		Command claimExample = new Command(CommandValue.CLAIM, "CAM", "Coach");
		assertEquals(CommandValue.CLAIM, claimExample.getCommand());
		assertEquals("CAM", claimExample.getOwner());
		assertEquals("Coach", claimExample.getNoteText());
		
		assertEquals(CommandValue.CLAIM, CommandValue.valueOf("CLAIM"));
		
		//checks the state of PROCESS
		Command processExample = new Command(CommandValue.PROCESS, null, "Coach");
		assertEquals(CommandValue.PROCESS, processExample.getCommand());
		assertEquals(null, processExample.getOwner());
		assertEquals("Coach", processExample.getNoteText());
		
		assertEquals(CommandValue.PROCESS, CommandValue.valueOf("PROCESS"));
		
		//checks the state of VERIFY
		Command verifyExample = new Command(CommandValue.VERIFY, null, "Coach");
		assertEquals(CommandValue.VERIFY, verifyExample.getCommand());
		assertEquals(null, verifyExample.getOwner());
		assertEquals("Coach", verifyExample.getNoteText());
		
		assertEquals(CommandValue.VERIFY, CommandValue.valueOf("VERIFY"));
		
		//checks the state of COMPLETE
		Command completeExample = new Command(CommandValue.COMPLETE, null, "Coach");
		assertEquals(CommandValue.COMPLETE, completeExample.getCommand());
		assertEquals(null, completeExample.getOwner());
		assertEquals("Coach", completeExample.getNoteText());
		
		assertEquals(CommandValue.COMPLETE, CommandValue.valueOf("COMPLETE"));
		
		//checks the state of REJECT
		Command rejectExample = new Command(CommandValue.REJECT, null, "Coach");
		assertEquals(CommandValue.REJECT, rejectExample.getCommand());
		assertEquals(null, rejectExample.getOwner());
		assertEquals("Coach", rejectExample.getNoteText());
		
		assertEquals(CommandValue.REJECT, CommandValue.valueOf("REJECT"));
		
	}
}
