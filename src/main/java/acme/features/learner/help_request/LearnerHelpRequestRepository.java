package acme.features.learner.help_request;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.follow_up.FollowUp;
import acme.entities.help_requests.HelpRequest;
import acme.entities.systemConfigurations.SystemConfiguration;
import acme.entities.tutorials.Tutorial;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Learner;
import acme.roles.Teacher;

@Repository
public interface LearnerHelpRequestRepository extends AbstractRepository {

	@Query("select hr from HelpRequest hr where hr.id = :id")
	HelpRequest findHelpRequestById(int id);
	
	@Query("select hr from HelpRequest hr where hr.learner.id = :learnerId")
	Collection<HelpRequest> findAllHelpRequestByLearnerId(int learnerId);

	@Query("select sc from SystemConfiguration sc")
	Collection<SystemConfiguration> findAllConfigurations();
	
	@Query("select hr from HelpRequest hr where hr.ticker = :ticker")
	HelpRequest findHelpRequestByTicker(String ticker);
	
	@Query("select t from Teacher t where t.id = :id")
	Teacher findTeacherById(Integer id);
	
	@Query("select t from Teacher t")
	Collection<Teacher> findTeachers();
	
	@Query("select t from Tutorial t")
	Collection<Tutorial> findTutorials();
	
	@Query("select l from Learner l where l.id = :id")
	Learner findOneLearnerById(int id);
	
	@Query("select fu from FollowUp fu where fu.helpRequest.id = :id")
	Collection<FollowUp> findAllFollowUpByhelpRequestId(int id);
	
	@Query("select t from Tutorial t where t.ticker = :ticker")
	Tutorial findTutorialByTicker(String ticker);
	
	@Modifying
	@Query("delete from FollowUp fu where fu.id = :id")
	void deleteFollowUpById(int id);

	


}
