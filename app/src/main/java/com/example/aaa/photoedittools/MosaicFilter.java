package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/22.
 */
import android.graphics.Color;

/**
 * 马赛克效果
 * @author daizhj
 *
 */
public class MosaicFilter implements IImageFilter{

    /**
     * 马赛克大小
     */
    public int MosiacSize = 4;

    //@Override
    public Image process(Image imageIn) {
        int width = imageIn.getWidth();
        int height = imageIn.getHeight();
        int r = 0, g = 0, b = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y % MosiacSize == 0) {
                    if (x % MosiacSize == 0){//整数倍时，取像素赋值
                        r = imageIn.getRComponent(x, y);
                        g = imageIn.getGComponent(x, y);
                        b = imageIn.getBComponent(x, y);
                    }
                    imageIn.setPixelColor(x, y, r, g, b);
                }
                else{ //复制上一行          */
                    imageIn.setPixelColor(x, y, imageIn.getPixelColor(x, y -1));
                }
            }
        }
        return imageIn;
    }
}
