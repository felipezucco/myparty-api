package com.myparty.controller;

import com.myparty.dto.action.GetAction;
import com.myparty.dto.action.PersistAction;
import com.myparty.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/action")
public class ActionController extends ControllerComponent {

    @Autowired
    private ActionService actionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void persistAction(@RequestBody PersistAction persistAction) {
        actionService.persistAction(_8(persistAction));
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<List<GetAction>> getActionsByEventId(@PathVariable("id") Long eventId) {
        return ResponseEntity.ok(_8(actionService.getActionsByEventId(eventId)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeAction(@PathVariable("id") Long actionId) {
        actionService.removeAction(actionId);
    }
}
