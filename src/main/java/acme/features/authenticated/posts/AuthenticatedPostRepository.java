package acme.features.authenticated.posts;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import acme.entities.posts.Post;
import acme.framework.repositories.AbstractRepository;


public interface AuthenticatedPostRepository extends AbstractRepository{

	@Query("select p from Post p where p.id = :id")
	Post findPostById(int id);
	
	@Query("select p from Post p where p.instantiationMoment > :deadline")
	Collection<Post> findRecentPost(Date deadline);

}