package acme.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemConfigurations.SystemConfiguration;
import acme.filter.SpamFilter;

@Service
public class SpamFilterService {

	@Autowired
	protected SpamFilterRepository repository;

	private SpamFilterService(final SpamFilterRepository repository) {
		this.repository = repository;
	}

	private void initFilter() {
		
		final List<SystemConfiguration> allConfigurations = new ArrayList<>(this.repository.findAllConfigurations());
		final SystemConfiguration sysCon = allConfigurations.get(0);
		
		SpamFilter.initFilter(sysCon.getSpamRecords(), sysCon.getSpamThreshold(), sysCon.getSpamBoosterFactor());
	}

	public boolean isSpam(final String cadena) {
		this.initFilter();
		return SpamFilter.isSpam(cadena);
	}
	}




