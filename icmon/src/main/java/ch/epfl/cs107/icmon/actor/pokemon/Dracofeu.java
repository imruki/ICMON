package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.pokemon.actions.ICMonActionAttack;
import ch.epfl.cs107.icmon.actor.pokemon.actions.ICMonActionHadesFlame;
import ch.epfl.cs107.icmon.actor.pokemon.actions.ICMonActionHeal;
import ch.epfl.cs107.icmon.actor.pokemon.actions.ICMonActionRunaway;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Dracofeu extends Pokemon {

    public Dracofeu(Area owner, Orientation orientation, DiscreteCoordinates coordinates, boolean onArea){
        super(owner, orientation, coordinates, "dracofeu", 1, 10, onArea);
        pokemonActions.add(new ICMonActionHadesFlame(this));
        pokemonActions.add(new ICMonActionAttack(this));
        pokemonActions.add(new ICMonActionHeal(this));
    }

}
