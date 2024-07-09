package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Bulbizarre;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

/**
 * Bulbasaur's Signature Move
 */
public class ICMonActionSolarBeam implements ICMonFightAction {
    private final Pokemon.PokemonProperties bulbizarreProperties;

    public ICMonActionSolarBeam(Bulbizarre bulbizarre){
        this.bulbizarreProperties = bulbizarre.properties();
    }
    @Override
    public String name() {
        return "Solar Beam";
    }

    @Override
    public boolean doAction(Pokemon target) {
        target.takeDamage(bulbizarreProperties.damage()*3);
        return true;
    }
}
