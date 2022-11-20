package acme.features.authenticated.learner;

import org.springframework.data.jpa.repository.Query;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Learner;

public interface AuthenticatedLearnerRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);

	@Query("select l from Learner l where l.userAccount.id = :id")
	Learner findOneLearnerByUserAccountId(int id);

}
