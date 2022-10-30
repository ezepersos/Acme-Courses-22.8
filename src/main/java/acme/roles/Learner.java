package acme.roles;


import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.framework.roles.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Learner extends UserRole {
	// Serialisation identifier -----------------------------------------------

    protected static final long    serialVersionUID    = 1L;

    // Attributes -------------------------------------------------------------

    @NotBlank
    @Size(min=1,max=75)
    protected String            school;

    @NotBlank
    @Size(min=1,max=255)
    protected String            statement;

    @URL
    protected String            hyperlink;

}