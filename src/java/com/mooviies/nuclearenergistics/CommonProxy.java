package com.mooviies.nuclearenergistics;

import com.mooviies.nuclearenergistics.block.ModBlocks;
import com.mooviies.nuclearenergistics.crafting.ModCrafting;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
    	ModBlocks.init();
    }

    public void init(FMLInitializationEvent e) {
    	ModCrafting.init();
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}