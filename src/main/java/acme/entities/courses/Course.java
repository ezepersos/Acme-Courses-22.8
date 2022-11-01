package acme.entities.courses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course extends AbstractEntity {

	// Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(:[A-Z]{1,10})?$")
	@NotBlank
	protected String			ticker;

	@NotBlank
	@Length(min = 1, max = 75)
	protected String			caption;

	@NotBlank
	@Length(min = 1, max = 255)
	protected String			abstractCourse;

	@URL
	protected String			link;

	// Relations -------------------------------------------------------------
	//TODO: Terminar con la entidad tutorial
	
}