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


public class ZoomBlurFilter implements IImageFilter{
    int m_length;
    double m_offset_x;
    double m_offset_y;
    int m_fcx, m_fcy;
    final int RADIUS_LENGTH = 64;

    public ZoomBlurFilter(int nLength)
    {
        this(nLength, 0.0f, 0.0f);
    }

    public ZoomBlurFilter(int nLength, double offset_x, double offset_y)
    {
        m_length = (nLength >= 1) ? nLength : 1 ;
        m_offset_x = (offset_x > 2.0 ? 2.0 : ( offset_x< -2.0 ? 0 : offset_x));
        m_offset_y =(offset_y > 2.0 ? 2.0 : ( offset_y< -2.0 ? 0 : offset_y));
    }

    //@Override
    public Image process(Image imageIn) {
        int width = imageIn.getWidth();
        int height = imageIn.getHeight();
        m_fcx = (int)(width * m_offset_x * 32768.0) + (width * 32768) ;
        m_fcy = (int)(height * m_offset_y * 32768.0) + (height * 32768) ;

        final int ta = 255;
        Image clone = imageIn.clone();
        for(int x = 0 ; x < width ; x++){
            for(int y = 0 ; y < height ; y++){
                int sr=0, sg=0, sb=0, sa=0;
                sr = clone.getRComponent(x, y) * ta;
                sg = clone.getGComponent(x, y) * ta;
                sb = clone.getBComponent(x, y) * ta;
                sa += ta;
                int   fx = (x * 65536) - m_fcx ;
                int   fy = (y * 65536) - m_fcy ;
                for (int i = 0 ; i < RADIUS_LENGTH ; i++)
                {
                    fx = fx - (fx / 16) * m_length / 1024 ;
                    fy = fy - (fy / 16) * m_length / 1024 ;

                    int   u = (fx + m_fcx + 32768) / 65536 ;
                    int   v = (fy + m_fcy + 32768) / 65536 ;
                    if (u>=0 && u<width && v>=0 && v<height)
                    {
                        sr += clone.getRComponent(u, v) * ta ;
                        sg += clone.getGComponent(u, v) * ta ;
                        sb += clone.getBComponent(u, v) * ta ;
                        sa += ta ;
                    }
                }
                imageIn.setPixelColor(x, y, Image.SAFECOLOR(sr/sa), Image.SAFECOLOR(sg/sa), Image.SAFECOLOR(sb/sa));
            }
        }
        return imageIn;
    }

}

