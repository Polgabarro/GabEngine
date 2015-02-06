attribute vec4 vPosition;
uniform mat4 uVPMatrix;
uniform mat4 uModelMatrix;


void main(){

    gl_Position = uVPMatrix*uModelMatrix*vPosition;


}