package acme.testing.teacher.followUp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class TeacherFollowUpListAllTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/teacher/follow-up/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String automaticSequenceNumber, final String instantiationMoment, final String message, final String link) {
		super.signIn("teacher1", "teacher1");
		super.clickOnMenu("Teacher", "List follow-ups");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, automaticSequenceNumber);
		super.checkColumnHasValue(recordIndex, 1, instantiationMoment);
		super.checkColumnHasValue(recordIndex, 2, message);
		super.checkColumnHasValue(recordIndex, 3, link);

		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------

}
