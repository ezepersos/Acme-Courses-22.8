package acme.entities.blahblah;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.tutorials.Tutorial;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Blahblah extends AbstractEntity {

	// Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes
	
	@NotBlank
	@Column(unique = true)
	//@Pattern(regexp = "^[yy]{3}-[0-9]{3}(:[A-Z]{1,10})?$")
	protected String ticker;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date creationMoment;
	
	@NotBlank
	@Length(min = 1, max = 75)
	protected String caption;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String summary;
	
	@NotNull
	protected Money cost;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startTime;	
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date endingTime;
	
	@URL
	protected String hlink;
	
	// Relations -------------------------------------------------------------
	
	@NotNull
	@Valid
	@OneToOne(optional = false)
	protected Tutorial tutorial;
	
}