package com.arpit.statemachine.config;

import com.arpit.statemachine.actions.ActionS1;
import com.arpit.statemachine.actions.ActionS2;
import com.arpit.statemachine.actions.ActionS3;
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

import java.util.ArrayList;
import java.util.List;

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

        // Single State Change
      /*  states
                .withStates()
                .initial(States.IDLE)
                .end(States.END)
                .states(EnumSet.allOf(States.class));*/

      // Hierarchical States - Only last action executes.
      /*  states
                .withStates()
                .initial(States.S1)
                .state(States.S1, actionS1())
                .and()
                .withStates()
                    .parent(States.S1)
                    .initial(States.S2)
                    .state(States.S2, actionS2())
                    .and()
                    .withStates()
                        .parent(States.S2)
                        .initial(States.S3)
                        .state(States.S3 , actionS3())
                        .end(States.END);*/
        List S2Actions= new ArrayList<>();
        S2Actions.add(actionS1());
        S2Actions.add(actionS2());
        S2Actions.add(initialize());
        states
                .withStates()
                .initial(States.IDLE)
                .state(States.S1, actionS1())
                .state(States.S2, S2Actions)
                .state(States.S3, actionS3());
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.IDLE).target(States.S1).event(Events.INITIALIZE).action(initialize())
                .and()
                .withExternal()
                .source(States.S1).target(States.S2).event(Events.E1)
                .and()
                .withExternal()
                .source(States.S2).target(States.S3).event(Events.E2)
        ;
    }

    @Bean
    public Action<States, Events> actionS1() {
        return new ActionS1();
    }

    @Bean
    public Action<States, Events> initialize() {
        return new Action<States, Events>() {

            @Override
            public void execute(StateContext<States, Events> context) {
                System.out.println("initialize.....");
            }
        };
    }

    @Bean
    public Action<States, Events> actionS2() {
        return new ActionS2();
    }

    @Bean
    public Action<States, Events> actionS3() {
        return new ActionS3();
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