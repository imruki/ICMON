package ch.epfl.cs107.icmon.gamelogic.actions;

/**
 * Interface for pausing the game
 */
public interface PauseAction extends Action{

    /** getter for the Action to perform
     * @return String : "Quit", "Resume" or "Pause"
     * */
    String getText();

}
