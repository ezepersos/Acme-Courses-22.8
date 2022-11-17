/*
 * AuthenticatedAnnouncementRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.teacher.tutorials;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.courses.Course;
import acme.entities.tutorials.Tutorial;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface TeacherTutorialRepository extends AbstractRepository {
	
	@Query("select t from Tutorial t where t.id = :id")
	Tutorial findTutorialById(int id);
	
	@Query("select t from Tutorial t where t.teacher.id = :id and t.tutorialType = acme.entities.tutorials.TutorialType.LAB")
	Collection<Tutorial> findLabByTeacher(Integer id);

	@Query("select t from Tutorial t where t.teacher.id = :id and t.tutorialType = acme.entities.tutorials.TutorialType.THEORY")
	Collection<Tutorial> findTheoryByTeacher(Integer id);
	
	@Query("select q.tutorial from Quantity q where q.course.id = :id and q.tutorial.tutorialType = acme.entities.tutorials.TutorialType.LAB")
	Collection<Tutorial> findTutorialByCourse(Integer id);

	@Query("select distinct q.course from Quantity q where q.course.teacher.id = :teacherId")
	Collection<Course> findAllCoursesByTeacherId(int teacherId);
	
	@Query("select qty.tutorial from Quantity qty where qty.course.id = :id and qty.tutorial.tutorialType = acme.entities.tutorials.TutorialType.LAB")
	Collection<Tutorial> findAllLabTutorialsByCourseId(int id);
	
	@Query("select qty.tutorial from Quantity qty where qty.course.id = :id and qty.tutorial.tutorialType = acme.entities.tutorials.TutorialType.THEORY")
	Collection<Tutorial> findAllTheoryTutorialsByCourseId(int id);
	
}
