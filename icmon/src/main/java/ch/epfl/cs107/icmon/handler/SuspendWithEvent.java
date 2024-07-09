package ch.epfl.cs107.icmon.handler;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.actions.RequestPauseAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RequestResumeAction;
import ch.epfl.cs107.icmon.gamelogic.actions.SuspendEventAction;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

/**
 * Suspension Event
 */
public class SuspendWithEvent extends GamePlayMessage{

    /** Event asking to suspend the other events */
    private final ICMonEvent event;

    /** Game in which the events are */
    private final ICMon icmon;

    /** Eventmanager managing the events in icmon */
    private final ICMon.ICMonEventManager eventManager;

    public SuspendWithEvent(ICMonEvent event, ICMon icmon, ICMon.ICMonEventManager eventManager){
        this.event = event;
        this.icmon = icmon;
        this.eventManager = eventManager;
    }

    @Override
    public void process(){
        if(event.getPauseMenu() != null) {
            eventManager.setPauseMenu(event.getPauseMenu());
            event.onStart(new RequestPauseAction(icmon));
            event.onStart(new SuspendEventAction(event, eventManager));
            event.onComplete(new RequestResumeAction(icmon));
            event.start();
        }

    }
}
