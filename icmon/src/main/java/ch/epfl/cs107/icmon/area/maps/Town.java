package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.Sign;
import ch.epfl.cs107.icmon.actor.pokemon.Carabaffe;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.math.Orientation;

import java.util.List;
import java.util.Random;
import java.util.random.*;

/**
 * Class for the Town area
 */
public final class Town extends ICMonArea {
    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(5, 15);
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new Door(this, "lab", new DiscreteCoordinates(6,2),
                    new DiscreteCoordinates(15,24)));
        registerActor(new Door(this, "arena", new DiscreteCoordinates(4,2),
                new DiscreteCoordinates(20,16)));
        registerActor(new Door(this, "house", new DiscreteCoordinates(2,2),
                new DiscreteCoordinates(7,27)));
        registerActor(new Door(this, "shop", new DiscreteCoordinates(3,2),
                new DiscreteCoordinates(25,20)));
        registerActor(new Door(this, "memeHouse", new DiscreteCoordinates(4,2),
                new DiscreteCoordinates(20,8)));
        registerActor(new Door(this, "pikachuHouse", new DiscreteCoordinates(4,2),
                new DiscreteCoordinates(10,13)));
        registerActor(new Sign(this, Orientation.UP, new DiscreteCoordinates(17,16), "arena_sign"));

        List<DiscreteCoordinates> waterCells = behavior.getCellsOfType(ICMonBehavior.ICMonCellType.WATER);
        registerActor(new Carabaffe(this, Orientation.UP,
                waterCells.get(new Random().nextInt(waterCells.size())), true));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public String getTitle() {
        return "town";
    }

}
