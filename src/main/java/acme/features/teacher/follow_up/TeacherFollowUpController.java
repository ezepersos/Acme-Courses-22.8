package acme.features.teacher.follow_up;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.follow_up.FollowUp;
import acme.framework.controllers.AbstractController;
import acme.roles.Teacher;

@Controller
public class TeacherFollowUpController extends AbstractController<Teacher, FollowUp>{

	// Internal state
	
	@Autowired
	protected TeacherFollowUpListService listService;
	
	@Autowired
	protected TeacherFollowUpShowService showService;
	
	@Autowired
	protected TeacherFollowUpCreateService createService;
	
	// Constructors
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
	}
}
