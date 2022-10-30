package acme.entities.blinks;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Blink extends AbstractEntity {

	// Serialisation identifier

	protected static final long	serialVersionUID = 1L;

	// Attributes

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date instantiationMoment;

	@NotBlank
	@Length(min=1, max=75)
	protected String caption;

	@NotBlank
	@Length(min=1, max=75)
	protected String authorAlias;

	@NotBlank
	@Length(min=1, max=255)
	protected String message;

	@Email
	protected String email;

}