package acme.testing.teacher.followUp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class TeacherFollowUpCreateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/teacher/follow-up/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest (final int recordIndex,final String automaticSequenceNumber, final String instantiationMoment, final String message, final String link ) {
		
		super.signIn("teacher1", "teacher1");
		super.clickOnMenu("Teacher", "List help requests");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.clickOnButton("Create follow up");
		super.checkFormExists();
		super.fillInputBoxIn("message", message);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create follow up");
		
		super.checkNotErrorsExist();
		
		super.navigateHome();
		super.clickOnMenu("Teacher", "List follow-ups");
		super.checkListingExists();
		super.sortListing(1, "desc");
		super.checkColumnHasValue(recordIndex, 2, message);
		super.checkColumnHasValue(recordIndex, 3, link);
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/teacher/follow-up/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex,final String automaticSequenceNumber, final String instantiationMoment, final String message, final String link ) {

		super.signIn("teacher1", "teacher1");
		super.clickOnMenu("Teacher", "List help requests");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.clickOnButton("Create follow up");
		super.checkFormExists();

		super.fillInputBoxIn("message", message);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create follow up");

		super.checkErrorsExist();

		super.signOut();
	}
}
