package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

/**
 * Healing a pokemon
 */
public class ICMonActionHeal implements ICMonFightAction {
    private final Pokemon pokemon;

    public ICMonActionHeal(Pokemon pokemon){
        this.pokemon = pokemon;
    }
    @Override
    public String name() {
        return "Heal";
    }

    @Override
    public boolean doAction(Pokemon target) {
        pokemon.heal();
        return true;
    }


}
