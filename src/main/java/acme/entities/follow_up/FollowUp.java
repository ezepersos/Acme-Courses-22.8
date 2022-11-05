package acme.entities.follow_up;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.help_requests.HelpRequest;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FollowUp extends AbstractEntity {

	// Serialisation identifier
	protected static final long	serialVersionUID = 1L;

	// Attributes

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date instantiationMoment;
	
	@NotBlank
	@Length(min=1, max=255)
	protected String message;
	
	@URL
	protected String link;
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected int serialNumber;
	
	// Derived attributes
	
	@NotBlank
	public String getAutomaticSequenceNumber() {
		return "〈" + this.getHelpRequest().getTicker() + "〉:〈" + String.format("%04d", this.getSerialNumber()) + "〉";
	}
	
	// Relationships
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected HelpRequest helpRequest;
	

}