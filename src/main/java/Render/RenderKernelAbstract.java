package Render;

import com.aparapi.*;

abstract class RenderKernelAbstract extends Kernel {

    protected RenderKernelAbstract() {

    }

    //This will act as a holder for mathematical methods for sub kernels for simplicity

    float dot(final float ax, final float ay, final float bx, final float by) {
        return (ax * bx) + (ay * by);
    }

    float len(final float ax, final float ay) {
        return sqrt(pow(ax, 2f) + pow(ay, 2f));
    }

    float smoothstep(final float a, final float b, final float x) {
        final float t = clamp((x - a) / (b - a), 0.0f, 1.0f);
        return t * t * (3.0f - 2.0f * t);
    }

    float clamp(final float val, final float min, final float max) {
        return Math.max(min, Math.min(max, val));
    }

}
