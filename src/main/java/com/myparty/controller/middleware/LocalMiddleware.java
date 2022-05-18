package com.myparty.controller.middleware;

import com.myparty.controller.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.myparty.dto.LocalDTO;
import com.myparty.model.Local;
import com.myparty.service.LocalService;
import org.springframework.stereotype.Service;

@Service
public class LocalMiddleware extends RootMiddleware {

    @Autowired
    private LocalService localService;

    @GetMapping
    public List<LocalDTO> getLocals() throws Exception {
        return convert(localService.getLocals());
    }

    public LocalDTO persistLocal(LocalDTO localDTO) throws Exception {
        Local local = convert(localDTO);
        localService.persistLocal(local);
        return convert(local);
    }

    public LocalDTO getLocal(Long id) {
        return convert(localService.getLocalById(id));
    }
    
    public List<LocalDTO> getLocalsByOrganizationId(Long id) {
        return convert(localService.getLocalsByOrganizationId(id));
    }

}
