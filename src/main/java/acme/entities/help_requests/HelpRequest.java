package acme.entities.help_requests;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.tutorials.Tutorial;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Learner;
import acme.roles.Teacher;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HelpRequest extends AbstractEntity {

	// Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes
	
	@NotNull
	protected HelpRequestStatus status;
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(:[A-Z]{1,10})?$")
	protected String ticker;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String statement;
	
	@NotNull
	protected Money budget;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date creationTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date endingTime;
	
	@URL
	protected String link;
	
	protected boolean isPublished;
	
	// Relations -------------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Learner learner;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Teacher teacher;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Tutorial tutorial;
	
	
}