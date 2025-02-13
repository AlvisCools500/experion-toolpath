package io.github.experion.tools.lib;

import java.awt.*;

public class PrimalDoubleColor {
    public final float R;
    public final float G;
    public final float B;

    public PrimalDoubleColor(Color col) {
        this.R = (float) (col.getRed() / 255);
        this.G = (float) (col.getBlue() / 255);
        this.B = (float) (col.getGreen() / 255);
    }

    public float getRED() {
        return R;
    }

    public float getGREEN() {
        return G;
    }

    public float getBLUE() {
        return B;
    }
}
