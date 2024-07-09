package ch.epfl.cs107.icmon.handler;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;

public class PassDoorMessage extends GamePlayMessage{

    /** Player who want to cross the door */
    private final ICMonPlayer player;

    /** Door used by the player */
    private final Door door;

    /** Gamestate getting this message */
    private final ICMon.ICMonGameState gameState;

    public PassDoorMessage(ICMonPlayer player, Door door, ICMon.ICMonGameState gameState){
        this.player = player;
        this.door = door;
        this.gameState = gameState;
    }

    @Override
    public void process() {
        gameState.switchArea(player, door.getTargetedArea(), door.getTargetedPosition());
    }
}
