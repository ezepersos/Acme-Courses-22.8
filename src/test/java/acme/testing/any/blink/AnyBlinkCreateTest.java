package acme.testing.any.blink;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyBlinkCreateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/blink/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest (final int recordIndex, final String instantiationMoment, final String caption, final String authorAlias, final String message, final String email) {
		
		super.signIn("teacher1", "teacher1");
		super.clickOnMenu("Authenticated", "List blinks");
		super.checkListingExists();
		super.clickOnButton("Create blink");
		super.checkFormExists();
		super.fillInputBoxIn("caption", message);
		super.fillInputBoxIn("authorAlias", authorAlias);
		super.fillInputBoxIn("message", message);
		super.fillInputBoxIn("email", email);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create blink");
		
		super.checkNotErrorsExist();
		
		super.navigateHome();
		super.clickOnMenu("Authenticated", "List blinks");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 1, caption);
		super.checkColumnHasValue(recordIndex, 2, message);
		super.checkColumnHasValue(recordIndex, 3, email);
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/blink/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String instantiationMoment, final String caption, final String authorAlias, final String message, final String email ) {

		super.signIn("teacher1", "teacher1");
		super.clickOnMenu("Authenticated", "List blinks");
		super.checkListingExists();
		super.clickOnButton("Create blink");
		super.checkFormExists();
		super.fillInputBoxIn("caption", message);
		super.fillInputBoxIn("authorAlias", authorAlias);
		super.fillInputBoxIn("message", message);
		super.fillInputBoxIn("email", email);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create blink");
		
		super.checkNotErrorsExist();
		
		super.navigateHome();
		super.clickOnMenu("Authenticated", "List blinks");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 1, caption);
		super.checkColumnHasValue(recordIndex, 2, message);
		super.checkColumnHasValue(recordIndex, 3, email);

		super.checkErrorsExist();

		super.signOut();
	}
}
