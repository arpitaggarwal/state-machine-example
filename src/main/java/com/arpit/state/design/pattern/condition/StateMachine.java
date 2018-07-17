package com.arpit.state.design.pattern.condition;

import java.util.List;
import java.util.Set;

import com.arpit.state.design.pattern.states.State;
import com.arpit.state.design.pattern.transition.Transition;

public class StateMachine {
	private List<Transition> transitions;
	private State current;

	public StateMachine(State start, List<Transition> transitions) {
		this.current = start;
		this.transitions = transitions;
	}

	public void apply(Set<Condition> conditions) {
		current = getNextState(conditions);
	}

	public State getNextState(Set<Condition> conditions) {
		for (Transition transition : transitions) {
			boolean currentStateMatches = transition.getFrom().getState().equals(current.getState());
			
			return transition.getTo();
			/*boolean conditionsMatch = transition.getConditions().equals(
					conditions);
			if (currentStateMatches && conditionsMatch) {
				return transition.getTo();
			}*/
		}
		return null;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public State getCurrent() {
		return current;
	}

	public void setCurrent(State current) {
		this.current = current;
	}
}
