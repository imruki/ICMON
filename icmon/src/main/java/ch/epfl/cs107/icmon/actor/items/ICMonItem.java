package ch.epfl.cs107.icmon.actor.items;

import ch.epfl.cs107.play.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Class for an Item
 */
public abstract class ICMonItem extends CollectableAreaEntity {

    private final RPGSprite itemSprite;

    public ICMonItem(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName){
        super(owner, orientation, coordinates);
        itemSprite = new RPGSprite(spriteName, 1f, 1f, this);
    }
    public ICMonItem(Area owner, DiscreteCoordinates coordinates, String spriteName){
        super(owner, Orientation.DOWN, coordinates);
        itemSprite = new RPGSprite(spriteName, 1f, 1f, this);
    }
    @Override
    public boolean takeCellSpace() {
        return true;
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
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void draw(Canvas canvas) {
        itemSprite.draw(canvas);
    }
}
