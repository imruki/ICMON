package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.play.engine.PauseMenu;

/**
 * Action for Resuming from the pause menu
 */
public class ResumePauseAction implements PauseAction{
    private final PauseMenu pauseMenu;
    public ResumePauseAction(PauseMenu pauseMenu){
        this.pauseMenu = pauseMenu;
    }
    @Override
    public void perform() {
        pauseMenu.end();
    }

    @Override
    public String getText() {
        return "Resume";
    }
}
