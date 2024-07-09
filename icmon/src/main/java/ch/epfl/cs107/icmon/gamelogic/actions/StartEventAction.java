package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

/**
 * Action for Starting an event
 */
public class StartEventAction implements Action{

    /** event that we want to start */
    private final ICMonEvent event;

    /**
     * Default Constructor for startEventAction
     * @param event event that we want to start
     */
    public StartEventAction(ICMonEvent event){
        this.event = event;
    }

    @Override
    public void perform() {
        event.start();
    }
}
