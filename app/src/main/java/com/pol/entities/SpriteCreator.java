package com.pol.entities;

import com.pol.graphics.Shader;
import com.pol.graphics.textures.Texture;
import com.pol.graphics.textures.TextureFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 13/01/15.
 */
public class SpriteCreator {

    private static Map<String, Texture> textureMap = null;

    /**
     * Create a Sprite
     *
     * @param posX    The position X of the sprite
     * @param posY    The position Y of the sprite
     * @param width   The width of the sprite
     * @param height  The height of the sprite
     * @param texture The texture of the sprite
     * @return the sprite
     */
    public static Sprite createSprite(float posX, float posY, float width, float height, Texture texture) {
        Sprite sprite = new Sprite(posX, posY, texture);
        sprite.shapeCords = new float[]{
                -width / 2, height / 2, 0, //top left
                -width / 2, -height / 2, 0,//bottom left
                width / 2, -height / 2, 0,//bottom right
                width / 2, height / 2, 0 // top right
        };
        sprite.shapeIndex = new short[]{
                0, 1, 2, 2, 3, 0
        };
        sprite.setWidth(width);
        sprite.setHeight(height);
        sprite.setShader(Shader.LoadShaders("shader/Sprite_VS.glsl", "shader/Sprite_FS.glsl"));
        sprite.initVertex();
        sprite.initIndex();
        return sprite;
    }

    /**
     * Create a Sprite
     *
     * @param posX    The position X of the sprite
     * @param posY    The position Y of the sprite
     * @param texture The texture of the sprite
     * @return the sprite
     */
    public static Sprite createSprite(float posX, float posY, Texture texture) {
        return createSprite(posX, posY, texture.getWidth(), texture.getHeight(), texture);
    }

    /**
     * Create a Sprite
     *
     * @param posX        The position X of the sprite
     * @param posY        The position Y of the sprite
     * @param width       The width of the sprite
     * @param height      The height of the sprite
     * @param texturePath The texture path of the sprite
     * @return the sprite
     */
    public static Sprite createSprite(float posX, float posY, float width, float height, String texturePath) {
        if (textureMap == null) {
            textureMap = new HashMap<String, Texture>();
        }
        Texture texture = textureMap.get(texturePath);
        if (texture == null) {
            texture = TextureFactory.LoadTexture(texturePath);
            textureMap.put(texturePath, texture);
        }
        if (width != 0)
            return createSprite(posX, posY, width, height, texture);
        else
            return createSprite(posX, posY, texture.getWidth(), texture.getHeight(), texture);
    }

    /**
     * Create a Sprite
     *
     * @param posX        The position X of the sprite
     * @param posY        The position Y of the sprite
     * @param texturePath The texture path of the sprite
     * @return the sprite
     */
    public static Sprite createSprite(float posX, float posY, String texturePath) {
        return createSprite(posX, posY, 0, 0, texturePath);
    }

    public static void init() {
        textureMap = null;
    }
}
