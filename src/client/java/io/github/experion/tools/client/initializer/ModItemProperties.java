package io.github.experion.tools.client.initializer;

import io.github.experion.tools.client.item.properties.FlintCriticalProperty;
import io.github.experion.tools.client.item.properties.ToolBooleanFirstProperty;
import io.github.experion.tools.client.item.properties.ToolStatusProperty;
import io.github.experion.tools.lib.Experion.ExperionRegistery;
import net.minecraft.client.render.item.property.bool.BooleanProperties;
import net.minecraft.client.render.item.property.numeric.NumericProperties;

public class ModItemProperties {
    public static void bootstrap() {
        NumericProperties.ID_MAPPER.put(ExperionRegistery.newId("tool_status"), ToolStatusProperty.CODEC);
        BooleanProperties.ID_MAPPER.put(ExperionRegistery.newId("flint_critical"), FlintCriticalProperty.CODEC);
        BooleanProperties.ID_MAPPER.put(ExperionRegistery.newId("tool_boolean_first"), ToolBooleanFirstProperty.CODEC);
    }
}
