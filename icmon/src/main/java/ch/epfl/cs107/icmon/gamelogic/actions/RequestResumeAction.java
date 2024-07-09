package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;

public class RequestResumeAction implements Action {

    private final ICMon icmon;
    public RequestResumeAction(ICMon icmon){
        this.icmon = icmon;
    }

    @Override
    public void perform() {
        icmon.requestResume();
    }
}
