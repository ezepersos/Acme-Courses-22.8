package acme.features.teacher.courses;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.framework.repositories.AbstractRepository;

public interface TeacherCourseRepository extends AbstractRepository{

	@Query("select c from Course c where c.id = :id")
	Course findCourseById(int id);

	@Query("select c from Course c where c.teacher.id = :teacherId")
	Collection<Course> findCoursesByTeacher(int teacherId);

	@Query("select sum(q.units * t.cost.amount) as suma, t.cost.currency as currency from Quantity q, Tutorial t where q.course.id = :id and q.tutorial = t.id group by t.cost.currency")
	Collection<Object[]> costByCurrency(int id);
	
	@Query("select qty from Quantity qty where qty.course.id = :id")
	Collection<Quantity> findAllQuantitiesByCourseId(int id);

}