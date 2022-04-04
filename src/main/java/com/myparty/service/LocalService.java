package com.myparty.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myparty.dto.LocalDTO;
import com.myparty.model.Local;
import com.myparty.repository.LocalRepository;


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
	
	public List<LocalDTO> getLocals() {
		List<Local> locals = localRepository.findAll();
		return locals.stream().map(l -> mapper.map(l, LocalDTO.class)).collect(Collectors.toList());
	}
	
	public LocalDTO getLocalById(Long id) {
		Optional<Local> local = localRepository.findById(id);		
		return local.map(l -> mapper.map(l, LocalDTO.class)).orElseGet(null); 
	}
}
