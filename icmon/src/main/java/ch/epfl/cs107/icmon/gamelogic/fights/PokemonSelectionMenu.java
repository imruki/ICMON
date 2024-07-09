package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.actor.Graphics;
import ch.epfl.cs107.play.engine.actor.GraphicsEntity;
import ch.epfl.cs107.play.engine.actor.ImageGraphics;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.io.ResourcePath;
import ch.epfl.cs107.play.math.TextAlign;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.awt.*;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Objects.nonNull;

public class PokemonSelectionMenu extends PauseMenu {

    private static final float FONT_SIZE = .6f;

    private final Keyboard keyboard;

    /** List of Pokemons we can choose from */
    private final Pokemon[] pokemons;

    private boolean isRunning;
    private final GraphicsEntity[] selectors;

    private final Graphics header;

    /** pokemon currently selected */
    private Pokemon choice;

    private int currentChoice;

    /**
     * Default constructor
     * @param scaleFactor scale factor
     * @param keyboard keyboard of game's window
     * @param pokemons List of pokemons available
     */
    public PokemonSelectionMenu(float scaleFactor, Keyboard keyboard, List<Pokemon> pokemons) {
        assert !pokemons.isEmpty();
        this.keyboard = keyboard;
        this.pokemons = pokemons.toArray(new Pokemon[0]);
        selectors = new GraphicsEntity[3];
        header = new GraphicsEntity(new Vector(scaleFactor / 2f, scaleFactor / 3 - 1f), new TextGraphics("Please, select a pokemon", FONT_SIZE, Color.WHITE, null, 0.0f, true, false, Vector.ZERO, TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE,  1f, 1003));
        currentChoice = 0;
        isRunning = true;
    }

    @Override
    public void update(float deltaTime) {
        // HR : Keyboard management
        if (keyboard.get(Keyboard.LEFT).isPressed() || keyboard.get(Keyboard.Q).isPressed() || keyboard.get(Keyboard.A).isPressed()){
            currentChoice = max(0, currentChoice - 1);
        } else if (keyboard.get(Keyboard.RIGHT).isPressed() || keyboard.get(Keyboard.RIGHT).isPressed() || keyboard.get(Keyboard.D).isPressed())
            currentChoice = min(currentChoice + 1, pokemons.length - 1);
        else if (keyboard.get(Keyboard.ENTER).isPressed() || keyboard.get(Keyboard.E).isPressed()){
            choice = pokemons[currentChoice];
            end();
        }


        var spriteName = "";
        var scale = CAMERA_SCALE_FACTOR;
        // HR : Prepare the left selector
        if (currentChoice == 0){
            selectors[0] = null;
        } else {
            spriteName = "pokemon/"+ pokemons[currentChoice - 1].properties().name();
            var image = new ImageGraphics(ResourcePath.getSprite(spriteName), scale/2,
                    scale/2);
            image.setAlpha(.6f);
            selectors[0] = new GraphicsEntity(new Vector(scale / 3 - 6f, scale / 2 - 4f),
                    image);
        }

        // HR : Prepare the middle selector
        spriteName = "pokemon/"+ pokemons[currentChoice].properties().name();
        var image = new ImageGraphics(ResourcePath.getSprite(spriteName), scale/2,
                scale/2);
        image.setAlpha(1f);
        selectors[1] = new GraphicsEntity(new Vector(scale / 3 - 1f, scale / 2 - 4f),
                image);

        // HR : Prepare the Right selector
        if (currentChoice == pokemons.length - 1 ){
            selectors[2] = null;
        } else {
            spriteName = "pokemon/"+ pokemons[currentChoice + 1].properties().name();
            image = new ImageGraphics(ResourcePath.getSprite(spriteName), scale/2,
                    scale/2);
            image.setAlpha(.6f);
            selectors[2] = new GraphicsEntity(new Vector(scale / 3 + 4f, scale / 2 - 4f),
                    image);

        }
        super.update(deltaTime);
    }

    public boolean isRunning(){
        return isRunning;
    }

    @Override
    public void end() {
        isRunning = false;
    }

    public Pokemon choice(){
        return choice;
    }
    @Override
    public void drawMenu(Canvas canvas) {
        // HR : Draw the header
        header.draw(canvas);
        // HR : Draw the selectors that are visible (not null)
        for (var selector : selectors)
            if(nonNull(selector))
                selector.draw(canvas);
    }
}
