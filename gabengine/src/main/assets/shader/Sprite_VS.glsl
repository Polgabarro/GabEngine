attribute vec4 vPosition;
uniform mat4 uVPMatrix;
uniform mat4 uModelMatrix;

attribute vec2 uv;
varying vec2 v_uv;


void main(){

    gl_Position = uVPMatrix*uModelMatrix*vPosition;
    v_uv = uv;

}