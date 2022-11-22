
package acme.forms;

import java.io.Serializable;
import java.util.Map;

import acme.entities.help_requests.HelpRequestStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {

	// Serialisation Identifier 
	private static final long				serialVersionUID	= 1L;

	// Attributes

	int										totalNumberOfTheoryTutorials;
	private Map<String, Double>				averageCostOfTheoryTutorialsByCurrency;
	private Map<String, Double>				deviationCostOfTheoryTutorialsByCurrency;
	private Map<String, Double>				minimumCostOfTheoryTutorialsByCurrency;
	private Map<String, Double>				maximumCostOfTheoryTutorialsByCurrency;
	int										totalNumberOfLabTutorials;
	private Map<String, Double>				averageCostOfLabTutorialsByCurrency;
	private Map<String, Double>				deviationCostOfLabTutorialsByCurrency;
	private Map<String, Double>				minimumCostOfLabTutorialsByCurrency;
	private Map<String, Double>				maximumCostOfLabTutorialsByCurrency;
	private Map<HelpRequestStatus, Integer>	totalNumberOfHelpRequestByStatus;
	private Map<HelpRequestStatus, Double>	averageHelpRequestsBudgetByStatus;
	private Map<HelpRequestStatus, Double>	deviationHelpRequestsBudgetByStatus;
	private Map<HelpRequestStatus, Double>	minimumHelpRequestsBudgetByStatus;
	private Map<HelpRequestStatus, Double>	maximumHelpRequestsBudgetByStatus;
	//Added
	private Double	ratioTutorialBlahblah;
	private Map<String, Double>	averageBlahBlahCostByCurrency;
	private Map<String, Double>	deviationBlahBlahCostByCurrency;
	private Map<String, Double>	minimumBlahBlahCostByCurrency;
	private Map<String, Double>	maximumBlahBlahCostByCurrency;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
}
