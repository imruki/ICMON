package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class FirstInteractionWithProfOakEvent extends ICMonEvent {
    /**
     * Default constructor
     * @param mainplayer main player
     */
    public FirstInteractionWithProfOakEvent(ICMonPlayer mainplayer){
        super(mainplayer);
        onStart(new LogAction("First Interaction with Oak Event started"));
        onComplete(new LogAction("First Interaction with Oak Event ended"));
    }
    @Override
    public void update(float deltaTime) {
    }

    /**
     * Interaction with prof during this event
     * @param oak professor oak
     * @param isCellInteraction isCellInteraction
     */
    @Override
    public void interactWith(ProfOak oak , boolean isCellInteraction){
        super.mainPlayer.openDialog(new Dialog("first_interaction_with_prof_oak"));
        System.out.println("This is the first interaction between the player and ProfOak based on events !");
        super.mainPlayer.recievePokemon(new Latios(super.mainPlayer.getOwnerArea(), Orientation.DOWN, new DiscreteCoordinates(6,6), false));
        complete();
    }

    /**
     * Interaction with assistant during this event
     * @param assistant Shop assistant
     * @param isCellInteraction isCellInteraction
     */
    @Override
    public void interactWith(ICShopAssistant assistant , boolean isCellInteraction){
        super.mainPlayer.openDialog(new Dialog("first_interaction_with_oak_event_icshopassistant"));
    }
}
