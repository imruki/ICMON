package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Dracofeu;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

/**
 * Charizard's Signature Move
 */
public class ICMonActionHadesFlame implements ICMonFightAction {
    private final Pokemon.PokemonProperties dracofeuProperties;

    public ICMonActionHadesFlame(Dracofeu dracofeu){
        this.dracofeuProperties = dracofeu.properties();
    }
    @Override
    public String name() {
        return "Hades Flame";
    }

    @Override
    public boolean doAction(Pokemon target) {
        target.takeDamage(dracofeuProperties.damage()*2);
        return true;
    }
}
