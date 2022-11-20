package acme.features.authenticated.teacher;

import org.springframework.data.jpa.repository.Query;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Teacher;

public interface AuthenticatedTeacherRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);

	@Query("select t from Teacher t where t.userAccount.id = :id")
	Teacher findOneLearnerByUserAccountId(int id);

}
