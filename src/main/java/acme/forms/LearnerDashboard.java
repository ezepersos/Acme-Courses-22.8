package acme.forms;


import java.io.Serializable;
import java.util.Map;

import acme.entities.help_requests.HelpRequestStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LearnerDashboard implements Serializable {

	// Serialisation Identifier 
	private static final long				serialVersionUID	= 1L;

	// Attributes
	private Map<HelpRequestStatus, Integer>	totalNumberOfHelpRequestByStatus;
	private Map<HelpRequestStatus, Double>	averageHelpRequestsBudgetByStatusGroupedByCurrency;
	private Map<HelpRequestStatus, Double>	deviationHelpRequestsBudgetByStatusGroupedByCurrency;
	private Map<HelpRequestStatus, Double>	minimumHelpRequestsBudgetByStatusGroupedByCurrency;
	private Map<HelpRequestStatus, Double>	maximumHelpRequestsBudgetByStatusGroupedByCurrency;


}