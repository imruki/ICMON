package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

/**
 * Basic Attack move
 */
public class ICMonActionAttack implements ICMonFightAction {
    private final Pokemon.PokemonProperties pokemonProperties;

    public ICMonActionAttack(Pokemon pokemon){
        this.pokemonProperties = pokemon.properties();
    }
    @Override
    public String name() {
        return "Attack";
    }

    @Override
    public boolean doAction(Pokemon target) {
        target.takeDamage(pokemonProperties.damage());
        return true;
    }
}
