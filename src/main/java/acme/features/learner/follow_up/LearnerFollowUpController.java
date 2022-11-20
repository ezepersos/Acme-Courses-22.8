package acme.features.learner.follow_up;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.follow_up.FollowUp;
import acme.framework.controllers.AbstractController;
import acme.roles.Learner;

@Controller
public class LearnerFollowUpController extends AbstractController<Learner, FollowUp>{

	// Internal state
	
	@Autowired
	protected LearnerFollowUpListService listService;
	
	@Autowired
	protected LearnerFollowUpShowService showService;
	
	@Autowired
	protected LearnerFollowUpCreateService createService;
	
	// Constructors
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
	}
}
