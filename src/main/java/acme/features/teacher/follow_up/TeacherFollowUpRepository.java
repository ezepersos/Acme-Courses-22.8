package acme.features.teacher.follow_up;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.follow_up.FollowUp;
import acme.entities.help_requests.HelpRequest;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface TeacherFollowUpRepository extends AbstractRepository {

	@Query("select fu from FollowUp fu where fu.id = :id")
	FollowUp findFollowUpById(int id);
	
	@Query("select fu from FollowUp fu where fu.helpRequest.teacher.id = :teacherId")
	Collection<FollowUp> findAllFollowUpsByTeacherId(int teacherId);

	@Query("select hr from HelpRequest hr where hr.id = :helpRequestId")
	HelpRequest findHelpRequestById(int helpRequestId);
}
