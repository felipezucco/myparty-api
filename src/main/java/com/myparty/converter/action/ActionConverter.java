package com.myparty.converter.action;

import com.myparty.converter.ConverterComponent;
import com.myparty.dto.action.GetAction;
import com.myparty.dto.action.PersistAction;
import com.myparty.enums.DateFormatEnum;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.action.Action;
import com.myparty.service.EventService;
import com.myparty.service.OrganizationService;
import com.myparty.utils.DateFormat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ActionConverter extends ConverterComponent implements DataConverterInterface<Action> {

    private OrganizationService organizationService;
    private EventService eventService;

    @Override
    public <T> T convert(Action entity, T destinationClass) {
        destinationClass = getDefault(destinationClass, GetAction.class);

        if (destinationClass instanceof GetAction) {
            GetAction ga = (GetAction) destinationClass;
            ga.setEndDate(DateFormat.format(DateFormatEnum.ptBR, entity.getEndDate()));
            ga.setStartDate(DateFormat.format(DateFormatEnum.ptBR, entity.getStartDate()));
            ga.setId(entity.getId());
            ga.setName(entity.getName());
            ga.setOrganizers(transform(entity.getOrganizers()));
            ga.setActionLink(transform(entity.getActionLinks()));
        }

        return destinationClass;
    }

    @Override
    public Action revert(Object o) {
        Action action = new Action();

        if (o instanceof PersistAction) {
            PersistAction pa = (PersistAction) o;
            action.setEndDate(DateFormat.format(DateFormatEnum.Default, pa.getEndDate()));
            action.setStartDate(DateFormat.format(DateFormatEnum.Default, pa.getStartDate()));
            action.setName(pa.getName());
            action.setEvent(eventService.getEventById(pa.getEventId()));
            action.setOrganizers(Arrays.stream(pa.getOrganizersId())
                    .map(organizerId -> organizationService.getOrganizerById(organizerId)
            ).collect(Collectors.toList()));
            action.setActionLinks(transform(pa.getActionLink()));
            action.getActionLinks().setAction(action);
        }

        return action;
    }
}
