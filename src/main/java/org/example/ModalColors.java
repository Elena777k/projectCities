package org.example;

import java.awt.*;

public enum ModalColors {
    BLUE(new Color(0, 191, 255)),
    LIGHT_YELLOW(new Color(253, 255, 150)),
    PURPLE(new Color(154, 17, 217)),
    LIGHT_BLUE(new Color(204, 252, 255)),
    DARK_BLUE(new Color(21, 7, 46));

    private final Color color;

    ModalColors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
