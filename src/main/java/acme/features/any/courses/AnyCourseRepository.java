package acme.features.any.courses;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.courses.Course;
import acme.framework.repositories.AbstractRepository;


public interface AnyCourseRepository extends AbstractRepository{

	@Query("select c from Course c where c.id = :id and c.isPublished = TRUE")
	Course findCouseById(int id);

	@Query("select c from Course c where c.isPublished = TRUE")
	Collection<Course> findAllPublishedCourses();
	
	@Query("select q.course from Quantity q where q.course.isPublished = TRUE and q.tutorial.id = :id")
	Collection<Course> findAllCourseWithTutorial(int id);
	
	@Query("select sum(q.units * t.cost.amount) as suma, t.cost.currency as currency from Quantity q, Tutorial t where q.course.id = :id and q.tutorial = t.id group by t.cost.currency")
	Collection<Object[]> costByCurrency(int id);

}