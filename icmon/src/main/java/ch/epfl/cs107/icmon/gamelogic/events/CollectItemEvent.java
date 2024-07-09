package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.window.Sound;

public class CollectItemEvent extends ICMonEvent {
    private final ICMonItem item;
    private final ICMonPlayer mainPlayer;

    public CollectItemEvent(ICMonPlayer mainplayer, ICMonItem item){
        super(mainplayer);
        this.mainPlayer = mainplayer;
        this.item = item;
        onStart(new LogAction("Item Collect Event started"));
        onComplete(new LogAction("Item Collect Event ended"));
    }
    @Override
    public void update(float deltaTime) {
        if (item.isCollected()){
            if (item instanceof ICBall){
                mainPlayer.recievePokemon(((ICBall) item).getPokemon());
                Sound sound = ((ICMonArea)mainPlayer.getOwnerArea()).getWindow().getSound("Collect.wav");
                ((ICMonArea)mainPlayer.getOwnerArea()).getWindow().playSound(sound, false, 1f, false, false, true);
                mainPlayer.openDialog(new Dialog("collect_item_event_collect_icball"));
            }
            complete();
        }

    }

    @Override
    public void interactWith(ICShopAssistant assistant , boolean isCellInteraction){
        super.mainPlayer.openDialog(new Dialog("collect_item_event_interaction_with_icshopassistant"));
        System.out.println("This is an interaction between the player and ICShopAssistant based on events !");
    }
}
