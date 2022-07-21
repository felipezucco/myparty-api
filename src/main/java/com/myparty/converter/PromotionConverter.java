package com.myparty.converter;

import com.myparty.dto.promotion.GetPromotion;
import com.myparty.dto.promotion.PersistPromotion;
import com.myparty.enums.DateFormatEnum;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.Promotion;
import com.myparty.service.EventService;
import com.myparty.utils.DateFormat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PromotionConverter extends ConverterComponent implements DataConverterInterface<Promotion> {

    private EventService eventService;

    @Override
    public <T> T convert(Promotion entity, T destinationClass) {
        destinationClass = getDefault(destinationClass, GetPromotion.class);

        if (destinationClass instanceof GetPromotion) {
            GetPromotion gp = (GetPromotion) destinationClass;
            gp.setDescription(entity.getDescription());
            gp.setId(entity.getId());
            gp.setEvent(transform(entity.getEvent()));
            gp.setName(entity.getName());
            gp.setStartDate(DateFormat.format(entity.getStartDate()));
            gp.setEndDate(DateFormat.format(entity.getEndDate()));
        }

        return destinationClass;
    }

    @Override
    public Promotion revert(Object o) {
        Promotion promotion = new Promotion();

        if (o instanceof PersistPromotion) {
            PersistPromotion pp = (PersistPromotion) o;
            promotion.setStartDate(DateFormat.format(DateFormatEnum.Default, pp.getStartDate()));
            promotion.setEndDate(DateFormat.format(DateFormatEnum.Default, pp.getEndDate()));
            promotion.setDescription(pp.getDescription());
            promotion.setName(pp.getName());
            promotion.setEvent(eventService.getEventById(pp.getEventId()));
        }

        return promotion;
    }
}
