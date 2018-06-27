package io.github.vftdan.vftdankeybinder;

import java.util.ArrayList;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class HandledKeyBinding {
	public static ArrayList<HandledKeyBinding> binds = new ArrayList<HandledKeyBinding>();
	public KeyBinding kb;
	public IHandleFunction[] functions;
	public HandledKeyBinding(KeyBinding k) {
		kb = k;
		ClientRegistry.registerKeyBinding(k);
		binds.add(this);
	}
	public HandledKeyBinding(String kbname, int defkey, String kbcat) {
		this(new KeyBinding(kbname, defkey, kbcat));
	}
	public void setHandler(IHandleFunction[] f) {
		functions = f;
	}
	public void setHandler(ArrayList<IHandleFunction> f) {
		IHandleFunction[] fs = new IHandleFunction[f.size()];
		f.toArray(fs);
		functions = fs;
	}
	public void onTrigger() {
		for(IHandleFunction f: functions) {
			f.execute();
		}
	}
}
