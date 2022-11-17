package acme.features.learner.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import acme.entities.help_requests.HelpRequestStatus;
import acme.framework.repositories.AbstractRepository;

public interface LearnerDashboardRepository extends AbstractRepository{
	
	@Query("select count(h), avg(h.budget.amount), stddev(h.budget.amount), min(h.budget.amount), max(h.budget.amount) from HelpRequest h where h.status = :status")
	List<Object[]> operationsHelpRequest(HelpRequestStatus status);

}
