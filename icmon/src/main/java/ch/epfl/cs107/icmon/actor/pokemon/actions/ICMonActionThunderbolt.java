package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pikachu;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

/**
 * Pikachu's Signature Move
 */
public class ICMonActionThunderbolt implements ICMonFightAction {
    private final Pokemon.PokemonProperties pikachuProperties;

    public ICMonActionThunderbolt(Pikachu pikachu){
        this.pikachuProperties = pikachu.properties();
    }
    @Override
    public String name() {
        return "Thunderbolt";
    }

    @Override
    public boolean doAction(Pokemon target) {
        target.takeDamage(pikachuProperties.damage()*2);
        return true;
    }
}
