package com.arpit.statemachine.actions;

import com.arpit.statemachine.events.Events;
import com.arpit.statemachine.model.Order;
import com.arpit.statemachine.states.States;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class ShippedAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> context) {
        System.out.println("Shipped Action called....");

            // set error in state machine to be captured by Interceptor.
           //  context.getStateMachine().setStateMachineError(e);

        /*String str = null;
        str.equals("abc");
*/
        if (context.getMessage().getHeaders() != null) {
            Order order = (Order) context.getMessage().getHeaders().get("order");
            System.out.println("Order id to be shipped :: " + order.getId());
            Message<Events> message = MessageBuilder
                    .withPayload(Events.DELIVERED)
                    .setHeader("order", new Order(1))
                    .build();
            context.getStateMachine().sendEvent(message);
        }
    }
}