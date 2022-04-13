package com.myparty.controller;

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
import com.myparty.model.Local;
import com.myparty.service.LocalService;

@RestController
@RequestMapping("/api/local")
public class LocalController extends RootController {

    @Autowired
    private LocalService localService;

    @GetMapping
    public ResponseEntity<List<LocalDTO>> getLocals() throws Exception {
        return ResponseEntity.ok(data.convert(localService.getLocals()));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void persistLocal(@RequestBody LocalDTO localDTO) throws Exception {
        localService.persistLocal(data.convert(localDTO));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LocalDTO> getLocal(@PathVariable("id") Long id) {
        return ResponseEntity.ok(data.convert(localService.getLocalById(id)));
    }

}
