package com.example.aaa.photoedittools.Distort;

/**
 * Created by aaa on 2016/1/22.
 */
import android.graphics.Color;
import com.example.aaa.photoedittools.*;

public class BilinearDistort implements IImageFilter{

    Image clone;

    BilinearDistort(){};

    double[] calc_undistorted_coord (int x, int y, double un_x, double un_y){
        return new double[]{un_x, un_y};
    }

    boolean IsInside (int width , int height, int x, int y) {
        return (x>=0) && (x<width) && (y>=0) && (y<height);
    }

    static int GetBilinear (double x, double y, int[] colors)
    {

        int[] px0 = new int[3];
        px0[0] = (colors[0] & 0xFF0000)>>16;
        px0[1] = (colors[0] & 0x00FF00)>>8;
        px0[2] = colors[0] & 0x0000FF;

        int[] px1 = new int[3];
        px1[0] = (colors[1] & 0xFF0000)>>16;
        px1[1] = (colors[1] & 0x00FF00)>>8;
        px1[2] = colors[1] & 0x0000FF;

        int[] px2 = new int[3];
        px2[0] = (colors[2] & 0xFF0000)>>16;
        px2[1] = (colors[2] & 0x00FF00)>>8;
        px2[2] = colors[2] & 0x0000FF;

        int[] px3 = new int[3];
        px3[0] = (colors[3] & 0xFF0000)>>16;
        px3[1] = (colors[3] & 0x00FF00)>>8;
        px3[2] = colors[3] & 0x0000FF;

        int[] crRet = new int[3];
        for (int i=0 ; i < 3 ; i++)
        {
            double m0 = px0[i] + x * (px1[i] - px0[i]);
            double m1 = px2[i] + x * (px3[i] - px2[i]);
            double my = m0 + y * (m1 - m0) ;
            crRet[i] = Image.SAFECOLOR((int)my) ;
        }

        return  Color.rgb(crRet[0], crRet[1], crRet[2]);
    }

    //@Override
    public Image process(Image imageIn) {
        clone = imageIn.clone();
        int width = imageIn.getWidth();
        int height = imageIn.getHeight();
        for(int x = 0 ; x < width; x++){
            for(int y = 0 ; y < height ; y++){
                double   un_x = 0, un_y = 0 ;
                double[] result = calc_undistorted_coord (x, y, un_x, un_y) ;
                un_x = result[0];un_y = result[1];
                int crNull = Color.WHITE;
                int cr  = crNull ;
                if ( (un_x > -1) && (un_x < width) && (un_y > -1) && (un_y < height) )
                {
                    // only this range is valid
                    int nSrcX = ((un_x < 0) ? -1 : (int)un_x);
                    int nSrcY = ((un_y < 0) ? -1 : (int)un_y);
                    int nSrcX_1 = nSrcX + 1;
                    int nSrcY_1 = nSrcY + 1;

                    int[] color = new int[4];
                    color[0] = IsInside(width, height, nSrcX, nSrcY) ? clone.getPixelColor(nSrcX,nSrcY) : crNull;
                    color[1] = IsInside(width, height, nSrcX_1, nSrcY) ? clone.getPixelColor(nSrcX_1,nSrcY) : crNull;
                    color[2] = IsInside(width, height, nSrcX, nSrcY_1) ? clone.getPixelColor(nSrcX,nSrcY_1) : crNull;
                    color[3] = IsInside(width, height, nSrcX_1, nSrcY_1) ? clone.getPixelColor(nSrcX_1,nSrcY_1) : crNull;
                    cr = GetBilinear(un_x-nSrcX, un_y-nSrcY, color);
                }
                imageIn.setPixelColor(x, y, cr);
            }
        }
        return imageIn;
    }
}

