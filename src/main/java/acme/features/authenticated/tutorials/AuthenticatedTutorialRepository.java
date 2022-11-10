package acme.features.authenticated.tutorials;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.tutorials.Tutorial;
import acme.framework.repositories.AbstractRepository;


public interface AuthenticatedTutorialRepository extends AbstractRepository{

	@Query("select t from Tutorial t where t.id = :id")
	Tutorial findTutorialById(int id);

	@Query("select t from Tutorial t where t.tutorialType = acme.entities.tutorials.TutorialType.THEORY")
	Collection<Tutorial> findAllTheoryTutorials();

	@Query("select t from Tutorial t where t.tutorialType = acme.entities.tutorials.TutorialType.LAB")
	Collection<Tutorial> findAllLabTutorials();

}