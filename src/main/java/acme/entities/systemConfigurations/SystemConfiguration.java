package acme.entities.systemConfigurations;


import javax.persistence.Entity;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity {

	// Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes

	protected String systemCurrency;

	protected String acceptedCurrencies;

	/* TODO:
	 	A list of spam records. A spam record has three components, namely: a term, a weight, and a booster. The term is a string with words that are commonly used by spammers,
	  e.g., “Viagra”; the weight is a number in range 0.00 – 1.00 that measures how likely finding the term in a piece of text indicates that it is actual spam, e.g., “0.80”;
	  the booster is an optional string that boosts the weight by a predefined boosting factor when it co-occurs with the term, e.g., “cheap”.
	  The default list of spam records must be as follows:	  (“sex”, 0.10, “”), (“viagra”, 0.80, “cheap”), (“cialis", 0.80, “cheap”), (“hard core”, 0.10, “picture”), (“sexy”, 0.05, “”),
	  (“nigeria”, 0.05, “million”), (“you’ve won”, 0.05, “nigeria”), (“one million”, 0.05, “”)
	  and their corresponding translations to the languages available for internationalisation.   
	 */
	
	protected Integer spamThreshold;
	
	protected Integer spamBoosterFactor;


}