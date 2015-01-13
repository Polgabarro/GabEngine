precision mediump float;

uniform vec4 vColor;
uniform sampler2D myTexture;
varying vec2 v_uv;

void main() {
              gl_FragColor = texture2D(myTexture,v_uv)*vColor;
            }
