package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import acme.entities.help_requests.HelpRequestStatus;
import acme.entities.tutorials.TutorialType;
import acme.framework.repositories.AbstractRepository;


public interface AdministratorDashboardRepository extends AbstractRepository{
	
	@Query("select count(t) from Tutorial t where t.tutorialType = :type")
	Integer totalTutorials(TutorialType type);
	
	@Query("select t.cost.currency, avg(t.cost.amount), stddev(t.cost.amount),  min(t.cost.amount), max(t.cost.amount) from Tutorial t group by t.cost.currency")
	List<Object[]> operationsTutorialsByC(TutorialType type);

	@Query("select count(h), avg(h.budget.amount), stddev(h.budget.amount), min(h.budget.amount), max(h.budget.amount) from HelpRequest h where h.status = :status")
	List<Object[]> operationsHelpRequest(HelpRequestStatus status);
	
	@Query("select b.cost.currency, avg(b.cost.amount), stddev(b.cost.amount), min(b.cost.amount), max(b.cost.amount) from Blahblah b group by b.cost.currency")
	List<Object[]> operationsByBlahBlah();
	
	@Query("select count(b) from Blahblah b")
	Double totalBlahblah();
	
	@Query("select count(t) from Tutorial t")
	Double totalTutorials();
	
}