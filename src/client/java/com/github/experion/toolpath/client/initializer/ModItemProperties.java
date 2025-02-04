package com.github.experion.toolpath.client.initializer;

import com.github.experion.toolpath.client.item.properties.numeric.CopperToolStatusProperty;
import com.github.experion.toolpath.lib.Experion.ExperionRegistery;
import net.minecraft.client.render.item.property.numeric.NumericProperties;

public class ModItemProperties {
    public static void bootstrap() {
        NumericProperties.ID_MAPPER.put(ExperionRegistery.newId("copper_status"), CopperToolStatusProperty.CODEC);
    }
}
