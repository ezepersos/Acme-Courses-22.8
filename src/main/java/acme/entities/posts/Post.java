package acme.entities.posts;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post extends AbstractEntity {
	
	// Serialisation identifier

	protected static final long	serialVersionUID = 1L;
	
	// Attributes 
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date instantiationMoment;
	
	@NotBlank
	@Length(min=1, max=75)
	protected String caption;
	
	@NotBlank
	@Length(min=1, max=255)
	protected String message;
	
	protected boolean informational;
	
	@URL
	protected String url;
	
	
}