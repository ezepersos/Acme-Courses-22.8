package acme.features.authenticated.blink;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import acme.entities.blinks.Blink;
import acme.framework.repositories.AbstractRepository;


public interface AuthenticatedBlinkRepository extends AbstractRepository{

	@Query("select b from Blink b where b.instantiationMoment < :end and b.instantiationMoment > :start ")
	Collection<Blink> findAllBlinks(Date start, Date end);

}