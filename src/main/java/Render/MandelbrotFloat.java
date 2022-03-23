package Render;

import Render.RenderKernelAbstract;
import com.aparapi.Kernel;

public class MandelbrotFloat extends RenderKernelAbstract {

    final int height;
    final int width;

    final float[] buffer; //Buffer is passed out with RGB data for image {R, G, B, R, G, B, ...}
    final float[] data; //Data is passed in the following form {XCoord, YCoord, ZoomFactor, FractalPower, Iterations, Threshold, AntiAliasing, ColormapR, ColormapG, ColormapB}



    public MandelbrotFloat(final int width, final int height, final float[] buffer, final float[] data) {

        this.height = height;
        this.width = width;

        this.buffer = buffer;
        this.data = data;

        setExplicit(true);
        put(this.buffer);
        put(this.data);

    }

    @Override
    public void run() {
        final int index = this.getGlobalId();

        final float currentx = index % this.width;//= - this.width * 0.5F + 0.5F;
        final float currenty = index / this.width;// - this.height * 0.5F + 0.5F;

        Render(currentx, currenty);

    }

    void Render(float pix, float piy) {
        final int AA = (int) data[6];
        final float power = data[3];
        final float zoom = data[2];

        float colx = 0;
        float coly = 0;
        float colz = 0;
        for(int a=0; a<AA; a++) {
            for(int b=0; b<AA; b++) {
                //Calculate coords using pixel and ambient occlusion offset and zoom factor
                final float aox = (float) a / (float) AA - 0.5f;
                final float aoy = (float) b / (float) AA - 0.5f;
                final float crt = zoom * (((this.width * -1.0f) + ((pix + aox) * 2.0f)) / this.height);
                final float cit = zoom * (((this.height * -1.0f) + ((piy + aoy) * 2.0f)) / this.height);
                //Offset for coords
                final float cr = crt + data[0];
                final float ci = cit + data[1];

                float r = 0.0000000000000000001f;
                float i = 0;
                int iter = 0;
                final float thresh = data[5];
                final int maxIter = (int) data[4];
                for (int k=0; k<maxIter; k++) {
                    //Perform mandelbrot calculation
                    final float argz = atan(i/r);
                    final float modz = len(r, i);
                    r = (pow(modz, power) * cos(argz * power)) + cr;
                    i = (pow(modz, power) * sin(argz * power)) + ci;

                    if(dot(r, i, r, i) > thresh) {
                        k = maxIter;
                    } else {
                        iter++;
                    }

                }

                //Smooth shading and colour mapping
                if(iter < maxIter) {
                    //https://iquilezles.org/www/articles/mset_smooth/mset_smooth.htm
                    final float sn = iter - log2(log2(dot(r, i, r, i))/(log2(thresh)))/log2(power);

                    //Color map for RGB
                    colx = colx + (0.5f + 0.5f*cos(3.0f + sn*0.075f*power + data[7]))*0.2f;
                    coly = coly + (0.5f + 0.5f*cos(3.0f + sn*0.075f*power + data[8]))*0.2f;
                    colz = colz + (0.5f + 0.5f*cos(3.0f + sn*0.075f*power + data[9]))*0.2f;

                }


            }
        }

        colx = colx / AA*AA;
        coly = coly / AA*AA;
        colz = colz / AA*AA;

        //Set the R, G, B for this pixel
        buffer[this.getGlobalId()*3] = colx;
        buffer[this.getGlobalId()*3+1] = coly;
        buffer[this.getGlobalId()*3+2] = colz;

    }



}