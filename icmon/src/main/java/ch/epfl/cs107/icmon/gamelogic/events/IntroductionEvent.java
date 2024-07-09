package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.actions.OpenDialogAction;

/**
 * Introduction event
 */
public class IntroductionEvent extends ICMonEvent {

    /**
     * Default constructor
     * @param mainplayer mainplayer of the game
     */
    public IntroductionEvent(ICMonPlayer mainplayer){
        super(mainplayer);
        onStart(new LogAction("Introduction Event started"));
        onComplete(new LogAction("Introduction Event ended"));
        onStart(new OpenDialogAction(mainPlayer, "welcome_to_icmon"));
    }

    @Override
    public void update(float deltaTime) {
        complete();
    }
}
