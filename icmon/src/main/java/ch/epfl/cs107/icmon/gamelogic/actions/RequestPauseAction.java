package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;

public class RequestPauseAction implements Action {

    private final ICMon icmon;
    public RequestPauseAction(ICMon icmon){
        this.icmon = icmon;
    }

    @Override
    public void perform() {
        icmon.requestPause();
    }
}
