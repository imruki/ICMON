package ch.epfl.cs107.icmon.gamelogic.events;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.Updatable;

import java.util.ArrayList;
import java.util.List;

/**
 * Model all event in the game
 */
public abstract class ICMonEvent implements Updatable, ICMonInteractionVisitor {

    protected ICMonPlayer mainPlayer;
    private boolean started;
    private boolean completed;
    private boolean suspended;

    private final List<Action> startActions;
    private final List<Action> completeActions;
    private final List<Action> suspendActions;
    private final List<Action> resumeActions;

    /**
     * Default constructor
     * @param mainPlayer player doing event
     */
    public ICMonEvent(ICMonPlayer mainPlayer){
        this.mainPlayer = mainPlayer;
        this.started = false;
        this.completed = false;
        this.suspended = false;
        startActions = new ArrayList<>();
        completeActions = new ArrayList<>();
        suspendActions = new ArrayList<>();
        resumeActions = new ArrayList<>();
    }

    @Override
    public abstract void update(float deltaTime);

    public final void start(){
        if (!started) {
            started = true;
            for (Action action : startActions) {
                action.perform();
            }
        }
    }

    public final void complete(){
        if (started && !completed) {
            completed = true;
            for (Action action : completeActions) {
                action.perform();
            }
        }
    }

    public final void suspend(){
        if (!suspended && !completed && started) {
            suspended = true;
            for (Action action : suspendActions) {
                action.perform();
            }
        }
    }

    public final void resume(){
        if (suspended && !completed && started) {
            suspended = false;
            for (Action action : resumeActions) {
                action.perform();
            }
        }
    }

    public final void onStart(Action action){
        startActions.add(action);
    }

    public final void onComplete(Action action){
        completeActions.add(action);
    }

    public final void onSuspend(Action action){
        suspendActions.add(action);
    }

    public final void onResume(Action action){
        resumeActions.add(action);
    }

    public final boolean isStarted(){ return this.started; }
    public final boolean isCompleted(){ return this.completed; }
    public final boolean isSuspended(){ return this.suspended; }
    public PauseMenu getPauseMenu(){return null;}

}
