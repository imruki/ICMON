package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.fights.PokemonSelectionMenu;
import ch.epfl.cs107.play.engine.actor.Dialog;

import static ch.epfl.cs107.icmon.ICMon.CAMERA_SCALE_FACTOR;

public class FirstInteractionWithGarryEvent extends ICMonEvent {

    private final ICMon.ICMonGameState gameState;

    /**
     * Default constructor
     * @param mainPlayer target player
     * @param gameState .
     */
    public FirstInteractionWithGarryEvent(ICMonPlayer mainPlayer, ICMon.ICMonGameState gameState){
        super(mainPlayer);
        this.gameState = gameState;
        onStart(new LogAction("First Interaction with Garry Event started"));
        onComplete(new LogAction("First Interaction with Garry Event ended"));
    }
    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void interactWith(Garry garry , boolean isCellInteraction){
        PokemonSelectionMenu selectionMenu = new PokemonSelectionMenu(CAMERA_SCALE_FACTOR, super.mainPlayer.getOwnerArea().getKeyboard(), super.mainPlayer.getPokemons());
        gameState.selectPokemon(garry,selectionMenu);
        complete();
    }

    @Override
    public void interactWith(ICShopAssistant assistant , boolean isCellInteraction){
        super.mainPlayer.openDialog(new Dialog("first_interaction_with_garry_event_icshopassistant"));
    }
}
