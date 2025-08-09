package net.fuzzykiwi3.tutorialmod.item.custom;

import net.minecraft.entity.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class LightningHammerItem extends Item {
    public LightningHammerItem(Settings settings) {
        super(settings);
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        double xPos = target.getX();
        double yPos = target.getY();
        double zPos = target.getZ();

        if(!world.isClient) {
            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            lightning.setPos(xPos, yPos, zPos);
            world.spawnEntity(lightning);
        }

        stack.damage(1, ((ServerWorld) world), ((ServerPlayerEntity) attacker),
                item -> attacker.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

        return super.postHit(stack, target, attacker);
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        double xPos = context.getHitPos().x;
        double yPos = context.getHitPos().y;
        double zPos = context.getHitPos().z;

        if(!world.isClient) {
            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            lightning.setPos(xPos, yPos, zPos);
            world.spawnEntity(lightning);

            context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                    item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

        }

        return ActionResult.SUCCESS;
    }
}
