package com.arpit.statemachine.handlers;

import com.arpit.statemachine.events.Events;
import com.arpit.statemachine.interceptors.StateInterceptor;
import com.arpit.statemachine.states.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.access.StateMachineFunction;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.LifecycleObjectSupport;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.stereotype.Component;

@Component
public class StateMachineHandler extends LifecycleObjectSupport {

    @Autowired
    private StateMachine<States, Events> stateMachine;

    /*private Set<OrderStateChangeListener> listeners = new HashSet<>();
*/
    @Override
    protected void onInit() throws Exception {
        stateMachine
                .getStateMachineAccessor()
                .withRegion().addStateMachineInterceptor(new StateInterceptor());

   /* public void registerListener(OrderStateChangeListener listener) {
        listeners.add(listener);
    }*/

   /* public void handleEvent(Message event, States sourceState)
    {
        stateMachine.stop();
        stateMachine
                .getStateMachineAccessor()
                .doWithAllRegions(access -> access.resetStateMa-chine(new DefaultStateMachineContext<States, Events>(sourceState, null, null, null)));
        stateMachine.start();
        stateMachine.sendEvent(event);
    }*/
}
}
