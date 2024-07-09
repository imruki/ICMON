package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.area.maps.MemeHouse;
import ch.epfl.cs107.play.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Sound;

/**
 * a class for Actors
 */
public abstract class ICMonActor extends MovableAreaEntity {

    /**
     * Constructor for the Actor
     * @param owner Area of the actor
     * @param orientation Orientation of the actor
     * @param coordinates Position of the actor
     */
    public ICMonActor(Area owner, Orientation orientation, DiscreteCoordinates coordinates) {
        super(owner, orientation, coordinates);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }


    /**
     * Leave an area by unregistering the Actor from the Area
     */
    public void leaveArea() {
        getOwnerArea().unregisterActor(this);
    }

    /**
     * Spawning in a specific Area
     * @param area     (Area): initial area, not null
     * @param position (DiscreteCoordinates): initial position, not null
     */
    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();
        if (this instanceof ICMonPlayer && area instanceof MemeHouse){
            Sound sound = ((MemeHouse) area).getWindow().getSound("whistle.wav");
            ((MemeHouse) area).getWindow().playSound(sound, false, 1f, false, false, true);
        }
    }


}