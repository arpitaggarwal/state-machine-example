package com.arpit.statemachine.actions;

import com.arpit.statemachine.events.Events;
import com.arpit.statemachine.model.Order;
import com.arpit.statemachine.states.States;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import java.util.List;

public class DeliveredAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> context) {
        System.out.println("Delivered Action called....");
        if (context.getMessage().getHeaders() != null) {
            Order order = (Order) context.getMessage().getHeaders().get("order");
            System.out.println("Order id to be delivered :: " + order.getId());
        }

    }
}
