package com.myparty.converter;

import com.myparty.dto.visual.GetVisual;
import com.myparty.dto.visual.PersistVisual;
import com.myparty.enums.DateFormatEnum;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.Visual;
import com.myparty.service.EventService;
import com.myparty.utils.DateFormat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VisualConverter extends ConverterComponent implements DataConverterInterface<Visual> {

    private EventService eventService;

    @Override
    public <T> T convert(Visual entity, T destinationClass) {
        destinationClass = getDefault(destinationClass, GetVisual.class);

        if (destinationClass instanceof GetVisual) {
            GetVisual gv = (GetVisual) destinationClass;
            gv.setDescription(entity.getDescription());
            gv.setEvent(transform(entity.getEvent()));
            gv.setFinancial(transform(entity.getFinancial()));
            gv.setName(entity.getName());
            gv.setId(entity.getId());
            gv.setReleaseDate(DateFormat.format(entity.getReleaseDate()));
            gv.setChannel(entity.getChannel());
            gv.setType(entity.getType());
        }

        return destinationClass;
    }

    @Override
    public Visual revert(Object o) {
        Visual visual = new Visual();

        if (o instanceof PersistVisual) {
            PersistVisual pv = (PersistVisual) o;
            visual.setName(pv.getName());
            visual.setDescription(pv.getDescription());
            visual.setReleaseDate(DateFormat.format(DateFormatEnum.Default, pv.getReleaseDate()));
            visual.setFinancial(transform(pv.getFinancial()));
            visual.setEvent(eventService.getEventById(pv.getEventId()));
            visual.setType(pv.getType());
            visual.setChannel(pv.getChannel());
        }

        return visual;
    }
}
