package acme.features.any.courses;

/*
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.courses.Course;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
@RequestMapping("/any/course/")
public class AnyCourseController extends AbstractController<Any, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyCourseShowService showService;
	@Autowired
	protected AnyCourseListService listService;
	@Autowired
	protected AnyCourseListWithTutorialService listTutorialService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
		super.addCommand("list-with-tutorial", "list", this.listTutorialService);
	}

}