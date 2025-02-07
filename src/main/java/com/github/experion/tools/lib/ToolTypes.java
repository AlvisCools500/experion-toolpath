package com.github.experion.tools.lib;

import java.util.List;

public class ToolTypes {
    public static final int SWORD = 1;
    public static final int AXE = 2;
    public static final int PICKAXE = 3;
    public static final int SHOVEL = 4;
    public static final int HOE = 5;

    public static final List<Integer> MINEABLE_TOOLS = List.of(AXE,PICKAXE,SHOVEL);
    public static final List<Integer> ATTACKABLE_TOOLS = List.of(SWORD,PICKAXE);
}
