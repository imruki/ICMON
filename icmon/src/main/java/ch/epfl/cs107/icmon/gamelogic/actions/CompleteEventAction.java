package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

/**
 * Action for completing an event
 */
public class CompleteEventAction implements Action{
    private final ICMonEvent event;

    public CompleteEventAction(ICMonEvent event){
        this.event = event;
    }

    @Override
    public void perform() {
        event.complete();
    }
}
