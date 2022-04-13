package com.myparty.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myparty.model.Local;
import com.myparty.repository.LocalRepository;

@Service
public class LocalService extends RootService {

    @Autowired
    private LocalRepository localRepository;

    public Local persistLocal(Local local) {
        localRepository.save(local);
        return local;
    }

    public List<Local> getLocals() {
        return localRepository.findAll();
    }

    public Local getLocalById(Long id) {
        Optional<Local> local = localRepository.findById(id);
        return local.orElseGet(null);
    }
}
