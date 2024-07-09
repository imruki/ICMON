package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.pokemon.actions.ICMonActionAttack;
import ch.epfl.cs107.icmon.actor.pokemon.actions.ICMonActionHeal;
import ch.epfl.cs107.icmon.actor.pokemon.actions.ICMonActionRunaway;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Carabaffe extends Pokemon {

    public Carabaffe(Area owner, Orientation orientation, DiscreteCoordinates coordinates, boolean onArea){
        super(owner, orientation, coordinates, "carabaffe", 1, 10, onArea);
        pokemonActions.add(new ICMonActionAttack(this));
        pokemonActions.add(new ICMonActionHeal(this));
        pokemonActions.add(new ICMonActionRunaway());
    }

}
