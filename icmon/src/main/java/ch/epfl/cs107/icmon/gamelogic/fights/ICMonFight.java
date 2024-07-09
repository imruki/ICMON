package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.graphics.ICMonFightActionSelectionGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class ICMonFight extends PauseMenu {
    private boolean isRunning = true;
    private final Pokemon player;
    private final Pokemon opponent;
    private final ICMonFightArenaGraphics arenaGraphics;
    private FightState currentFightState;
    private ICMonFightAction currentAction;
    private String fightText;
    private final Area arena;
    private ICMonFightActionSelectionGraphics actionSelectionMenu;


    /**
     * Default constructor
     * @param player player's pokemon
     * @param opponent opponent's pokemon
     * @param arena area to display pokemon in fight and other component
     */
    public ICMonFight(Pokemon player, Pokemon opponent, Area arena){
        this.player = player;
        this.opponent = opponent;
        this.arena = arena;
        arenaGraphics = new ICMonFightArenaGraphics(CAMERA_SCALE_FACTOR , this.player.properties(),
                this.opponent.properties());
        currentFightState = FightState.INTRODUCTION;
    }

    @Override
    public void update(float deltaTime) {
        switch (currentFightState){
            case INTRODUCTION-> {
                fightText = "Welcome to this Fight";
                showText(fightText);
                actionSelectionMenu = new ICMonFightActionSelectionGraphics(CAMERA_SCALE_FACTOR, arena.getKeyboard(), player.getFightActions());
                if (getKeyboard().get(Keyboard.SPACE).isPressed()) {
                    currentFightState = FightState.ACTION_SELECTION;
                }
            }

            case ACTION_SELECTION->{
                arenaGraphics.setInteractionGraphics(actionSelectionMenu);
                actionSelectionMenu.update(deltaTime);
                currentAction = actionSelectionMenu.choice();
                if (currentAction != null) {
                    currentFightState = FightState.ACTION_EXECUTION;
                    actionSelectionMenu = new ICMonFightActionSelectionGraphics(CAMERA_SCALE_FACTOR, arena.getKeyboard(), player.getFightActions());

                }
            }

            case ACTION_EXECUTION->{
                if (currentAction.doAction(opponent)){
                    if (opponent.isDead()){
                        fightText = "The player has won the fight";
                        currentFightState = FightState.CONCLUSION;
                    } else {
                        currentFightState = FightState.OPPONENT_ACTION;
                    }
                } else{
                    fightText = "The player decided not to continue the fight";
                    currentFightState = FightState.CONCLUSION;
                }
            }

            case OPPONENT_ACTION->{
                currentAction = opponent.getAttackAction();
                if (currentAction != null && currentAction.doAction(player)){
                    if (player.isDead()){
                        fightText = "The opponent has won the fight";
                        currentFightState = FightState.CONCLUSION;
                    } else{
                        currentFightState = FightState.ACTION_SELECTION;
                    }
                } else{
                    fightText = "The opponent decided not to continue the fight";
                    currentFightState = FightState.CONCLUSION;
                }
            }

            case CONCLUSION->{
                showText(fightText);
                if(getKeyboard().get(Keyboard.SPACE).isPressed()) {
                    end();
                }
            }
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

    /**
     * Method to set a text in dialog box of arena
     * @param text string we want to display
     */
    public void showText(String text){
        if(text != null){
            arenaGraphics.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR ,
                    text));
        } else {
            arenaGraphics.setInteractionGraphics(null);
        }
    }

    @Override
    protected void drawMenu(Canvas c) {
        arenaGraphics.draw(c);
    }

    /**
     * Type of different fight state
     */
    enum FightState{
        INTRODUCTION, ACTION_SELECTION, ACTION_EXECUTION, OPPONENT_ACTION, CONCLUSION
    }
}
