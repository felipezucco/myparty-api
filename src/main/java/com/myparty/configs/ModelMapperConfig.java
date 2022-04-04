package com.myparty.configs;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myparty.dto.EventDTO;
import com.myparty.dto.OrganizationDTO;
import com.myparty.dto.OrganizerDTO;
import com.myparty.enums.DateFormatEnum;
import com.myparty.model.Account;
import com.myparty.model.Event;
import com.myparty.model.Organization;
import com.myparty.model.Organizer;
import com.myparty.utils.DateFormat;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() throws Exception {
		ModelMapper mapper = new ModelMapper();
		eventMapper(mapper);
		organizerMapper(mapper);
		organizationMapper(mapper);
		return mapper;
	}

	private void eventMapper(ModelMapper mapper) throws Exception {
		Converter<String, Date> stringToDateConverter = ctx -> {
			try {
				return DateFormat.format(DateFormatEnum.ptBR, ctx.getSource());
			} catch (ParseException e) {
				return null;
			}
		};

		mapper.createTypeMap(EventDTO.class, Event.class)
				.addMappings(map -> map.using(stringToDateConverter).map(EventDTO::getDate, Event::setDate));
	}

	private void organizerMapper(ModelMapper mapper) throws Exception {
		Converter<Long, Account> longToAccountConverter = ctx -> {
			Account account = new Account();
			account.setId(ctx.getSource());
			return account;
		};

		Converter<Long, Organization> longToOrganizationConverter = ctx -> {
			Organization organization = new Organization();
			organization.setId(ctx.getSource());
			return organization;
		};

		mapper.createTypeMap(OrganizerDTO.class, Organizer.class)
				.addMappings(map -> map.using(longToAccountConverter).map(OrganizerDTO::getAccountId, Organizer::setAccount))
				.addMappings(map -> map.using(longToOrganizationConverter).map(OrganizerDTO::getOrganizationId,
						Organizer::setOrganization));
		
	}

	private void organizationMapper(ModelMapper mapper) {
		Converter<List<Organizer>, List<OrganizerDTO>> organizerToOrganizerDTOConverter = ctx -> {
			return ctx.getSource().stream().map(org -> mapper.map(org, OrganizerDTO.class)).collect(Collectors.toList());
		};

		mapper.createTypeMap(Organization.class, OrganizationDTO.class)
				.addMappings(map -> map.using(organizerToOrganizerDTOConverter)
						.map(Organization::getOrganizers, OrganizationDTO::setOrganizers));
		
	}
	
}