package com.tendelfc.common;

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
		
		Converter<Integer, RoleEnum> roleConverter = ctx -> RoleEnum.getRoleEnumById(ctx.getSource());
		
		mapper.createTypeMap(AccountDTO.class, Account.class)
			.addMappings(map -> map.using(roleConverter)
				.map(AccountDTO::getRole, Account::setRole));
		
		/*mapper.createTypeMap(User.class, UserDTO.class)
			.addMapping(user -> user.getRole(), (dto, value) -> {
				if (value != null) dto.setRole(((RoleEnum) value).getId());
				else dto.setRole(null);
			});*/
		
		return mapper;
	}
	
}
