package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/22.
 */
import com.example.aaa.photoedittools.IImageFilter.Function;


public class PosterizeFilter extends LUTFilter{

    int   _level ;
    public int InitLUTtable (int LUTIndex)
    {
        double  d = 255.0 / (_level - 1.0) ;
        int     n = (int)(LUTIndex / d + 0.5) ; // round
        return Function.FClamp0255 (d * n) ; // round
    }

    public PosterizeFilter (int nLevel)
    {
        _level = ((nLevel >= 2) ? nLevel : 2) ;
    }
}
