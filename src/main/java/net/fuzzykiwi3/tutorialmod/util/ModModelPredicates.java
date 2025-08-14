package net.fuzzykiwi3.tutorialmod.util;

import net.fuzzykiwi3.tutorialmod.TutorialMod;
import net.fuzzykiwi3.tutorialmod.component.ModDataComponentTypes;
import net.fuzzykiwi3.tutorialmod.item.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.CHISEL, Identifier.of(TutorialMod.MOD_ID, "used"),
                (stack, world, entity, seed) -> stack.get(ModDataComponentTypes.COORDINATES) != null ? 1f : 0f);

    }
}
