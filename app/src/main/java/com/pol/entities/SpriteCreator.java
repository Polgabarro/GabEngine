package com.pol.entities;

import com.pol.graphics.Shader;
import com.pol.graphics.textures.Texture;

/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 13/01/15.
 */
public class SpriteCreator {

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

        sprite.setShader(Shader.LoadShaders("shader/Sprite_VS.glsl", "shader/Sprite_FS.glsl"));
        sprite.initVertex();
        sprite.initIndex();
        return sprite;
    }
}
