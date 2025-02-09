package com.github.experion.tools.client.initializer;

import com.github.experion.tools.client.item.properties.FlintCriticalProperty;
import com.github.experion.tools.client.item.properties.ToolStatusProperty;
import com.github.experion.tools.lib.Experion.ExperionRegistery;
import net.minecraft.client.render.item.property.bool.BooleanProperties;
import net.minecraft.client.render.item.property.bool.BooleanProperty;
import net.minecraft.client.render.item.property.numeric.NumericProperties;

public class ModItemProperties {
    public static void bootstrap() {
        NumericProperties.ID_MAPPER.put(ExperionRegistery.newId("tool_status"), ToolStatusProperty.CODEC);
        BooleanProperties.ID_MAPPER.put(ExperionRegistery.newId("flint_critical"), FlintCriticalProperty.CODEC);
    }
}
