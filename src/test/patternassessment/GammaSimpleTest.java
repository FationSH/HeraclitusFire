package test.patternassessment;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datamodel.TableDetailedStatsElement;
import mainEngine.TableStatsMainEngine;
import patternassessment.PatternAssessmentResult;
import patternassessment.PatternGammaAssessment;

class GammaSimpleTest {
	private static TableStatsMainEngine tableStatsMainEngine; 
	private static ArrayList<TableDetailedStatsElement> inputTupleCollection;
	private static int numRows;
	private static PatternGammaAssessment gammaAssessment;
	private static double ALPHA = 0.001;
	private static PatternAssessmentResult result;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		tableStatsMainEngine = new TableStatsMainEngine("resources/Atlas", null);
		inputTupleCollection = new ArrayList<TableDetailedStatsElement>();
		ArrayList<String> header = new ArrayList<String>();
		numRows = tableStatsMainEngine.loadData("resources/Atlas/results/tables_DetailedStats.tsv", "\t", true, 22, header, inputTupleCollection);
		gammaAssessment = new PatternGammaAssessment(inputTupleCollection, "Atlas", "resources/test/GammaTest", ALPHA);
		result = gammaAssessment.constructResult();
		gammaAssessment.computeContingencyTable(result);
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	final void testConstructResult() {
		assertEquals(2,result.getContingencyNumColumns());
		assertEquals(2,result.getContingencyNumRows());
	}

	@Test
	final void testComputeContingencyTable() {
		
		int[][] contingencyTable = result.getContingencyTable();
		assertEquals(contingencyTable[0][0], 13);
		assertEquals(contingencyTable[0][1], 61);
		assertEquals(contingencyTable[1][0], 2);
		assertEquals(contingencyTable[1][1], 12);
	}

	@Test
	final void testDecideIfPatternHolds() {
		assertTrue(gammaAssessment.decideIfPatternHolds(result));
	}

	  
	  @Test 
	  final void testAssessPattern() { 
		  assertEquals(numRows,89, "Atlas tables are 88 + 1 line header");
		  assertEquals(inputTupleCollection.size(),88);
		  assertTrue(gammaAssessment.assessPattern()); 
	  }
	 

}
