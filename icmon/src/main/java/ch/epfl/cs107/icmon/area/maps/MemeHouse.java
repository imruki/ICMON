package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.pokemon.Bulbizarre;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.icmon.area.ICMonArea;

/**
 * Class for the memeHouse area (easterEgg)
 */
public final class MemeHouse extends ICMonArea {
    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(4, 2);
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        //registerActor(new Foreground(this));
        registerActor(new Door(this, "town", new DiscreteCoordinates(20,7),
                new DiscreteCoordinates(4,1), new DiscreteCoordinates(5,1)));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public String getTitle() {
        return "memeHouse";
    }

}