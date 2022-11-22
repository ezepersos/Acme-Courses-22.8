package acme.features.learner.blahblah;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.blahblah.Blahblah;
import acme.framework.controllers.AbstractController;
import acme.roles.Learner;

@Controller
public class LearnerBlahblahController extends AbstractController<Learner, Blahblah>{

	// Internal state
	
	@Autowired
	protected LearnerBlahblahListService listService;
	
	@Autowired
	protected LearnerBlahblahShowService showService;
	
	@Autowired
	protected LearnerBlahblahCreateService createService;
	
	@Autowired
	protected LearnerBlahBlahDeleteService deleteService;
	
	@Autowired
	protected LearnerBlahblahUpdateService updateService;
	
	// Constructors
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("update", this.updateService);
	}
}
