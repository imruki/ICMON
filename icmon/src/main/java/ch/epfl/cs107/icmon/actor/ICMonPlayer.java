package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.icmon.gamelogic.fights.PokemonSelectionMenu;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.icmon.handler.PassDoorMessage;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.actor.Interactor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;

import static ch.epfl.cs107.icmon.ICMon.CAMERA_SCALE_FACTOR;

/**
 * Class for the main Player
 */
public final class ICMonPlayer extends ICMonActor implements Interactor {

    /** Duration for the animation (Time between each frame) */
    private final static int ANIMATION_DURATION = 8;
    private final OrientedAnimation groundAnimation = new OrientedAnimation("actors/player",
            ANIMATION_DURATION/2, Orientation.DOWN, this);
    private final OrientedAnimation waterAnimation = new OrientedAnimation("actors/player_water",
            ANIMATION_DURATION/2, Orientation.DOWN, this);
    private OrientedAnimation currentAnimation = groundAnimation;

    private final Keyboard keyboard = getOwnerArea().getKeyboard();

    private final ICMonPlayerInteractionHandler handler = new ICMonPlayerInteractionHandler();

    private final ICMon.ICMonGameState gameState;

    private Dialog dialog;

    private final List<Pokemon> pokemons;
    private boolean isTalking = false;

    private Pokemon chosenPokemon;

    public ICMonPlayer(Area owner, Orientation orientation, DiscreteCoordinates coordinates, ICMon.ICMonGameState gameState) {
        super(owner, orientation, coordinates);
        this.gameState = gameState;
        pokemons = new ArrayList<>();
    }

    @Override
    public void update(float deltaTime) {

        moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT), keyboard.get(Keyboard.Q), keyboard.get(Keyboard.A));
        moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP), keyboard.get(Keyboard.Z), keyboard.get(Keyboard.W));
        moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT), keyboard.get(Keyboard.D));
        moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN), keyboard.get(Keyboard.S));

        if(isDisplacementOccurs())currentAnimation.update(deltaTime);
        else currentAnimation.reset();

        if(isTalking){ updateDialog(dialog, deltaTime); }
        super.update(deltaTime);
    }

    @Override
    public boolean takeCellSpace() {return true;}

    @Override
    public boolean isViewInteractable() {return false;}

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }

    /**
     * Orientate and Move this player in the given orientation if the given button is down
     * @param orientation (Orientation): given orientation, not null
     * @param buttons (Button): button corresponding to the given orientation, not null
     */
    private void moveIfPressed(Orientation orientation, Button... buttons) {
        for(Button b : buttons) {
            if (b.isDown() && !isTalking) {
                if (!isDisplacementOccurs()) {
                    orientate(orientation);
                    currentAnimation.orientate(orientation);
                    move(ANIMATION_DURATION);
                }
            }
        }
    }

    /**
     * Center the camera on the player
     */
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }

    @Override
    public void draw(Canvas canvas) {
        currentAnimation.draw(canvas);
        if(isTalking){
            dialog.draw(canvas);
        }
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        return (keyboard.get(Keyboard.L).isDown() || keyboard.get(Keyboard.F).isDown()) && !isTalking;
    }

    @Override
    public void interactWith(Interactable other, boolean isCellInteraction) {
        try {
            other.acceptInteraction(handler, isCellInteraction);
            gameState.acceptInteraction(other, isCellInteraction);
        } catch (ConcurrentModificationException e){
            System.out.println("Test");
        }
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     * update method for a dialog
     * @param dialog dialog we want to update
     * @param dt elapsed time since last update, in seconds, non-negative
     */
    private void updateDialog(Dialog dialog, float dt){
        if(dialog.isCompleted()){
            isTalking = false;
        }
        else{
            if (keyboard.get(Keyboard.SPACE).isPressed()) {
                dialog.update(dt);
            }
        }
    }

    /**
     * Starting a dialog
     * @param dialog dialog we want to open
     */
    public void openDialog(Dialog dialog){
        this.dialog = dialog;
        isTalking = true;
    }

    /** recieving a pokemon, so we can add it to the mainPlayer's collection
     * @param pokemon pokemon that we will add to the mainPlayer's collection
     * */
    public void recievePokemon(Pokemon pokemon){
        pokemons.add(pokemon);
    }

    /** getter for the pokemons list */
    public List<Pokemon> getPokemons(){
        List<Pokemon> pokemonsCopy = new ArrayList<>(pokemons.size());
        for(Pokemon p: pokemons){
            pokemonsCopy.add(p);
        }
        return pokemonsCopy;
    }

    /** getter for the chosen Pokemon */
    public Pokemon getChosenPokemon(){return chosenPokemon;}

    /** setter for the chosen Pokemon */
    public void setChosenPokemon(Pokemon chosen){chosenPokemon = chosen;}

    @Override
    public Area getOwnerArea() {
        return super.getOwnerArea();
    }

    private class ICMonPlayerInteractionHandler implements ICMonInteractionVisitor{

        @Override
        public void interactWith(ICMonBehavior.ICMonCell icmonCell, boolean isCellInteraction) {
            if(icmonCell.getWalkingType().equals(ICMonBehavior.AllowedWalkingType.FEET)){
                currentAnimation = groundAnimation;
            }
            if(icmonCell.getWalkingType().equals(ICMonBehavior.AllowedWalkingType.SURF)){
                currentAnimation = waterAnimation;
            }
        }

        @Override
        public void interactWith(ICBall ball, boolean isCellInteraction) {
            if(wantsViewInteraction()){
                ball.collect();
            }
        }

        @Override
        public void interactWith(Door door, boolean isCellInteraction) {
            if (isCellInteraction) {
                gameState.message = new PassDoorMessage(ICMonPlayer.this, door, gameState);
            }
        }

        @Override
        public void interactWith(Pokemon pokemon, boolean isCellInteraction) {
            if (isCellInteraction) {
                if(!pokemons.isEmpty()){
                    PokemonSelectionMenu selectionMenu = new PokemonSelectionMenu(CAMERA_SCALE_FACTOR, getOwnerArea().getKeyboard(), pokemons);
                    gameState.selectPokemon(pokemon, selectionMenu);
                } else {
                    ICMonPlayer.this.openDialog(new Dialog("no_pokemon_assert"));
                    ICMonPlayer.this.setCurrentPosition(ICMonPlayer.this.getLeftCells().get(0).toVector());
                }
            }
        }

        @Override
        public void interactWith(Sign sign, boolean isCellInteraction){
            if(wantsViewInteraction()){
                ICMonPlayer.this.openDialog(new Dialog(sign.getDialogText()));
            }
        }
    }

}
