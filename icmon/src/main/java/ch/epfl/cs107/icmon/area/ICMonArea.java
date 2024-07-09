package ch.epfl.cs107.icmon.area;

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.icmon.ICMon;

/**
 * Class for an area of ICMon
 */
public abstract class ICMonArea extends Area {

    protected ICMonBehavior behavior;
    private Window window;
    protected abstract void createArea();

    /**
     * get the Spawn Position of the player when he enters the area
     * @return DiscreteCoordinates of the spawn position
     */
    public abstract DiscreteCoordinates getPlayerSpawnPosition();

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            behavior = new ICMonBehavior(window, getTitle());
            this.window = window;
            setBehavior(behavior);
            createArea();
            return true;
        }
        return false;
    }

    @Override
    public final float getCameraScaleFactor() {
        return ICMon.CAMERA_SCALE_FACTOR;
    }
    public Window getWindow(){return window;}
}
