package acme.testing.any.blink;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyBlinkListAllTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/blink/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String instantiationMoment, final String caption, final String authorAlias, final String message, final String email) {
		super.signIn("learner1", "learner1");
		super.clickOnMenu("Authenticated", "List blinks");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, instantiationMoment);
		super.checkColumnHasValue(recordIndex, 1, caption);
		super.checkColumnHasValue(recordIndex, 2, authorAlias);
		super.checkColumnHasValue(recordIndex, 3, message);
		super.checkColumnHasValue(recordIndex, 4, email);

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
