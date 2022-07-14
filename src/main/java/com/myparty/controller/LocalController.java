package com.myparty.controller;

import java.util.List;

import com.myparty.dto.local.PersistLocal;
import com.myparty.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myparty.middleware.LocalMiddleware;
import com.myparty.dto.local.GetLocal;

@RestController
@RequestMapping("/api/local")
public class LocalController extends ControllerComponent {

    @Autowired
    private LocalService localService;
    
    @Autowired
    private NotificationController notificationController;
    
    @GetMapping
    public ResponseEntity<List<GetLocal>> getLocals() throws Exception {
        return ResponseEntity.ok(_8(localService.getLocals()));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void persistLocal(@RequestBody PersistLocal persistLocal) throws Exception {
        localService.persistLocal(_8(persistLocal));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GetLocal> getLocal(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_8(localService.getLocalById(id)));
    }
    
    @GetMapping(value = "/org/{id}")
    public ResponseEntity<List<GetLocal>> getLocalsByOrganizationId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_8(localService.getLocalsByOrganizationId(id)));
    }

}
