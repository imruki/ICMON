package ch.epfl.cs107.icmon.handler;

import ch.epfl.cs107.icmon.ICMon;

public class RestartMessage extends GamePlayMessage{

    /** Gamestate getting this message */
    private final ICMon.ICMonGameState gameState;

    public RestartMessage(ICMon.ICMonGameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void process() {
        gameState.restart();
    }
}
