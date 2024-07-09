package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Arrays;
import java.util.List;

/**
 * Class for ICBall
 */
public class Door extends AreaEntity {

    /** Area to which we would like to go */
    private final String targetedArea;

    /** Position to which we would like to spwan in the targeted Area */
    private final DiscreteCoordinates targetedPosition;

    private final List<DiscreteCoordinates> currentCells;
    public Door(Area owner, String targetedArea, DiscreteCoordinates targetedPosition, DiscreteCoordinates... currentCells) {
        super(owner, Orientation.UP, currentCells[0]);
        this.targetedArea = targetedArea;
        this.targetedPosition = targetedPosition;
        this.currentCells = Arrays.stream(currentCells).toList();
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return currentCells;
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

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }

    @Override
    public void draw(Canvas canvas) {}

    /** getter for targetedArea */
    public String getTargetedArea(){
        return targetedArea;
    }

    /** getter for targetedPosition */
    public DiscreteCoordinates getTargetedPosition(){
        return new DiscreteCoordinates(targetedPosition.x, targetedPosition.y);
    }
}
