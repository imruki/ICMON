package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Actor;

/**
 * Action for registering an actor in an area
 */
public class RegisterinAreaAction implements Action {
    private final Actor actor;
    private final ICMonArea area;

    public RegisterinAreaAction(ICMonArea area, Actor actor){
        this.actor = actor;
        this.area = area;
    }

    @Override
    public void perform() {
        area.registerActor(actor);
    }
}
