package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;

/**
 * Action for resuming all events that are registered
 */
public class ResumeEventAction implements Action{

    private final ICMon.ICMonEventManager eventManager;

    public ResumeEventAction(ICMon.ICMonEventManager eventManager){
        this.eventManager = eventManager;
    }

    @Override
    public void perform() {
        eventManager.resumeAllEvents();
    }
}
