package io.github.vftdan.vftdankeybinder;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.MinecraftForge;

import java.io.*;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = VftdanKeyBinder.MODID, version = VftdanKeyBinder.VERSION, name = VftdanKeyBinder.NAME)
public class VftdanKeyBinder
{
    public static final String MODID = "vftdankeybinder";
    public static final String VERSION = "1.0";
    public static final String NAME = "Vftdan key binder";
    public static File fname = new File("binds.txt");
    public static Minecraft minecraft = Minecraft.getMinecraft();
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        //System.out.println("DIRT BLOCK >> "+Blocks.DIRT.getUnlocalizedName());
		MinecraftForge.EVENT_BUS.register(new EventHandlerCommon());
		//KeyBinding k = new KeyBinding("key.bind1", Keyboard.KEY_O, "key.categories.bindings");
		//ClientRegistry.registerKeyBinding(k);
		//HandledKeyBinding k = new HandledKeyBinding("key.bind1", Keyboard.KEY_O, "");
		//k.setHandler(new IHandleFunction[] {new GameOutputHandleFunction("O pressed!")});
		//System.out.println(fname.getAbsolutePath());
		try {
			FileReader fr = new FileReader(fname);
			LineNumberReader lnr = new LineNumberReader(fr);
			String s;
			String[] kv;
			String[] funcs;
			String[] kvf;
			int i;
			HandledKeyBinding k;
			ArrayList<IHandleFunction> arr;
			while((s = lnr.readLine()) != null) {
				kv = s.split("=", 2);
				k = new HandledKeyBinding(kv[0], Keyboard.CHAR_NONE, "key.categories.bindings");
				funcs = kv[1].split("(?<!\\\\);");
				arr = new ArrayList<IHandleFunction>();
				for(i = 0; i < funcs.length; i++) {
					kvf = funcs[i].split("=", 2);
					switch(kvf[0]) {
						case "gout": arr.add(new GameOutputHandleFunction(kvf[1]));
						break;
						case "chatsend": arr.add(new ChatMessageHandleFunction(kvf[1]));
						break;
						default: System.err.println("Unknown function: " + kvf[0]);
					}
				}
				k.setHandler(arr);
			}
			lnr.close();
			fr.close();
		} catch(IOException e) {
			System.err.println(e);
		}
    }
    
}
