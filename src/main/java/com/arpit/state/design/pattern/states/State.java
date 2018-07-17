package com.arpit.state.design.pattern.states;

public class State {
	private final String state;

	public State(final String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}
