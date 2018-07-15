package com.arpit.statemachine.actions;

import com.arpit.statemachine.events.Events;
import com.arpit.statemachine.model.Order;
import com.arpit.statemachine.states.States;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class OrderedAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> context) {
        System.out.println("Ordered Action called....");
        Message<Events> message = MessageBuilder
                .withPayload(Events.SHIPPED)
                .setHeader("order", new Order(1))
                .build();
        System.out.println("Order placed and id is :: " + 1);
        context.getStateMachine().sendEvent(message);
    }
}
