package com.mooviies.nuclearenergistics.block;

import com.mooviies.nuclearenergistics.Main;
import com.mooviies.nuclearenergistics.client.render.blocks.RenderBlockHDAssembler;
import com.mooviies.nuclearenergistics.tile.TileHDMolecularAssembler;

import appeng.block.AEBaseTileBlock;
import appeng.core.CommonHelper;
import appeng.core.CreativeTab;
import appeng.core.features.ActivityState;
import appeng.core.features.BlockStackSrc;
import appeng.core.features.TileDefinition;
import appeng.core.sync.GuiBridge;
import appeng.tile.AEBaseTile;
import appeng.util.Platform;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockHDMolecularAssembler extends AEBaseTileBlock {
	private static boolean booleanAlphaPass = false;
	private final TileDefinition definition;

	public BlockHDMolecularAssembler() {
		super(Material.iron);

		this.setTileEntity(TileHDMolecularAssembler.class);
		this.isOpaque = false;
		this.lightOpacity = 1;

		final String name = "BlockHDMolecularAssembler";
		this.setCreativeTab(CreativeTab.tabBlock);
		this.setBlockName(name);
		this.setBlockTextureName(Main.MODID + ":" + name);

		if (Platform.isClient()) {
			CommonHelper.proxy.bindTileEntitySpecialRenderer(this.getTileEntityClass(), this);
		}

		final String registryName = "tile." + name;
		
		definition = new TileDefinition(this, ActivityState.Enabled);

		// Bypass the forge magic with null to register our own itemblock later.
		GameRegistry.registerBlock(this, null, registryName);
		GameRegistry.registerItem(definition.maybeItem().get(), registryName);
		GameRegistry.registerTileEntity(this.getTileEntityClass(), this.toString());
		AEBaseTile.registerTileItem(this.getTileEntityClass(), new BlockStackSrc(this, 0, ActivityState.from(true)));
	}

	@Override
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public boolean canRenderInPass(final int pass) {
		setBooleanAlphaPass(pass == 1);
		return pass == 0 || pass == 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public RenderBlockHDAssembler getRenderer() {
		return new RenderBlockHDAssembler();
	}

	@Override
	public boolean onActivated(final World w, final int x, final int y, final int z, final EntityPlayer p,
			final int side, final float hitX, final float hitY, final float hitZ) {
		final TileHDMolecularAssembler tg = this.getTileEntity(w, x, y, z);
		if (tg != null && !p.isSneaking()) {
			Platform.openGUI(p, tg, ForgeDirection.getOrientation(side), GuiBridge.GUI_MAC);
			return true;
		}
		return false;
	}

	public static boolean isBooleanAlphaPass() {
		return booleanAlphaPass;
	}

	private static void setBooleanAlphaPass(final boolean booleanAlphaPass) {
		BlockHDMolecularAssembler.booleanAlphaPass = booleanAlphaPass;
	}
}