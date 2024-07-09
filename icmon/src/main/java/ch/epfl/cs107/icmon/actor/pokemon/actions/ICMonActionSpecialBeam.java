package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

/**
 * Latios's Signature Move
 */
public class ICMonActionSpecialBeam implements ICMonFightAction {
    private final Pokemon.PokemonProperties latiosProperties;

    public ICMonActionSpecialBeam(Latios latios){
        this.latiosProperties = latios.properties();
    }
    @Override
    public String name() {
        return "Special Beam";
    }

    @Override
    public boolean doAction(Pokemon target) {
        target.takeDamage(latiosProperties.damage()*2);
        return true;
    }
}
