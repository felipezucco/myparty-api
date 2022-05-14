package com.myparty.configs;

import com.myparty.converter.RoleConverter;
import com.myparty.dto.organizer.OrganizerDTO;
import com.myparty.dto.UserWithoutPasswordDTO;
import com.myparty.enums.RoleEnum;
import com.myparty.model.Organization;
import com.myparty.model.Organizer;
import com.myparty.model.UserProfile;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        organizerToDTO(mapper);
        dtoToOrganizer(mapper);
        return mapper;
    }
    
    private void organizerToDTO(ModelMapper mapper) {
        //RoleEnum to Integer
        Converter<RoleEnum, Integer> roleEnumToInteger = ctx -> {
            return ctx.getSource().getId();
        };
        
//        //Organization to Long
//        Converter<Organization, Long> organizationToLong = ctx -> {
//            return ctx.getSource().getId();
//        };
        
        //UserProfile to Long
        Converter<UserProfile, Long> userProfileToLong = ctx -> {
            return ctx.getSource().getId();
        };
        
        mapper.createTypeMap(Organizer.class, OrganizerDTO.class)
                .addMappings(m -> m.using(roleEnumToInteger).map(Organizer::getRole, OrganizerDTO::setRole))
                //.addMappings(m -> m.using(organizationToLong).map(Organizer::getOrganization, OrganizerDTO::setOrganization))
                .addMappings(m -> m.using(userProfileToLong).map(Organizer::getUser, OrganizerDTO::setUser));
    }    
    
    private void dtoToOrganizer(ModelMapper mapper) {
        //User to Long
        Converter<Long, UserProfile> longToUser = ctx -> {
            UserProfile user = new UserProfile();
            user.id(ctx.getSource());
            return user;
        };
        
        //Long to Role
        Converter<Integer, RoleEnum> integerToRole = ctx -> {
            return new RoleConverter().convertToEntityAttribute(ctx.getSource());
        };
        
        mapper.createTypeMap(OrganizerDTO.class, Organizer.class)
                .addMappings(m -> m.using(longToUser).map(OrganizerDTO::getUser, Organizer::setUser))
                .addMappings(m -> m.using(integerToRole).map(OrganizerDTO::getRole, Organizer::setRole));
    }
    
}
