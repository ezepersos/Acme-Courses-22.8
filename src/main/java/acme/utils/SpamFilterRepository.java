package acme.utils;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemConfigurations.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SpamFilterRepository extends AbstractRepository {

	@Query("select sc from SystemConfiguration sc")
	Collection<SystemConfiguration> findAllConfigurations();


}