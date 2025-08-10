package net.fuzzykiwi3.tutorialmod.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.poi.PointOfInterestStorage;
import net.minecraft.world.poi.PointOfInterestTypes;

import java.util.List;
import java.util.Optional;

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


    private Optional<BlockPos> findLightningRod(ServerWorld world, BlockPos pos) {
        return world.getPointOfInterestStorage()
                .getNearestPosition(
                        poiType -> poiType.matchesKey(PointOfInterestTypes.LIGHTNING_ROD),
                        innerPos -> innerPos.getY() ==
                                world.getTopY(Heightmap.Type.WORLD_SURFACE, innerPos.getX(), innerPos.getZ()) - 1,
                        pos,
                        128, // radius
                        PointOfInterestStorage.OccupationStatus.ANY
                )
                .map(innerPos -> innerPos.up(1)); // one block above rod
    }


    private static void createLightning(World world, BlockPos strikePos) {
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightning.setPos(
                strikePos.getX() + 0.5,
                strikePos.getY(),
                strikePos.getZ() + 0.5
        );
        world.spawnEntity(lightning);
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();

        if(!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            BlockPos clickedPos = context.getBlockPos();
            Optional<BlockPos> rodPos = findLightningRod(serverWorld, clickedPos);
            BlockPos strikePos = rodPos.orElse(clickedPos);
            createLightning(world, strikePos);

            context.getStack().damage(1, serverWorld, ((ServerPlayerEntity) context.getPlayer()),
                    item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.tutorialmod.lightning_hammer.shift_down.tooltip1"));
            tooltip.add(Text.translatable("tooltip.tutorialmod.lightning_hammer.shift_down.tooltip2"));
        } else {
            tooltip.add(Text.translatable("tooltip.tutorialmod.lightning_hammer"));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }

}
