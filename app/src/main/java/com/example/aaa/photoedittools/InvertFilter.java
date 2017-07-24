package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/22.
 */
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

/**
 * 反色效果
 * @author daizhj
 *
 */
public class InvertFilter implements IImageFilter{

    //@Override
    public Image process(Image imageIn) {
        int r, g, b;
        for (int x = 0; x < imageIn.getWidth(); x++) {
            for (int y = 0; y < imageIn.getHeight(); y++) {
                r = (255-imageIn.getRComponent(x, y));
                g = (255-imageIn.getGComponent(x, y));
                b = (255-imageIn.getBComponent(x, y));

                imageIn.setPixelColor(x,y,r,g,b);
            }
        }
        return imageIn;
    }

}
