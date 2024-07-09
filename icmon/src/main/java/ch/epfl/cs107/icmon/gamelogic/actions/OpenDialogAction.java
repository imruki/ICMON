package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.play.engine.actor.Dialog;

/**
 * Action for Opening a dialog
 */
public class OpenDialogAction implements Action{
    private final ICMonPlayer mainPlayer;
    private final String dialog;

    public OpenDialogAction(ICMonPlayer mainPlayer, String dialog){
        this.mainPlayer = mainPlayer;
        this.dialog = dialog;
    }

    @Override
    public void perform() {
        mainPlayer.openDialog(new Dialog(dialog));
    }
}
