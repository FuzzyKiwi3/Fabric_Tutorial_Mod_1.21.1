package net.fuzzykiwi3.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fuzzykiwi3.tutorialmod.block.ModBlocks;
import net.fuzzykiwi3.tutorialmod.component.ModDataComponentTypes;
import net.fuzzykiwi3.tutorialmod.item.ModItemGroups;
import net.fuzzykiwi3.tutorialmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItemGroups.registerItemGroups();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        ModDataComponentTypes.registerDataComponentTypes();

        // If you want to add more fuel items it is suggested to make a class method like above.
        FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);
	}
}