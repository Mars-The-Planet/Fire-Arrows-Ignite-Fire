package com.mars.firearrows;

import com.google.common.collect.Lists;
import com.mars.deimos.config.DeimosConfig;

import java.util.List;

public class FireArrowsConfig extends DeimosConfig {
    @Entry public static List<String> blocksBrokenByFireArrows = Lists.newArrayList(
            "minecraft:air", "minecraft:cave_air", "minecraft:void_air", "minecraft:short_grass", "minecraft:tall_grass", "minecraft:fern", "minecraft:large_fern", "minecraft:dead_bush"
    );
}
