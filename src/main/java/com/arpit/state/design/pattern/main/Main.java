package com.arpit.state.design.pattern.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.arpit.state.design.pattern.actions.OrderedAction;
import com.arpit.state.design.pattern.actions.ShippedAction;
import com.arpit.state.design.pattern.condition.Condition;
import com.arpit.state.design.pattern.condition.StateMachine;
import com.arpit.state.design.pattern.context.StateContext;
import com.arpit.state.design.pattern.states.State;
import com.arpit.state.design.pattern.transition.Transition;

public class Main {
	public static void main(String[] args) {
		/*
		 * StateContext context = new StateContext();
		 * 
		 * OrderedAction startState = new OrderedAction();
		 * startState.doAction(context);
		 * 
		 * System.out.println(context.getAction().toString());
		 * 
		 * ShippedAction stopState = new ShippedAction();
		 * stopState.doAction(context);
		 * 
		 * System.out.println(context.getAction().toString());
		 */

		State one = new State("one");
		State two = new State("two");
		State three = new State("three");

		Condition sunday = new Condition("Sunday");
		Condition raining = new Condition("Raining");
		Condition notSunday = new Condition("Not Sunday");
		Condition notRaining = new Condition("Not Raining");

		List<Transition> transitions = new ArrayList<>();
		Set<Condition> sundaySet = new HashSet<>();
		sundaySet.add(notRaining);
		transitions.add(new Transition(new State("one"), new State("two"),
				sundaySet));
		/*
		 * transitions.add(one, new Set(sunday), two); // <<--- Invalid, cant go
		 * to two and three transitions.add(one, new Set(raining), three);
		 * transitions.add(one, new Set(sunday, raining), three);
		 * transitions.add(one, new Set(notSunday, notRaining), three);
		 */
		StateMachine machine = new StateMachine(one, transitions);
		System.out.println(machine.getCurrent().getState()); // "one"

		Set<Condition> mondaySet = new HashSet<>();
		mondaySet.add(notRaining);

		machine.apply(new HashSet<Condition>(mondaySet));
		System.out.println(machine.getCurrent().getState()); // "three
	}
}