package com.github.experion.toolpath.items.tool_identify;

public class ToolGetter {
    final int ToolType;
    final int ToolID;

    public ToolGetter(int toolType, int toolID) {
        ToolType = toolType;
        ToolID = toolID;
    }

    public int getToolType() {
        return ToolType;
    }

    public int getToolID() {
        return ToolID;
    }
}
