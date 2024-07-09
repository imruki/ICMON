package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.handler.RestartMessage;
import ch.epfl.cs107.play.engine.PauseMenu;

/**
 * Action for Restarting the game
 */
public class RestartGameAction implements PauseAction{

    private final ICMon.ICMonGameState gameState;

    private final PauseMenu pauseMenu;

    public RestartGameAction(ICMon.ICMonGameState gameState, PauseMenu pauseMenu){
        this.gameState = gameState;
        this.pauseMenu = pauseMenu;
    }
    @Override
    public void perform() {
        pauseMenu.end();
        gameState.message = new RestartMessage(gameState);
    }

    @Override
    public String getText() {
        return "Restart";
    }
}
