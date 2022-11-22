package acme.features.learner.blahblah;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.blahblah.Blahblah;
import acme.entities.systemConfigurations.SystemConfiguration;
import acme.entities.tutorials.Tutorial;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Teacher;

@Repository
public interface LearnerBlahblahRepository extends AbstractRepository {

	@Query("select b from Blahblah b")
	Collection<Blahblah> findAllBlahblah();

	@Query("select b from Blahblah b where b.id = :id")
	Blahblah findBlahblahById(int id);
	@Query("select b from Blahblah b where b.ticker = :ticker")
	Blahblah findBlahblahByTicker(String ticker);

	@Query("select t from Tutorial t")
	Collection<Tutorial> findTutorials();
	
	@Query("select t from Teacher t where t.id = :id")
	Teacher findTeacherById(Integer id);
	
	@Query("select t from Tutorial t where t.ticker = :ticker")
	Tutorial findTutorialByTicker(String ticker);

	@Query("select sc from SystemConfiguration sc")
	Collection<SystemConfiguration> findAllConfigurations();

	@Query("select t from Tutorial t where t.id = :id")
	Tutorial findTutorialById(Integer id);
}
