package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.icmon.gamelogic.fights.PokemonSelectionMenu;
import ch.epfl.cs107.icmon.handler.SuspendWithEvent;
import ch.epfl.cs107.play.window.Sound;

/**
 * Action for starting the fight after selecting the pokemon
 */
public class AfterPokemonSelectionFightAction implements Action{
    private final ICMon icmon;
    private final ICMon.ICMonGameState gameState;
    private final ICMon.ICMonEventManager eventManager;
    private final ICMonFightableActor opponent;
    private final ICMonPlayer mainPlayer;
    private final PokemonSelectionMenu selectionMenu;

    public AfterPokemonSelectionFightAction(ICMon.ICMonEventManager eventManager, ICMon icmon, ICMon.ICMonGameState gameState, ICMonPlayer mainPlayer, PokemonSelectionMenu selectionMenu, ICMonFightableActor opponent){
        this.eventManager = eventManager;
        this.icmon = icmon;
        this.gameState = gameState;
        this.opponent = opponent;
        this.mainPlayer = mainPlayer;
        this.selectionMenu = selectionMenu;
    }
    @Override
    public void perform() {
        mainPlayer.setChosenPokemon(selectionMenu.choice());
        Sound sound = ((ICMonArea)mainPlayer.getOwnerArea()).getWindow().getSound("BattleSound.wav");
        ((ICMonArea)mainPlayer.getOwnerArea()).getWindow().playSound(sound, false, 1f, false, false, true);
        ICMonFight fightMenu;
        if (opponent instanceof Garry){
            fightMenu = new ICMonFight(mainPlayer.getChosenPokemon(), opponent.getChosenPokemon(), mainPlayer.getOwnerArea());
        } else {
            fightMenu = new ICMonFight(mainPlayer.getChosenPokemon(), (Pokemon) opponent, mainPlayer.getOwnerArea());
        }

        PokemonFightEvent fightEvent = new PokemonFightEvent(fightMenu,mainPlayer);
        fightEvent.onStart(new RegisterEventAction(eventManager,fightEvent));

        if(opponent instanceof Pokemon && ((Pokemon) opponent).properties().onArea()){
            ((Pokemon) opponent).regenerate();
            mainPlayer.recievePokemon((Pokemon) opponent);
        }

        mainPlayer.getChosenPokemon().regenerate();
        fightEvent.onComplete(new LeaveAreaAction((ICMonActor) opponent));
        fightEvent.onComplete(new ResumeEventAction(eventManager));
        gameState.message = new SuspendWithEvent(fightEvent, icmon, eventManager);
    }
}
