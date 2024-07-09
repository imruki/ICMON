package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

/**
 * Action for registering an event
 */
public class RegisterEventAction implements Action{

    private final ICMonEvent event;

    private final ICMon.ICMonEventManager eventManager;

    public RegisterEventAction(ICMon.ICMonEventManager eventManager, ICMonEvent event){
        this.eventManager = eventManager;
        this.event = event;
    }
    @Override
    public void perform() {
        eventManager.register(event);
    }
}
