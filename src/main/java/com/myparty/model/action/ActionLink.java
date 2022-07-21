package com.myparty.model.action;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.myparty.annotations.DataConverterType;
import com.myparty.converter.action.ActionLinkConverter;
import com.myparty.model.Promotion;
import com.myparty.model.Visual;
import com.myparty.model.production.Production;
import com.myparty.model.ticket.TicketBracelet;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "action_link")
@DataConverterType(ActionLinkConverter.class)
public class ActionLink {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "action_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "action_link_action_fk"))
    @JsonBackReference
    @ToString.Exclude
    private Action action;

    @ManyToOne
    @JoinColumn(name = "production_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "action_link_production_fk"))
    private Production production;

    @ManyToOne
    @JoinColumn(name = "visual_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "action_link_visual_fk"))
    private Visual visual;

    @ManyToOne
    @JoinColumn(name = "ticket_bracelet_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "action_link_ticket_bracelet_fk"))
    private TicketBracelet ticketBracelet;

    @ManyToOne
    @JoinColumn(name = "promotion_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "action_link_promotion_fk"))
    private Promotion promotion;

}
