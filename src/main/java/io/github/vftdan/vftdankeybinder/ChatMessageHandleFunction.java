package io.github.vftdan.vftdankeybinder;

public class ChatMessageHandleFunction implements IHandleFunction {
	private String outStr;
	public void execute() {
		VftdanKeyBinder.minecraft.player.sendChatMessage(outStr);
	}
	public ChatMessageHandleFunction(String s) {
		outStr = s;
	}
}
