package acme.features.teacher.quantity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.quantities.Quantity;
import acme.framework.controllers.AbstractController;
import acme.roles.Teacher;

@Controller
public class TeacherQuantityController extends AbstractController<Teacher, Quantity>{

	// Internal state
	
	@Autowired
	protected TeacherQuantityListService listService;
	
	@Autowired
	protected TeacherQuantityShowService showService;
	
	@Autowired
	protected TeacherQuantityCreateService createService;
	
	@Autowired
	protected TeacherQuantityDeleteService deleteService;
	
	// Constructors
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService);
	}
}