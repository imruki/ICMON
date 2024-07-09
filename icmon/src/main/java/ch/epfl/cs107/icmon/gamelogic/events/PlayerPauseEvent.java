package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.pause.PlayerPauseMenu;

/**
 * Event for pausing the game
 */
public class PlayerPauseEvent extends ICMonEvent {

    private final PlayerPauseMenu pauseMenu;

    /**
     * Default constructor
     * @param pauseMenu menu doing the pause
     * @param mainPlayer player calling the pause
     */
    public PlayerPauseEvent(PlayerPauseMenu pauseMenu, ICMonPlayer mainPlayer) {
        super(mainPlayer);
        this.pauseMenu = pauseMenu;
    }

    @Override
    public void update(float deltaTime) {
        if(!pauseMenu.isRunning()){
            this.complete();
        }
    }

    public PlayerPauseMenu getPauseMenu() {
        return pauseMenu;
    }

}
