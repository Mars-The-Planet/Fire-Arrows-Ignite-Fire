package com.mars.firearrows.mixin;

import com.mars.firearrows.FireArrowsConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class MixinMinecraft extends Projectile {
    MixinMinecraft(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(at = @At("TAIL"), method = "onHitBlock")
    public void onHitBlock(BlockHitResult BHR, CallbackInfo ci) {
        if(BHR.getType() == HitResult.Type.MISS) {return;}
        if(!this.isOnFire()) {return;}
        Level level = this.level();
        if(level instanceof ServerLevel) {
            switch(BHR.getDirection()) {
                case Direction.UP:
                    startFire(BHR.getBlockPos().above(), level);
                    break;
                case Direction.DOWN:
                    startFire(BHR.getBlockPos().below(), level);
                    break;
                case Direction.EAST:
                    startFire(BHR.getBlockPos().east(), level);
                    break;
                case Direction.WEST:
                    startFire(BHR.getBlockPos().west(), level);
                    break;
                case Direction.NORTH:
                    startFire(BHR.getBlockPos().north(), level);
                    break;
                case Direction.SOUTH:
                    startFire(BHR.getBlockPos().south(), level);
                    break;
            }
        }
    }

    public void startFire(BlockPos firePosition, Level level) {
        Block blockInLevel = level.getBlockState(firePosition).getBlock();
        for (int i = 0; i < FireArrowsConfig.blocksBrokenByFireArrows.size(); i++) {
            if(blockInLevel.equals(BuiltInRegistries.BLOCK.get(ResourceLocation.parse(FireArrowsConfig.blocksBrokenByFireArrows.get(i))))){
                level.destroyBlock(firePosition, true);
                level.setBlock(firePosition, BaseFireBlock.getState(level, firePosition), 11);
            }
        }
    }
}
