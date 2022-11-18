package acme.testing.any.tutorial;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyTutorialListAllTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/tutorial/list-all-lab.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveLabTest(final int recordIndex,final String title, final String ticker, final String cost, final String tutorialType) {
		super.signIn("learner1", "learner1");
		super.clickOnMenu("Authenticated", "List lab tutorials");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, ticker);
		super.checkColumnHasValue(recordIndex, 2, cost);
		super.checkColumnHasValue(recordIndex, 3, tutorialType);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/tutorial/list-all-theory.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTheoryTest(final int recordIndex,final String title, final String ticker, final String cost, final String tutorialType) {
		super.signIn("learner1", "learner1");
		super.clickOnMenu("Authenticated", "List theory tutorials");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, ticker);
		super.checkColumnHasValue(recordIndex, 2, cost);
		super.checkColumnHasValue(recordIndex, 3, tutorialType);

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
