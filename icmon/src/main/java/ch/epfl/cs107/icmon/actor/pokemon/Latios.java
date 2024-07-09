package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.pokemon.actions.ICMonActionAttack;
import ch.epfl.cs107.icmon.actor.pokemon.actions.ICMonActionHeal;
import ch.epfl.cs107.icmon.actor.pokemon.actions.ICMonActionRunaway;
import ch.epfl.cs107.icmon.actor.pokemon.actions.ICMonActionSpecialBeam;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Latios extends Pokemon {

    public Latios(Area owner, Orientation orientation, DiscreteCoordinates coordinates, boolean onArea){
        super(owner, orientation, coordinates, "latios", 2, 20, onArea);
        pokemonActions.add(new ICMonActionAttack(this));
        pokemonActions.add(new ICMonActionSpecialBeam(this));
        pokemonActions.add(new ICMonActionHeal(this));
        pokemonActions.add(new ICMonActionRunaway());
    }

}
