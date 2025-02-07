package com.github.experion.tools.lib;

import com.github.experion.tools.items.ToolIdList;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class ToolAppend {
    List<Text> Lines;
    final Item item;

    public ToolAppend(Item target_item) {
        this.Lines = new ArrayList<>();
        this.item = target_item;
    }

    public ToolAppend line(String str, TYPE type) {
        return this.line(str,type,false, null);
    }

    public ToolAppend line(String str, TYPE type, int tooltype) {
        return this.line(str,type,true,List.of(tooltype));
    }

    public ToolAppend line(String str, TYPE type, boolean whitelist, List<Integer> toolValids) {
        String res = str;
        String prefix = "";
        Formatting format = Formatting.RESET;

        if (type == TYPE.POSITIVE) { // POSITIVE
            prefix = "+ ";
            format = Formatting.GREEN;
        }else if (type == TYPE.NEGATIVE) { // NEGATIVE
            prefix = "- ";
            format = Formatting.RED;
        }else if (type == TYPE.MIDS) { // MIDS
            prefix = "= ";
            format = Formatting.GOLD;
        }else if (type == TYPE.HINT) { // HINT
            prefix = "? ";
            format = Formatting.GRAY;
        }else if (type == TYPE.QUOTE) {
            format = Formatting.DARK_GRAY;
        }else if (type == TYPE.INSANE) {
            prefix = "++ ";
            format = Formatting.LIGHT_PURPLE;
        }

        boolean fond = false;
        int tooltype = ToolIdList.getToolType(this.item);

        if (toolValids != null) {
            for (int v : toolValids) {
                if (v == tooltype) {
                    if (whitelist == true) {
                        fond = true;
                        break;
                    }else {
                        break;
                    }

                }
            }
        }else {
            fond = true;
        }

        if (fond) {
            Lines.add(Text.literal(prefix + str).formatted(format));
        }

        return this;
    }

    public void offer(List<Text> tooltip) {
       for (int i = 0; i <= Lines.size() - 1; i++) {
           tooltip.add(Lines.get(i));
       }
    }

    public static enum TYPE {
        POSITIVE,
        NEGATIVE,
        MIDS,
        QUOTE,
        HINT,
        INSANE
        ;
    }

    public static ToolAppend of(Item target_item) {
        return new ToolAppend(target_item);
    }
}
