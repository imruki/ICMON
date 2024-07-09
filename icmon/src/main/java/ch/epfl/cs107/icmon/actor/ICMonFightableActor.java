package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.play.areagame.actor.Interactable;

/**
 * Interface for a fightable actor (Pokemons,Npcs like garry etc)
 */
public interface ICMonFightableActor extends Interactable {

    /** get the Pokemon of the opponent */
    Pokemon getChosenPokemon();
}
