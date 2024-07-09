package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.play.engine.actor.Dialog;

/**
 * Last event of the game
 */
public class EndOfTheGameEvent extends ICMonEvent{

    /**
     * default constructor
     * @param mainPlayer main player
     */
    public EndOfTheGameEvent(ICMonPlayer mainPlayer) {
        super(mainPlayer);
        onStart(new LogAction("Ending Event started"));
        onComplete(new LogAction("Ending Event ended"));
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        super.mainPlayer.openDialog(new Dialog("end_of_game_event_interaction_with_icshopassistant"));
        System.out.println("I heard that you were able to implement this step successfully. Congrats !");
        complete();
    }
}
