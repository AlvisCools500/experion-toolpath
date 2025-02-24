package io.github.ExperionPlanet.tools.client.initializer;

import io.github.ExperionPlanet.tools.client.item.properties.FlintCriticalProperty;
import io.github.ExperionPlanet.tools.client.item.properties.HasAmountProperty;
import io.github.ExperionPlanet.tools.client.item.properties.ToolBooleanFirstProperty;
import io.github.ExperionPlanet.tools.client.item.properties.ToolStatusProperty;
import io.github.ExperionPlanet.tools.lib.Experion.ExperionRegistery;
import net.minecraft.client.render.item.property.bool.BooleanProperties;
import net.minecraft.client.render.item.property.numeric.NumericProperties;

public class ModItemProperties {
    public static void bootstrap() {
        NumericProperties.ID_MAPPER.put(ExperionRegistery.newId("tool_status"), ToolStatusProperty.CODEC);
        BooleanProperties.ID_MAPPER.put(ExperionRegistery.newId("flint_critical"), FlintCriticalProperty.CODEC);
        BooleanProperties.ID_MAPPER.put(ExperionRegistery.newId("tool_boolean_first"), ToolBooleanFirstProperty.CODEC);
        BooleanProperties.ID_MAPPER.put(ExperionRegistery.newId("has_amount"), HasAmountProperty.CODEC);
    }
}
