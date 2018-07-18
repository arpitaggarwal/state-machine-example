package com.arpit.statemachine.config;

import com.arpit.statemachine.actions.DeliveredAction;
import com.arpit.statemachine.actions.OrderedAction;
import com.arpit.statemachine.actions.ShippedAction;
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

@Configuration
@EnableStateMachine
public class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true);
                //.listener(listener());
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
        states
                .withStates()
                .initial(States.IDLE)
                .state(States.ORDERED, orderedAction())
                .state(States.SHIPPED, shippedAction())
                .state(States.DELIVERED, deliveredAction()).end(States.END);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.IDLE).target(States.ORDERED).event(Events.ORDERED)
                .and()
                .withExternal()
                .source(States.ORDERED).target(States.SHIPPED).event(Events.SHIPPED)
                .and()
                .withExternal()
                .source(States.SHIPPED).target(States.DELIVERED).event(Events.DELIVERED)
                .and()
                .withExternal()
                .source(States.DELIVERED).target(States.END)
        ;
    }

    @Bean
    public Action<States, Events> orderedAction() {
        return new OrderedAction();
    }
    @Bean
    public Action<States, Events> orderedErrorAction() {
        return new OrderedAction();
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
    public Action<States, Events> shippedAction() {
        return new ShippedAction();
    }

    @Bean
    public Action<States, Events> deliveredAction() {
        return new DeliveredAction();
    }

    /*@Bean
    public StateMachineListener<States, Event> listener() {
        return new StateMachineListenerAdapter<States, Event>() {
            @Override
            public void stateChanged(State<States, Event> from, State<States, Event> to) {
                System.out.println("State change to " + to.getId());
            }

            @Override
            public void stateMachineError(StateMachine<States, Event> stateMachine, Exception exception) {
                System.out.println("State Machine Error");
            }

        };
    }*/
}