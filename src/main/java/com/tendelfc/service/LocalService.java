package com.tendelfc.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tendelfc.dto.LocalDTO;
import com.tendelfc.model.Local;
import com.tendelfc.repository.LocalRepository;

@Service
public class LocalService {

	@Autowired
	private LocalRepository localRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public LocalDTO saveLocal(LocalDTO localDTO) {
		Local local = mapper.map(localDTO, Local.class);
		localRepository.save(local);
		localDTO.setId(local.getId());
		return localDTO;
	}
}
