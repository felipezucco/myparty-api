package com.myparty.middleware;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.myparty.dto.local.GetLocal;
import com.myparty.model.Local;
import com.myparty.service.LocalService;
import org.springframework.stereotype.Service;

@Service
public class LocalMiddleware extends RootMiddleware {

    @Autowired
    private LocalService localService;

    @GetMapping
    public List<GetLocal> getLocals() throws Exception {
        return convert(localService.getLocals());
    }

    public GetLocal persistLocal(GetLocal getLocal) throws Exception {
        Local local = convert(getLocal);
        localService.persistLocal(local);
        return convert(local);
    }

    public GetLocal getLocal(Long id) {
        return convert(localService.getLocalById(id));
    }
    
    public List<GetLocal> getLocalsByOrganizationId(Long id) {
        return convert(localService.getLocalsByOrganizationId(id));
    }

}
