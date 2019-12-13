package patternassessment.tablepatterns;

import java.util.ArrayList;

import datamodel.TableDetailedStatsElement;


public class PatternAssessmentFactory {

	public PatternAssessmentTemplateMethod createPatternAssessmentTester(
			PatternAssessmentTypesEnum testType,
			ArrayList<TableDetailedStatsElement> pInputTupleCollection,
			String projectName,
			String pOutputFolderWithPatterns,
			String globalAppendLog,
			double alpha) {

		switch(testType) {
		case GAMMA: return new GammaPatternLKVAssessment(pInputTupleCollection,projectName, pOutputFolderWithPatterns, globalAppendLog, alpha);

		default: return null; 
		}
		//return null;
	}
}