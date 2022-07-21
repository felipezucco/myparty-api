package com.myparty.converter.action;

import com.myparty.converter.ConverterComponent;
import com.myparty.dto.action.GetAction;
import com.myparty.dto.action.GetActionLink;
import com.myparty.dto.action.PersistAction;
import com.myparty.dto.action.PersistActionLink;
import com.myparty.enums.DateFormatEnum;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.action.Action;
import com.myparty.model.action.ActionLink;
import com.myparty.service.*;
import com.myparty.utils.DateFormat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ActionLinkConverter extends ConverterComponent implements DataConverterInterface<ActionLink> {

    private ProductionService productionService;
    private VisualService visualService;
    private ActionService actionService;
    private PromotionService promotionService;

    @Override
    public <T> T convert(ActionLink entity, T destinationClass) {
        destinationClass = getDefault(destinationClass, GetActionLink.class);

        if (destinationClass instanceof GetActionLink) {
            GetActionLink gal = (GetActionLink) destinationClass;
            gal.setProduction(transform(entity.getProduction()));
            gal.setVisual(transform(entity.getVisual()));

        }

        return destinationClass;
    }

    @Override
    public ActionLink revert(Object o) {
        ActionLink action = new ActionLink();

        if (o instanceof PersistActionLink) {
            PersistActionLink pal = (PersistActionLink) o;

            if (pal.getProductionId() != null) {
                action.setProduction(productionService.getProductionById(pal.getProductionId()));
            }
            if (pal.getVisualId() != null) {
                action.setVisual(visualService.getVisualById(pal.getVisualId()));
            }
            if (pal.getPromotionId() != null) {
                action.setPromotion(promotionService.getPromotionById(pal.getPromotionId()));
            }
            if (pal.getActionId() != null) {
                action.setAction(actionService.getActionById(pal.getActionId()));
            }
        }

        return action;
    }
}
