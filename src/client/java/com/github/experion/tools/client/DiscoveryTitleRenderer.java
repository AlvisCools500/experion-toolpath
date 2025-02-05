package com.github.experion.tools.client;

import com.github.experion.tools.ModInit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class DiscoveryTitleRenderer {
    private static boolean show = false;
    private static int targclock = 0;

    private static final float duration = convertTick(2.0f);
    private static final float fadeout = convertTick(1.0f);
    private static float currdur = 0.0f;

    private static Identifier item_id = null;
    private static int type_title = 1;

    public static void renderTitle(DrawContext context, RenderTickCounter deltaCount) {
        if (show) {
            MinecraftClient client = MinecraftClient.getInstance();
            InGameHud hud = client.inGameHud;
            MatrixStack matrix = context.getMatrices();

            if (currdur < (duration + fadeout + convertTick(1.0f))) {
                int width = context.getScaledWindowWidth();
                int height = context.getScaledWindowHeight();

                float startx = 10f;
                float endx = -200f;

                float xpos = 10;

                float alphadur = Math.clamp(currdur/duration,0.0f,1.0f);

                if (alphadur >= 1) {
                    float alphamove = Math.clamp((currdur - duration)/fadeout,0.0f,1.0f);

                    xpos = startx + (endx - startx) * curveIn(alphamove);
                }

                Text main = Text.literal("unknown_title");
                Text sub = Text.literal("NULL").formatted(Formatting.YELLOW);

                if (type_title == 1) {
                    main = Text.literal("Tool Discovered!").formatted(Formatting.GREEN).formatted(Formatting.BOLD);
                }else if (type_title == 2)  {
                    main = Text.literal("Craft Required:").formatted(Formatting.RED).formatted(Formatting.BOLD);
                }

                if (item_id != null) {
                    sub = Registries.ITEM.get(item_id).getName();
                }

                drawText(context, main, 2.0f, (int) xpos,height / 2);
                drawText(context, sub, 1.5f, (int) xpos,(height / 2) + 20);

                if (!client.isPaused()) {
                    currdur += deltaCount.getLastFrameDuration();
                }

            }else {
                ModInit.LOGGER.info("BOOM");
                show = false;
                currdur = 0;
            }
        }
    }

    public static float curveIn(float p) {
        double k = 1.70158;
        return (float) (p*p*(p*(k+1) - k));
    }

    public static float convertTick(float num) {
        return num * 20f;
    }

    public static void drawText(DrawContext context, Text text, float scale, int xpos, int ypos) {
        MinecraftClient client = MinecraftClient.getInstance();
        InGameHud hud = client.inGameHud;
        MatrixStack matrix = context.getMatrices();

        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();

        int textWidth = hud.getTextRenderer().getWidth(text);
        int x = (width - (int) (textWidth * scale));
        x -= xpos;

        matrix.push();
        matrix.scale(scale, scale, 1.0f);
        context.drawTextWithShadow(hud.getTextRenderer(), text, (int) (x / scale), (int) (ypos / scale), 0xFFFFFF);
        matrix.pop();
    }

    public static void trigger(int type, Identifier identifier) {
        boolean fond = true;

        if (type == 2) {
            if (item_id == identifier && show) {
                fond = false;
            }
        }

        if (fond) {
            show = true;
            currdur = 0;
            type_title = type;
            item_id = identifier;
        }


    }


}
