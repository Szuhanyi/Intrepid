package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import model.Individual;

class IndividualTest {

	@Test
	void testIndividual() {	
		// how do you test a constructor in a model class ?				
		//test if id generation works ? ?		
		int id = 0;
		Individual ind = new Individual();
		
		assertEquals(id,ind.getId());
		
		ind = new Individual();
		assertEquals(id+1,ind.getId());
		
	}

	@Test
	void testIndividualIndividual() {
		// Or is this for the individual... ?? what not
	}

	@Test
	void testLessThen() {
		//  this method compares two values, but there objective values.. 
		// a < b , only if every value is less then that one.. 		
		
		// get two specific solution from the function's and 
		// then compare those , I guess ???
		
		double [] x = { 1.1, 1.2, 1.2 };
		double [] y = { 1.2, 1.3, 1.4 };
		
		Individual ind1 = new Individual();
		Individual ind2 = new Individual();
		ind1.setObjectiveValues(x);
		ind2.setObjectiveValues(y);
		assertEquals(true, ind1.lessThen(ind2));	

		x = new double [] { 1.1, 1.2, 1.2 };
		y = new double [] { 1.1, 1.2, 1.2 };		
		ind1 = new Individual();
		ind2 = new Individual();
		ind1.setObjectiveValues(x);
		ind2.setObjectiveValues(y);
		assertEquals(false, ind1.lessThen(ind2));	

		
		x = new double [] { 1.1, 1.2, 1.2 };
		y = new double [] { 1.0, 1.1, 1.1 };		
		ind1 = new Individual();
		ind2 = new Individual();
		ind1.setObjectiveValues(x);
		ind2.setObjectiveValues(y);
		assertEquals(true, ind2.lessThen(ind1));
	}

	@Test
	void testGetDominatedSetSize() {
		// not going that well I see.. non.. IT
		Individual ind = new Individual();
		assertEquals(0,ind.getDominatedSetSize());
		
		
	}

	@Test
	void testAddDominatedSolution() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveDominatedSolution() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDominatedSolution() {
		fail("Not yet implemented");
	}

	@Test
	void testIncrementDominationCount() {
		fail("Not yet implemented");
	}

	@Test
	void testDecrementDominationCount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDominationCount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetId() {
		fail("Not yet implemented");
	}

	@Test
	void testSetId() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDecisionVariables() {
		fail("Not yet implemented");
	}

	@Test
	void testSetDecisionVariables() {
		fail("Not yet implemented");
	}

	@Test
	void testGetObjectiveValues() {
		fail("Not yet implemented");
	}

	@Test
	void testSetObjectiveValues() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDistance() {
		fail("Not yet implemented");
	}

	@Test
	void testSetDistance() {
		fail("Not yet implemented");
	}

	@Test
	void testGetFitness() {
		fail("Not yet implemented");
	}

	@Test
	void testSetFitness() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRank() {
		fail("Not yet implemented");
	}

	@Test
	void testSetRank() {
		fail("Not yet implemented");
	}

}
