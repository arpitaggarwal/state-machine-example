package com.arpit.state.design.pattern.transition;

import java.util.Set;

import com.arpit.state.design.pattern.condition.Condition;
import com.arpit.state.design.pattern.states.State;

public class Transition {
	private State from;
	private Set<Condition> conditions;
	private State to;

	public Transition(State from, State to, Set<Condition> conditions) {
		this.from = from;
		this.to = to;
		this.conditions = conditions;
	}

	public State getFrom() {
		return from;
	}

	public void setFrom(State from) {
		this.from = from;
	}

	public Set<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(Set<Condition> conditions) {
		this.conditions = conditions;
	}

	public State getTo() {
		return to;
	}

	public void setTo(State to) {
		this.to = to;
	}
}
