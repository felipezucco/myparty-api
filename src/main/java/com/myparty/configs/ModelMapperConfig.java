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
import com.myparty.model.Event;
import com.myparty.model.Organization;
import com.myparty.model.Organizer;
import com.myparty.model.UserProfile;
import com.myparty.utils.DateFormat;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        eventMapper(mapper);
        organizerMapper(mapper);
        organizationMapper(mapper);
        return mapper;
    }

    private void eventMapper(ModelMapper mapper) {
        Converter<String, Date> stringToDateConverter = ctx -> {
            try {
                return DateFormat.format(DateFormatEnum.ptBR, ctx.getSource());
            } catch (ParseException e) {
                return null;
            }
        };

        mapper.createTypeMap(EventDTO.class, Event.class)
                .addMappings(map -> map.using(stringToDateConverter).map(EventDTO::getDate, Event::setDate));
        
        Converter<Date, String> dateToStringConverter = ctx -> {
            return DateFormat.format(DateFormatEnum.ptBR, ctx.getSource());
        };

        mapper.createTypeMap(Event.class, EventDTO.class)
                .addMappings(map -> map.using(dateToStringConverter).map(Event::getDate, EventDTO::setDate));
        
    }

    private void organizerMapper(ModelMapper mapper) {
        Converter<Long, UserProfile> longToAccountConverter = ctx -> {
            UserProfile account = new UserProfile();
            account.setId(ctx.getSource());
            return account;
        };

        Converter<Long, Organization> longToOrganizationConverter = ctx -> {
            Organization organization = new Organization();
            organization.setId(ctx.getSource());
            return organization;
        };

        mapper.createTypeMap(OrganizerDTO.class, Organizer.class)
                .addMappings(
                        map -> map.using(longToAccountConverter).map((org) -> org.getUser().getId(), Organizer::setUser))
                .addMappings(map -> map.using(longToOrganizationConverter).map(OrganizerDTO::getOrganizationId,
                Organizer::setOrganization));

    }

    private void organizationMapper(ModelMapper mapper) {
        Converter<List<Organizer>, List<String>> organizerToOrganizerDTOConverter = ctx -> {
            return ctx.getSource().stream().map(org -> org.getUser().getEmail()).collect(Collectors.toList());
        };

        Converter<UserProfile, Long> userToIdDTOConverter = ctx -> {
            return ctx.getSource().getId();
        };

        mapper.createTypeMap(Organization.class, OrganizationDTO.class)
                .addMappings(map -> map.using(userToIdDTOConverter).map(Organization::getUser,
                OrganizationDTO::setAccountId))
                .addMappings(map -> map.using(organizerToOrganizerDTOConverter).map(Organization::getOrganizers,
                OrganizationDTO::setOrganizers));

        Converter<Long, UserProfile> accountIdToUserDTOConverter = ctx -> {
            UserProfile userProfile = new UserProfile();
            userProfile.setId(ctx.getSource());
            return userProfile;
        };
        
        Converter<List<OrganizerDTO>, List<Organizer>> emailToOrganizerConverter = ctx -> {
            List<Organizer> organizerList = ctx.getSource().parallelStream().map(orgDTO -> {
                Organizer organizer = new Organizer();
                UserProfile userProfile = UserProfile.builder().id(orgDTO.getId());
                organizer.setUser(userProfile);
                return organizer;
            }).collect(Collectors.toList());
            return organizerList;
        };

        mapper.createTypeMap(OrganizationDTO.class, Organization.class)
                .addMappings(map -> map.using(accountIdToUserDTOConverter).map(OrganizationDTO::getAccountId, Organization::setUser))
                .addMappings(map -> map.using(emailToOrganizerConverter).map(OrganizationDTO::getOrganizers, Organization::setOrganizers));
                
    }

}
