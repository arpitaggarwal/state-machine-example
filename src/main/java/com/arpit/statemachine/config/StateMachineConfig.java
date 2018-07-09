package com.arpit.statemachine.config;

import com.arpit.statemachine.actions.ActionS1;
import com.arpit.statemachine.actions.ActionS2;
import com.arpit.statemachine.events.Events;
import com.arpit.statemachine.states.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.SI)
                .states(EnumSet.allOf(States.class));

        /*states
                .withStates()
                .initial(States.S1)
                .state(States.S1)
                .and()
                .withStates()
                .parent(States.S1)
                .initial(States.S2)
                .state(States.S2).end(States.END)
                .and()
                .withStates()
                .parent(States.S1)
                .initial(States.S3)
                .state(States.S3).end(States.END);*/

      /*  states
                .withStates()
                .initial(States.S1, actionS1())
                .state(States.S1)
                .and()
                .withStates()
                .parent(States.S1)
                .initial(States.S2)
                .state(States.S2)
                .and()
                .withStates()
                .parent(States.S2)
                .initial(States.S3)
                .state(States.S3)
                .end(States.END);*/
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.SI).target(States.S2).event(Events.E1)
                .action(actionS1())
                .and()
                .withExternal()
                .source(States.S2).target(States.S1).action(actionS2())
              /*  .and()
                .withExternal()
                .source(States.S2).target(States.S3).action(actionS2())*/
        ;
    }

    @Bean
    public Action<States, Events> actionS1() {
        return new ActionS1();
    }

    @Bean
    public Action<States, Events> actionS2() {
        return new ActionS2();
    }
    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }
}