/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.help_requests.HelpRequestStatus;
import acme.entities.tutorials.TutorialType;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "totalNumberOfTheoryTutorials", "averageCostOfTheoryTutorialsByCurrency", "deviationCostOfTheoryTutorialsByCurrency",
			"minimumCostOfTheoryTutorialsByCurrency", "maximumCostOfTheoryTutorialsByCurrency", "totalNumberOfLabTutorials",
			"averageCostOfLabTutorialsByCurrency", "deviationCostOfLabTutorialsByCurrency", "minimumCostOfLabTutorialsByCurrency", "maximumCostOfLabTutorialsByCurrency",
			"totalNumberOfHelpRequestByStatus", "averageHelpRequestsBudgetByStatus", "deviationHelpRequestsBudgetByStatus", "minimumHelpRequestsBudgetByStatus", "maximumHelpRequestsBudgetByStatus");
	}
	
	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;

		final AdministratorDashboard result = new AdministratorDashboard();
		
		final EnumMap<HelpRequestStatus, Integer> mapTotalNumberHR = new EnumMap<>(HelpRequestStatus.class);
		final EnumMap<HelpRequestStatus, Double> mapAverageHR = new EnumMap<>(HelpRequestStatus.class);
		final EnumMap<HelpRequestStatus, Double> mapDeviationHR = new EnumMap<>(HelpRequestStatus.class);
		final EnumMap<HelpRequestStatus, Double> mapMinumumHR = new EnumMap<>(HelpRequestStatus.class);
		final EnumMap<HelpRequestStatus, Double> mapMaximumHR = new EnumMap<>(HelpRequestStatus.class);
	
		final Map<String, Double> averageCostTheoryByC = new HashMap<>();
		final Map<String, Double> deviationCostTheoryByC = new HashMap<>();
		final Map<String, Double> minCostTheoryByC = new HashMap<>();
		final Map<String, Double> maxCostTheoryByC = new HashMap<>();
		
		final Map<String, Double> averageCostLabByC = new HashMap<>();
		final Map<String, Double> deviationCostLabByC = new HashMap<>();
		final Map<String, Double> minCostLabByC = new HashMap<>();
		final Map<String, Double> maxCostLabByC = new HashMap<>();
		
		for(final HelpRequestStatus status: HelpRequestStatus.values()) {
			for(final Object[] obj: this.repository.operationsHelpRequest(status)) {
				mapTotalNumberHR.put(status, this.getIntegerValue(obj[0]));
				mapAverageHR.put(status,  this.getDoubleValue(obj[1]));
				mapDeviationHR.put(status, this.getDoubleValue(obj[2]));
				mapMinumumHR.put(status, this.getDoubleValue(obj[3]));
				mapMaximumHR.put(status, this.getDoubleValue(obj[4]));
			}
		}
		
		for(final Object[] obj: this.repository.operationsTutorialsByC(TutorialType.THEORY)) {
			averageCostTheoryByC.put(obj[0].toString(), Double.valueOf(obj[1].toString()));
			deviationCostTheoryByC.put(obj[0].toString(), Double.valueOf(obj[2].toString()));
			minCostTheoryByC.put(obj[0].toString(), Double.valueOf(obj[3].toString()));
			maxCostTheoryByC.put(obj[0].toString(), Double.valueOf(obj[4].toString()));
		}
		
		for(final Object[] obj: this.repository.operationsTutorialsByC(TutorialType.LAB)) {
			averageCostLabByC.put(obj[0].toString(), Double.valueOf(obj[1].toString()));
			deviationCostLabByC.put(obj[0].toString(), Double.valueOf(obj[2].toString()));
			minCostLabByC.put(obj[0].toString(), Double.valueOf(obj[3].toString()));
			maxCostLabByC.put(obj[0].toString(), Double.valueOf(obj[4].toString()));
		}
		
		result.setTotalNumberOfTheoryTutorials(this.repository.totalTutorials(TutorialType.THEORY));
		result.setTotalNumberOfLabTutorials(this.repository.totalTutorials(TutorialType.LAB));
		result.setTotalNumberOfHelpRequestByStatus(mapTotalNumberHR);
		result.setAverageCostOfTheoryTutorialsByCurrency(averageCostTheoryByC);
		result.setDeviationCostOfTheoryTutorialsByCurrency(deviationCostTheoryByC);
		result.setMinimumCostOfTheoryTutorialsByCurrency(minCostTheoryByC);
		result.setMaximumCostOfTheoryTutorialsByCurrency(maxCostTheoryByC);
		result.setAverageCostOfLabTutorialsByCurrency(averageCostLabByC);
		result.setDeviationCostOfLabTutorialsByCurrency(deviationCostLabByC);
		result.setMinimumCostOfLabTutorialsByCurrency(maxCostLabByC);
		result.setMaximumCostOfLabTutorialsByCurrency(maxCostLabByC);
		result.setAverageHelpRequestsBudgetByStatus(mapAverageHR);
		result.setDeviationHelpRequestsBudgetByStatus(mapDeviationHR);
		result.setMinimumHelpRequestsBudgetByStatus(mapMinumumHR);
		result.setMaximumHelpRequestsBudgetByStatus(mapMaximumHR);
		return result;
	}

	private Integer getIntegerValue(final Object obj) {
		Integer res = 0;
		if(obj != null) {
			res = Integer.valueOf(obj.toString());
		}
		return res;
	}
	
	private Double getDoubleValue(final Object obj) {
		Double res = .0;
		if(obj != null) {
			res = Double.valueOf(obj.toString());
		}
		return res;
	}

	// Internal state ---------------------------------------------------------

}


