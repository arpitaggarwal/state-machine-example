package com.arpit.state.design.pattern.transition;

import com.arpit.state.design.pattern.events.Event;
import com.arpit.state.design.pattern.states.State;

public class Transition {
    private State from;
    private Event event;
    private State to;

    public Transition(State from, State to, Event event) {
        this.from = from;
        this.to = to;
        this.event = event;
    }

    public State getFrom() {
        return from;
    }

    public void setFrom(State from) {
        this.from = from;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public State getTo() {
        return to;
    }

    public void setTo(State to) {
        this.to = to;
    }
}
