package com.github.experion.toolpath.items.tool_identify;

import com.github.experion.toolpath.ModInit;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class ToolIdentifier {
    private final TagKey<Item> main;
    private final int depedency;
    private final int NumID;
    private final HashMap<Integer, String> ToolIDs;

    public ToolIdentifier(int numid, TagKey<Item> main, int depend) {
        this.main = main;
        this.depedency = depend;
        this.NumID = numid;

        ToolIdList.tryAddTag(numid,main);
        ToolIdList.tryAddID(this);

        this.ToolIDs = new HashMap<>(){{
            RegistryEntryList<Item> itemList = Registries.ITEM.getEntryList(main).orElse(null);

            if (itemList != null) {
                for (RegistryEntry<Item> a : itemList) {
                    Item item = a.value();
                    Identifier id = Registries.ITEM.getId(item);

                    int tooltype = ToolIdList.getToolType(item);

                    if (tooltype != -1) {
                        put(tooltype, id.getNamespace() + ":" + id.getPath());
                    }


                }
            }
        }};


    }

    public TagKey<Item> getMain() {
        return main;
    }

    public int getDepedency() {
        return depedency;
    }

    public int getNumID() {
        return NumID;
    }

    public Item getTool(int tooltype) {
        if (this.ToolIDs.containsKey(tooltype)) {
            return Registries.ITEM.get(ToolIdList.convertIdentifier(this.ToolIDs.get(tooltype)));
        }

        return null;
    }

    public Identifier getToolIdentifier(int tooltype) {
        if (this.ToolIDs.containsKey(tooltype)) {
            return ToolIdList.convertIdentifier(this.ToolIDs.get(tooltype));
        }

        return null;
    }
}
