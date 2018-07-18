package com.arpit.state.design.pattern.config;

import com.arpit.state.design.pattern.events.Event;
import com.arpit.state.design.pattern.states.State;
import com.arpit.state.design.pattern.transition.Transition;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class StateMachine {
    private List<Transition> transitions = new ArrayList<>();
    private State current;

    @PostConstruct
    public void postConstruct() {
        transitions.add(new Transition(State.INITIALIZED, State.ORDERED,
                Event.ORDER));
        transitions.add(new Transition(State.ORDERED, State.SHIPPED,
                Event.SHIP));
        transitions.add(new Transition(State.SHIPPED, State.DELIVERED,
                Event.DELIVER));

        transitions.add(new Transition(State.DELIVERED, State.PRINTED,
                Event.PRINT));
        transitions.add(new Transition(State.PRINTED, State.END,
                Event.END));
    }

    public State getNextState(State currentState, Event event) {
        for (Transition transition : transitions) {
            String state = transition.getFrom().name();
            String eventName = transition.getEvent().name();

            String currState = currentState.name();
            String eve = event.name();

            if (state.equals(currState) && eventName.equals(eve)) {
                return transition.getTo();
            }
        }
        return null;
    }

    public State getCurrent() {
        return current;
    }

    public void setCurrent(State current) {
        this.current = current;
    }
}
