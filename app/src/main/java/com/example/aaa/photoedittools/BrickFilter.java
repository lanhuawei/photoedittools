package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/21.
 */
/**
 * 积木效果，（用于处理人像）
 *
 *
 */
public class BrickFilter implements IImageFilter{

    public int ThreshHold = 128;

    //@Override
    public Image process(Image imageIn) {
        int width = imageIn.getWidth();
        int height = imageIn.getHeight();
        Image clone = imageIn.clone();
        int r = 0, g = 0, b = 0, avg = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                r = clone.getRComponent(x, y);
                g = clone.getGComponent(x, y);
                b = clone.getBComponent(x, y);
                avg = (r + g + b) / 3;
                avg = avg >= ThreshHold ? 255 : 0;
                imageIn.setPixelColor(x, y, avg, avg, avg);
            }
        }
        return imageIn;
    }
}
