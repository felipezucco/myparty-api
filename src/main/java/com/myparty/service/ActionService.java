package com.myparty.service;

import com.myparty.model.action.Action;
import com.myparty.repository.ActionLinkRepository;
import com.myparty.repository.ActionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActionService {

    private ActionRepository actionRepository;
    private ActionLinkRepository actionLinkRepository;

    public Action getActionById(Long id) {
        return actionRepository.getById(id);
    }

    public Action persistAction(Action action) {
        return actionRepository.save(action);
    }

    public List<Action> getActionsByEventId(Long eventId) {
        return actionRepository.findByEventId(eventId);
    }

    public void removeAction(Long actionId) {
        actionRepository.deleteById(actionId);
    }
}
