package acme.features.teacher.quantity;

import java.awt.Toolkit;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.courses.Course;
import acme.entities.quantities.Quantity;
import acme.entities.tutorials.Tutorial;
import acme.framework.repositories.AbstractRepository;

public interface TeacherQuantityRepository extends AbstractRepository {
	
	@Query("select q from Quantity q where q.id = :id")
	Quantity findQuantityById(int id);

	@Query("select q from Quantity q where q.course.id = :id")
	Collection<Quantity> findQuantityByCourse(int id);
	
	@Query("select distinct q from Quantity q where q.tutorial.teacher.id = :teacherId")
	Collection<Quantity> findAllQuantitiesByTeacherId(int teacherId);
	
	@Query("select q.course from Quantity q where q.id = :id")
	Toolkit findCourseByQuantity(int id);
	
	@Query("select i from Tutorial i where i.teacher.id = :teacherid and i.tutorialType = acme.entities.tutorials.TutorialType.THEORY")
	Collection<Tutorial> findTheoryTutorialsByTeacher(int teacherid);

	@Query("select i from Tutorial i where i.teacher.id = :teacherid and i.tutorialType = acme.entities.tutorials.TutorialType.LAB")
	Collection<Tutorial> findLabTutorialsByTeacher(int teacherid);
	
	@Query("select i from Tutorial i where i.title = :title and i.tutorialType = acme.entities.tutorials.TutorialType.THEORY")
	Tutorial findTheoryTutorialByName(String title);
	
	@Query("select i from Tutorial i where i.title = :title and i.tutorialType = acme.entities.tutorials.TutorialType.LAB")
	Tutorial findLabTutorialByName(String title);
	
	@Query("select t from Course t where t.id = :id")
	Course findCourseById(int id);
	
	@Query("select i from Tutorial i where i.title = :title")
	Tutorial findTutorialByName(String title);
	
	@Query("select i from Tutorial i where i.teacher.id = :teacherid")
	Collection<Tutorial> findTutorialByTeacher(int teacherid);
	
	@Query("select q.tutorial from Quantity q where q.id = :id")
	Tutorial findTutorialByQuantity(int id);
	
}