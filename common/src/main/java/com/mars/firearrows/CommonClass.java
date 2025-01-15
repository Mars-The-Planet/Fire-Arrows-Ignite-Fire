package com.mars.firearrows;

import com.mars.deimos.config.DeimosConfig;
import com.mars.firearrows.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;

public class CommonClass {
    public static void init() {
        DeimosConfig.init("firearrows", FireArrowsConfig.class);
    }
}
