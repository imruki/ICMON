package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.fights.PokemonSelectionMenu;


/**
 * Event called on pokemon selection
 */
public class PokemonSelectionEvent extends ICMonEvent{

    private final PokemonSelectionMenu pauseMenu;

    /**
     * Default constructor
     * @param pauseMenu menu doing the selection
     * @param mainPlayer player calling the selection
     */
    public PokemonSelectionEvent(PokemonSelectionMenu pauseMenu, ICMonPlayer mainPlayer) {
        super(mainPlayer);
        this.pauseMenu = pauseMenu;
    }

    @Override
    public void update(float deltaTime) {
        if(!pauseMenu.isRunning()){
            this.complete();
        }
    }

    public PokemonSelectionMenu getPauseMenu() {
        return pauseMenu;
    }
}
