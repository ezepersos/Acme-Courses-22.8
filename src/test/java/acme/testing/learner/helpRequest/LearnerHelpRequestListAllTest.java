package acme.testing.learner.helpRequest;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class LearnerHelpRequestListAllTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/learner/help-request/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String status, final String statement, final String startTime, final String creationTime, final String endingTime, final String link) {
		super.signIn("learner1", "learner1");
		super.clickOnMenu("Learner", "List help requests");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, statement);
		super.checkColumnHasValue(recordIndex, 2, startTime);
		super.checkColumnHasValue(recordIndex, 3, creationTime);
		super.checkColumnHasValue(recordIndex, 4, endingTime);
		super.checkColumnHasValue(recordIndex, 5, link);

		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------

}
