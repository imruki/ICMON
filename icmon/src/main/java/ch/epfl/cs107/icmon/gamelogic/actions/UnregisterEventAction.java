package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

/**
 * Action for unregistering an event
 */
public class UnregisterEventAction implements Action{

    /**  event that we want to unregister */
    private final ICMonEvent event;

    private final ICMon.ICMonEventManager eventManager;

    /**
     * Default Constructor for unregisterEventAction
     * @param eventManager eventManager so we can unregister an event
     * @param event event that we want to unregister
     */
    public UnregisterEventAction(ICMon.ICMonEventManager eventManager, ICMonEvent event){
        this.eventManager = eventManager;
        this.event = event;
    }
    @Override
    public void perform() {
        eventManager.unregister(event);
    }
}
