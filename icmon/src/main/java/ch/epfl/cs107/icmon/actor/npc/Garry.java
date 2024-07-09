package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Bulbizarre;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.actor.pokemon.Nidoqueen;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for Garry
 */
public class Garry extends NPCActor implements ICMonFightableActor {
    private final List<Pokemon> pokemons;
    public Garry(Area owner, Orientation orientation, DiscreteCoordinates coordinates){
        super(owner, orientation, coordinates, "actors/garry");
        pokemons = new ArrayList<>();
        pokemons.add(new Nidoqueen(owner, Orientation.DOWN, new DiscreteCoordinates(6,6), false));
        pokemons.add(new Bulbizarre(owner, Orientation.DOWN, new DiscreteCoordinates(6,6), false));
        pokemons.add(new Latios(owner, Orientation.DOWN, new DiscreteCoordinates(6,6), false));
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }

    @Override
    public Pokemon getChosenPokemon(){
        Random rand = new Random();
        return pokemons.get(rand.nextInt(0,pokemons.size()));
    }

}
