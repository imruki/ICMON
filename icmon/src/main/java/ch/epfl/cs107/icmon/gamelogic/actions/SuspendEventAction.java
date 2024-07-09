package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

/**
 * Action for suspending an event
 */
public class SuspendEventAction implements Action{

    private final ICMon.ICMonEventManager eventManager;

    /** event that caused the suspension, we will not suspend it*/
    private final ICMonEvent originEvent;

    /**
     * Default Constructor for unregisterEventAction
     * @param eventManager eventManager so we can register an event
     * @param originEvent event that caused the suspension, we will not suspend it
     */
    public SuspendEventAction(ICMonEvent originEvent, ICMon.ICMonEventManager eventManager){
        this.originEvent = originEvent;
        this.eventManager = eventManager;
    }

    @Override
    public void perform() {
        eventManager.suspendAllEvents(originEvent);
    }
}
