package com.myparty.controller;

import com.myparty.controller.middleware.LocalMiddleware;
import java.util.List;

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

import com.myparty.dto.LocalDTO;

@RestController
@RequestMapping("/api/local")
public class LocalController {

    @Autowired
    private LocalMiddleware localMiddleware;

    @GetMapping
    public ResponseEntity<List<LocalDTO>> getLocals() throws Exception {
        return ResponseEntity.ok(localMiddleware.getLocals());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void persistLocal(@RequestBody LocalDTO localDTO) throws Exception {
        localMiddleware.persistLocal(localDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LocalDTO> getLocal(@PathVariable("id") Long id) {
        return ResponseEntity.ok(localMiddleware.getLocal(id));
    }
    
    @GetMapping(value = "/org/{id}")
    public ResponseEntity<List<LocalDTO>> getLocalsByOrganizationId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(localMiddleware.getLocalsByOrganizationId(id));
    }

}
