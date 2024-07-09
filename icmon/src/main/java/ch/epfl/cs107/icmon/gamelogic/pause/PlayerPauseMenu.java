package ch.epfl.cs107.icmon.gamelogic.pause;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.actions.PauseAction;
import ch.epfl.cs107.icmon.gamelogic.actions.QuitPauseAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RestartGameAction;
import ch.epfl.cs107.icmon.gamelogic.actions.ResumePauseAction;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.actor.GraphicsEntity;
import ch.epfl.cs107.play.engine.actor.ImageGraphics;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.math.TextAlign;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.awt.*;

import static ch.epfl.cs107.play.io.ResourcePath.getBackground;
import static java.lang.Math.*;
import static java.util.Objects.nonNull;


/**
 * PauseMenu to model the Pause screen event
 * Allow user to select different actions
 */
public class PlayerPauseMenu extends PauseMenu {

    private final Keyboard keyboard;
    private boolean isRunning;
    private final GraphicsEntity[] selectors;

    /** List of Actions the player can choose in */
    private final PauseAction[] actions = new PauseAction[3];
    private int currentChoice;
    private final ICMon.ICMonEventManager eventManager;
    private final ImageGraphics background;

    /**
     * Default constructor
     *
     * @param eventManager EventManager of the game
     * @param keyboard Keyboard of the game's window
     * @param gameState Gamestate to get access to methods
     */
    public PlayerPauseMenu(ICMon.ICMonEventManager eventManager, Keyboard keyboard, ICMon.ICMonGameState gameState) {
        this.eventManager = eventManager;
        this.keyboard = keyboard;
        selectors = new GraphicsEntity[3];
        currentChoice = 1;
        isRunning = true;

        background = new ImageGraphics(getBackground("pauseScreen"), 13, 13);

        actions[0] = new RestartGameAction(gameState, this);
        actions[1] = new ResumePauseAction(this);
        actions[2] = new QuitPauseAction(gameState);
    }


    @Override
    public void update(float deltaTime) {
        // HR : Keyboard management
        if (keyboard.get(Keyboard.LEFT).isPressed() || keyboard.get(Keyboard.Q).isPressed() || keyboard.get(Keyboard.A).isPressed()){
            currentChoice = max(0, currentChoice - 1);
        } else if (keyboard.get(Keyboard.RIGHT).isPressed() || keyboard.get(Keyboard.RIGHT).isPressed() || keyboard.get(Keyboard.D).isPressed())
            currentChoice = min(currentChoice + 1, actions.length - 1);
        else if (keyboard.get(Keyboard.ENTER).isPressed() || keyboard.get(Keyboard.E).isPressed()){
            // Action the player wants to do
            PauseAction choice = actions[currentChoice];
            if(currentChoice == 1){
                eventManager.resumeAllEvents();
            }
            choice.perform();
        }


        var actionName = "";
        var scale = CAMERA_SCALE_FACTOR;
        // HR : Prepare the left selector
        if (currentChoice == 0){
            selectors[0] = null;
        } else {
            actionName = actions[currentChoice - 1].getText();
            var text = new TextGraphics(actionName, .8f, Color.BLACK, null, 0.0f, false, false, Vector.ZERO, TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE,  1f, 1003);
            text.setAlpha(.6f);
            selectors[0] = new GraphicsEntity(new Vector(scale / 3 - 1f, scale / 2), text);
        }

        // HR : Prepare the middle selector
        actionName = actions[currentChoice].getText();
        var text = new TextGraphics(actionName, 1f, Color.BLACK, null, 0.0f, true, false, Vector.ZERO, TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE,  1f, 1003);
        text.setAlpha(1f);
        selectors[1] = new GraphicsEntity(new Vector(scale / 3 + 2f, scale / 2), text);

        // HR : Prepare the Right selector
        if (currentChoice == actions.length - 1 ){
            selectors[2] = null;
        } else {
            actionName = actions[currentChoice + 1].getText();
            text = new TextGraphics(actionName, .8f, Color.BLACK, null, 0.0f, false, false, Vector.ZERO, TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE,  1f, 1003);
            text.setAlpha(.6f);
            selectors[2] = new GraphicsEntity(new Vector(scale / 3 + 5f, scale / 2), text);

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

    @Override
    protected void drawMenu(Canvas c) {
        // HR : Draw the selectors that are visible (not null)
        for (var selector : selectors)
            if(nonNull(selector))
                selector.draw(c);
        background.draw(c);
    }
}
