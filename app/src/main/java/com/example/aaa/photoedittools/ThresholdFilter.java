package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/22.
 */
/**
 * 阈值特效
 * @author daizhj
 *
 */
public class ThresholdFilter implements IImageFilter{

    private float Threshold = 0.5f;
    //@Override
    public Image process(Image imageIn) {
        int r, g, b;
        int threshold = (int)(this.Threshold * 255f);

        for (int x = 0; x < imageIn.getWidth(); x++) {
            for (int y = 0; y < imageIn.getHeight(); y++) {
                r = imageIn.getRComponent(x, y);
                g = imageIn.getGComponent(x, y);
                b = imageIn.getBComponent(x, y);

                int rgb = (((r * 0x1b36) + (g * 0x5b8c)) + (b * 0x93e)) >> 15;
                r = g = b  = rgb > threshold ? 0xff : 0;
                imageIn.setPixelColor(x,y,r,g,b);
            }
        }
        return imageIn;
    }

}

