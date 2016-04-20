package com.mooviies.nuclearenergistics;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION)
public class Main {

	@SidedProxy(clientSide="com.mooviies.nuclearenergistics.ClientProxy", serverSide="com.mooviies.nuclearenergistics.ServerProxy")
	public static CommonProxy proxy;
	
    public static final String MODID = "nuclearenergistics";
    public static final String MODNAME = "Nuclear Energistics";
    public static final String VERSION = "0.0.1";
        
    @Instance
    public static Main instance = new Main();
     
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	proxy.preInit(e);
    }
        
    @EventHandler
    public void init(FMLInitializationEvent e) {
    	proxy.init(e);
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	proxy.postInit(e);
    }
}