package com.mars.firearrows;

import com.mars.deimos.config.DeimosConfig;

public class CommonClass {
    public static void init() {
        DeimosConfig.init("firearrows", FireArrowsConfig.class);
    }
}
