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
public class LocalMiddleware extends RootController {

    @Autowired
    private LocalService localService;

    @GetMapping
    public List<LocalDTO> getLocals() throws Exception {
        return data.convert(localService.getLocals());
    }

    public LocalDTO createLocal(LocalDTO localDTO) throws Exception {
        Local local = data.convert(localDTO);
        localService.persistLocal(local);
        return data.convert(local);
    }

    public LocalDTO getLocal(Long id) {
        return data.convert(localService.getLocalById(id));
    }

}
