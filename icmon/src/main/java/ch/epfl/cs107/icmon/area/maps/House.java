package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.icmon.area.ICMonArea;

/**
 * Class for the House area of the mainPlayer
 */
public final class House extends ICMonArea {
    Garry garry = new Garry(this, Orientation.DOWN, new DiscreteCoordinates(1,3));

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(4, 2);
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(garry);
        registerActor(new Door(this, "town", new DiscreteCoordinates(7,26),
                new DiscreteCoordinates(3,1), new DiscreteCoordinates(4,1)));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(garry.getChosenPokemon().isDead() && this.exists(garry)){garry.leaveArea();}
    }

    @Override
    public String getTitle() {
        return "house";
    }

}