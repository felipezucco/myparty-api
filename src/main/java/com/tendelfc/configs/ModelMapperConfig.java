package com.tendelfc.configs;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tendelfc.dto.AccountDTO;
import com.tendelfc.enums.RoleEnum;
import com.tendelfc.model.Account;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		
		accountMapper(mapper);
		
		return mapper;
	}
	
	private ModelMapper accountMapper(ModelMapper mapper) {
		Converter<Integer, RoleEnum> integerToRoleConverter = ctx -> {
			if (ctx.getSource() == null) return RoleEnum.USER;
			else return RoleEnum.getRoleEnumById(ctx.getSource());
		};
		mapper.createTypeMap(AccountDTO.class, Account.class)
			.addMappings(map -> map.using(integerToRoleConverter)
				.map(AccountDTO::getRole, Account::setRole));
		
		Converter<RoleEnum, Integer> roleToIntegerConverter = ctx -> {
			if (ctx.getSource() == null) return RoleEnum.USER.getId();
			else return ctx.getSource().getId();
		};
		mapper.createTypeMap(Account.class, AccountDTO.class)
			.addMappings(map -> map.using(roleToIntegerConverter)
				.map(Account::getRole, AccountDTO::setRole));
		
		return mapper;
	}
	
}
