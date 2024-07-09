package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

/**
 * Class for an NPC
 */
public abstract class NPCActor extends ICMonActor {
    private RPGSprite npcSprite;

    public NPCActor(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String sprite_name){
        super(owner, orientation, coordinates);
        npcSprite = new RPGSprite(sprite_name , 1, 1.3125f, this , new RegionOfInterest(0, 0, 16,
                21));
    }

    @Override
    public void draw(Canvas canvas) {
        npcSprite.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }
}
