package io.github.vftdan.vftdankeybinder;

import net.minecraftforge.client.event.GuiScreenEvent.KeyboardInputEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventHandlerCommon {
    /*@SubscribeEvent
    public void mouseInput(MouseEvent e) {
    	anyInput(e);
    }
    @SubscribeEvent
    public void onKeyInput(KeyboardInputEvent e) {
    	anyInput(e);
    }
    @SubscribeEvent
    public void onKeyboardInput(KeyboardInputEvent e) {
    	anyInput(e);
    }*/
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent e) {
		anyInput(e);
	}
    public void anyInput(Event e) {
    	for(HandledKeyBinding k: HandledKeyBinding.binds) {
    		if(k.kb.isPressed()) k.onTrigger();
    	}
    }
}
