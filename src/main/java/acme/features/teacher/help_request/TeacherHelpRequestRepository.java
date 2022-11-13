package acme.features.teacher.help_request;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.help_requests.HelpRequest;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface TeacherHelpRequestRepository extends AbstractRepository {

	@Query("select hr from HelpRequest hr where hr.id = :id")
	HelpRequest findHelpRequestById(int id);
	
	@Query("select hr from HelpRequest hr where hr.teacher.id = :teacherId")
	Collection<HelpRequest> findAllHelpRequestByTeacherId(int teacherId);
}
