package ch.epfl.cs107.icmon;

import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.pokemon.Nidoqueen;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.*;
import ch.epfl.cs107.icmon.gamelogic.actions.*;
import ch.epfl.cs107.icmon.gamelogic.events.*;
import ch.epfl.cs107.icmon.gamelogic.fights.PokemonSelectionMenu;
import ch.epfl.cs107.icmon.gamelogic.pause.PlayerPauseMenu;
import ch.epfl.cs107.icmon.handler.GamePlayMessage;
import ch.epfl.cs107.icmon.handler.SuspendWithEvent;
import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Sound;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * Class of the game ICMon
 */
public final class ICMon extends AreaGame {
    public final static float CAMERA_SCALE_FACTOR = 13.f;
    private ICMonPlayer player;
    private List<ICMonEvent> completedEvents;
    private List<ICMonEvent> startedEvents;
    private ICMonGameState gameState;
    private static ICMonEventManager eventManager;

    /**
     * adding all areas of the game
     */
    private void createAreas() {
        addArea(new House());
        addArea(new Shop());
        addArea(new Town());
        addArea(new Lab());
        addArea(new Arena());
        addArea(new MemeHouse());
        addArea(new PikachuHouse());
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {

            gameState = new ICMonGameState();
            eventManager = new ICMonEventManager();
            startedEvents = new ArrayList<>();
            completedEvents = new ArrayList<>();

            createAreas();
            initArea("house");

            events();
            return true;
        }
        return false;
    }

    /** method for registering the chained events */
    private void events(){

        //initialising PokeBall collection event
        ICBall ball = new ICBall(setCurrentArea("town", false), new DiscreteCoordinates(6,6),new Nidoqueen(getCurrentArea(), Orientation.DOWN, new DiscreteCoordinates(6,6), false));
        ICMonEvent ballEvent = new CollectItemEvent(player, ball);
        ballEvent.onStart(new RegisterinAreaAction((ICMonArea) setCurrentArea("town", false), ball));
        setCurrentArea("house", false);

        //initialising all events
        ICMonEvent endEvent = new EndOfTheGameEvent(player);
        ICMonEvent introductionEvent = new IntroductionEvent(player);
        ICMonEvent firstInteractionWithProfOak = new FirstInteractionWithProfOakEvent(player);
        ICMonEvent firstInteractionWithGarry = new FirstInteractionWithGarryEvent(player, gameState);

        //adding the events to the chain list
        List<ICMonEvent> eventsList = new ArrayList<>();
        eventsList.add(firstInteractionWithProfOak);
        eventsList.add(ballEvent);
        eventsList.add(firstInteractionWithGarry);
        eventsList.add(endEvent);

        //initialising the chained event
        ICMonEvent chainedEvent = new ICMonChainedEvent(player, eventManager, introductionEvent, eventsList);
        chainedEvent.onStart(new RegisterEventAction(eventManager, chainedEvent));
        chainedEvent.start();

        Sound sound = getWindow().getSound("Introduction.wav");
        getWindow().playSound(sound, false, 1f, false, false, true);
    }

    @Override
    public void update(float deltaTime) {
        Keyboard keyboard = getWindow().getKeyboard();

        try{
        for (ICMonEvent event : startedEvents){
                if(!event.isSuspended()){
                    event.update(deltaTime);
            }
        }} catch (ConcurrentModificationException e){
            System.out.println("an event update modified startedEvent, we will reiterate startedEvents in the next update");
        }

        if (keyboard.get(Keyboard.R).isPressed() && !isPaused()){
            PlayerPauseMenu pauseMenu = new PlayerPauseMenu(eventManager, keyboard, gameState);
            ICMonEvent pause = new PlayerPauseEvent(pauseMenu, player);
            pause.onStart(new RegisterEventAction(eventManager, pause));
            pause.onComplete(new ResumeEventAction(eventManager));

            gameState.message = new SuspendWithEvent(pause, this, eventManager);
        }

        player.update(deltaTime);
        if(gameState.message != null){
            gameState.message.process();
            gameState.message = null;
        }
        super.update(deltaTime);
    }

    @Override
    public void end() {}
    @Override
    public String getTitle() {
        return "ICMon";
    }

    /**
     * intitialise an area and spawn the main player in it
     * @param areaKey title of the area
     */
    private void initArea(String areaKey) {
        ICMonArea area = (ICMonArea) setCurrentArea(areaKey, true);
        DiscreteCoordinates coords = new DiscreteCoordinates(5, 5);
        player = new ICMonPlayer(area, Orientation.DOWN, coords, gameState);
        player.enterArea(area, coords);
        player.centerCamera();
    }

    public class ICMonGameState{
        public GamePlayMessage message;
        private ICMonGameState() {}

        public void acceptInteraction(Interactable interactable , boolean isCellInteraction){
            for(var event : ICMon.this.startedEvents)
                interactable.acceptInteraction(event , isCellInteraction);
        }

        /**
         * method for switching an area
         * @param player main player
         * @param targetedArea targeted area
         * @param spawnPosition spawn position within the new area
         */
        public void switchArea(ICMonPlayer player, String targetedArea, DiscreteCoordinates spawnPosition){
            player.leaveArea();
            ICMonArea currentArea = (ICMonArea) setCurrentArea(targetedArea, false);
            player.enterArea(currentArea, spawnPosition);
        }

        /**
         * method for selecting a pokemon and starting a fight
         * @param opponent opponent we want to fight
         * @param selectionMenu pokemon selection menu
         */
        public void selectPokemon(ICMonFightableActor opponent, PokemonSelectionMenu selectionMenu){
            PokemonSelectionEvent selectionEvent = new PokemonSelectionEvent(selectionMenu,player);

            selectionEvent.onStart(new RegisterEventAction(eventManager,selectionEvent));
            selectionEvent.onComplete(new AfterPokemonSelectionFightAction(eventManager, ICMon.this, gameState, player, selectionMenu, opponent));

            gameState.message = new SuspendWithEvent(selectionEvent, ICMon.this, ICMon.eventManager);
        }

        /** restart the game */
        public void restart(){
            ICMon.super.begin(getWindow(), getFileSystem());
            begin(getWindow(), getFileSystem());
        }

        public void close(){getWindow().dispose();}
    }

    public class ICMonEventManager{
        private ICMonEventManager(){}

        /** register an event
         * Suspend all events
         * @param event event that we want to register
         */
        public void register(ICMonEvent event){
            startedEvents.add(event);
        }

        /**
         * unregister an event
         * @param event event that we want to unregister
         */
        public void unregister(ICMonEvent event){
            startedEvents.remove(event);
            completedEvents.add(event);
        }

        /**
         * Suspend all events
         * @param originEvent event that caused suspension
         */
        public void suspendAllEvents(ICMonEvent originEvent){
            for (ICMonEvent icmonEvent : startedEvents) {
                if(icmonEvent != originEvent){
                icmonEvent.suspend();}
                }
        }

        /** resume all events */
        public void resumeAllEvents(){
            for (ICMonEvent icmonEvent : startedEvents)
                icmonEvent.resume();
        }

        public void setPauseMenu(PauseMenu menu){
            ICMon.this.setPauseMenu(menu);
        }
    }
}
