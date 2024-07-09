package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.*;

import java.util.List;

/**
 * Class to chain event between them
 */
public class ICMonChainedEvent extends ICMonEvent {
    /**
     * Default constructor
     * @param mainplayer player
     * @param eventManager managing the event
     * @param firstEvent first event of the chain (mandatory)
     * @param chain every other event (optional)
     */
    public ICMonChainedEvent(ICMonPlayer mainplayer, ICMon.ICMonEventManager eventManager, ICMonEvent firstEvent, List<ICMonEvent> chain){
        super(mainplayer);

        onStart(new RegisterEventAction(eventManager, firstEvent));
        onStart(new StartEventAction(firstEvent));

        firstEvent.onComplete(new RegisterEventAction(eventManager, chain.get(0)));
        firstEvent.onComplete(new StartEventAction(chain.get(0)));
        firstEvent.onComplete(new UnregisterEventAction(eventManager, firstEvent));

        for (int i = 1; i < chain.size(); i++){

            //Register the next event
            chain.get(i-1).onComplete(new RegisterEventAction(eventManager, chain.get(i)));

            //Start nextEvent
            chain.get(i-1).onComplete(new StartEventAction(chain.get(i)));

            //Unregister Itself
            chain.get(i-1).onComplete(new UnregisterEventAction(eventManager, chain.get(i-1)));
        }
        chain.get(chain.size()-1).onComplete(new CompleteEventAction(this));
    }
    @Override
    public void update(float deltaTime) {
    }
}
