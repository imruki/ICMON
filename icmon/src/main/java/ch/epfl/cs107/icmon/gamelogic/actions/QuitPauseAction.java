package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;

/**
 * Action for quitting the game
 */
public class QuitPauseAction implements PauseAction{

    private final ICMon.ICMonGameState gameState;

    public QuitPauseAction(ICMon.ICMonGameState gameState){this.gameState = gameState;}
    @Override
    public void perform() {
        gameState.close();

    }

    @Override
    public String getText() {
        return "Quit";
    }
}
