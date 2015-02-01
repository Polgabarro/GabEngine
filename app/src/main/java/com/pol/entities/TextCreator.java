package com.pol.entities;

import com.pol.graphics.Shader;
import com.pol.graphics.textures.Font;
import com.pol.graphics.textures.FontFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 30/01/15.
 */
public class TextCreator {
    private static Map<String, Font> fontMap = null;

    /**
     * Create a text entity
     *
     * @param posX  the position X
     * @param posY  the position Y
     * @param sText the string of text
     * @param font  the font
     * @return the Text
     */
    public static Text createText(float posX, float posY, String sText, Font font) {
        Text text = new Text(posX, posY);
        text.font = font;
        text.setText(sText);
        text.setShader(Shader.LoadShaders("shader/Sprite_VS.glsl", "shader/Sprite_FS.glsl"));
        return text;
    }

    /**
     * Create a text entity with default font
     *
     * @param posX  the position X
     * @param posY  the position Y
     * @param sText the string of text
     * @param size  the size of text
     * @return the Text
     */
    public static Text createText(float posX, float posY, String sText, int size) {
        return createText(posX, posY, sText, FontFactory.LoadFont(size));
    }

    /**
     * Create a text entity with a font
     *
     * @param posX     the position X
     * @param posY     the position Y
     * @param sText    the string of text
     * @param fontPath the font path
     * @param size     the size of text
     * @return the Text
     */
    public static Text createText(float posX, float posY, String sText, String fontPath, int size) {
        if (fontMap == null) {
            fontMap = new HashMap<String, Font>();
        }
        Font font = fontMap.get(fontPath + size);
        if (font == null) {
            font = FontFactory.LoadFont(fontPath, size);
            fontMap.put(fontPath + size, font);
        }
        return createText(posX, posY, sText, font);
    }

    public static void init() {
        fontMap = null;
    }
}
