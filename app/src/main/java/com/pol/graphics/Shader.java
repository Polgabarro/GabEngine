package com.pol.graphics;

import android.app.Activity;
import android.content.res.AssetManager;
import android.opengl.GLES20;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Pol Gabarró<polgabarr@gmail.com> on 03/12/14.
 */
public class Shader {

    private static Activity activity;
    private static Map<String, Integer> shaderMap = new HashMap<String, Integer>();

    public static void init(Activity activity) {
        Shader.activity = activity;
        shaderMap.clear();
    }

    public static int LoadShaders(String VertexShader, String FragmentShader) {

        if (shaderMap.containsKey(VertexShader)) {
            return shaderMap.get(VertexShader);
        } else {
            Log.i("Shader Load","Shader: "+VertexShader+" and "+FragmentShader);
            int vertexShader = LoadShader(GLES20.GL_VERTEX_SHADER, VertexShader);
            int fragmentShader = LoadShader(GLES20.GL_FRAGMENT_SHADER, FragmentShader);

            int mProgram = GLES20.glCreateProgram();             // create empty OpenGL Program
            GLES20.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
            GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
            GLES20.glLinkProgram(mProgram);                  // create OpenGL program executables

            shaderMap.put(VertexShader, mProgram);
            return mProgram;
        }

    }

    private static int LoadShader(int type, String path) {
        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);


        InputStream is = null;
        AssetManager am = activity.getAssets();
        try {
            is = am.open(path);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }


        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, total.toString());
        GLES20.glCompileShader(shader);


        return shader;

    }


}
