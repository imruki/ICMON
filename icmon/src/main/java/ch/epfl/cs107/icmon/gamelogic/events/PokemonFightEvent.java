package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;

/**
 * Event called on pokemon fight
 */
public class PokemonFightEvent extends ICMonEvent{

    private final ICMonFight pauseMenu;

    /**
     * Default constructor
     * @param pauseMenu menu doing the fight
     * @param mainPlayer player calling the fight
     */
    public PokemonFightEvent(ICMonFight pauseMenu, ICMonPlayer mainPlayer) {
        super(mainPlayer);
        this.pauseMenu = pauseMenu;
    }

    @Override
    public void update(float deltaTime) {
        if(!pauseMenu.isRunning()){
            this.complete();
        }
    }

    public ICMonFight getPauseMenu() {
        return pauseMenu;
    }
}
