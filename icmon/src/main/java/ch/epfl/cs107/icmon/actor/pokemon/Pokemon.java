package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.actions.*;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ???
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 */
public abstract class Pokemon extends ICMonActor implements ICMonFightableActor {

    private final String name;
    private int hp;
    private final int maxHp;
    private final int damage;
    private final RPGSprite pokemonSprite;
    private final PokemonProperties properties;
    protected List<ICMonFightAction> pokemonActions = new ArrayList<ICMonFightAction>();
    private final int hpHeal = 3;
    private boolean onArea;

    public Pokemon(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String name, int damage, int maxHp, boolean onArea){
        super(owner, orientation, coordinates);
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.damage = damage;
        this.pokemonSprite = new RPGSprite("pokemon/" + name, 1, 1, this);
        this.properties = new PokemonProperties();
        this.onArea = onArea;
    }

    public String getName(){
        return this.name;
    }

    public int getHp(){
        return this.hp;
    }

    public int getMaxHp(){
        return this.maxHp;
    }

    public int getDamage(){
        return this.damage;
    }

    public ICMonFightAction getFightActionAt(int index){
        return pokemonActions.get(index);
    }
    public List<ICMonFightAction> getFightActions(){
        List<ICMonFightAction> newList = new ArrayList<ICMonFightAction>();
        for (ICMonFightAction fightAction : pokemonActions){
            if (fightAction instanceof ICMonActionAttack) newList.add(new ICMonActionAttack(this));
            if (fightAction instanceof ICMonActionRunaway) newList.add(new ICMonActionRunaway());
            if (fightAction instanceof ICMonActionHeal) newList.add(new ICMonActionHeal(this));
            if (fightAction instanceof ICMonActionSolarBeam) newList.add(new ICMonActionSolarBeam((Bulbizarre) this));
            if (fightAction instanceof ICMonActionThunderbolt) newList.add(new ICMonActionThunderbolt((Pikachu) this));
            if (fightAction instanceof ICMonActionSpecialBeam) newList.add(new ICMonActionSpecialBeam((Latios) this));
            if (fightAction instanceof ICMonActionHadesFlame) newList.add(new ICMonActionHadesFlame((Dracofeu) this));
        }
        return newList;
    }

    public ICMonFightAction getAttackAction(){
        for (ICMonFightAction fightAction : pokemonActions){
            if (fightAction instanceof ICMonActionAttack){
                return fightAction;
            }
        }
        return null;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public Pokemon getChosenPokemon() {
        return this;
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
    public boolean isViewInteractable() {return false;}

    public void takeDamage(int damage){
        if (damage< hp){
            hp -= damage;
        } else {
            hp = 0;
        }
    }

    public void heal(){
        if (hp < (maxHp-hpHeal)){
            hp += hpHeal;
        }
    }

    public void regenerate(){
        hp = maxHp;
    }

    public boolean isDead(){
        return hp==0;
    }

    @Override
    public void draw(Canvas canvas) {pokemonSprite.draw(canvas);}

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }

    public PokemonProperties properties(){
        return properties;
    }

    /**
     * @author Hamza REMMAL (hamza.remmal@epfl.ch)
     */
    public final class PokemonProperties {

        public String name(){
            return name;
        }

        public float hp(){
            return hp;
        }

        public float maxHp(){
            return maxHp;
        }

        public int damage(){
            return damage;
        }

        public boolean onArea(){return onArea;}
    }

}