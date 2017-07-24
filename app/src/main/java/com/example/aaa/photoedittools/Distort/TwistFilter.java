package com.example.aaa.photoedittools.Distort;

/**
 * Created by aaa on 2016/1/22.
 */
import  com.example.aaa.photoedittools.IImageFilter;

public class TwistFilter extends BilinearDistort{
    double   _twist ;
    double   _size ;
    double   _offsetX ;
    double   _offsetY ;

    public TwistFilter(int amount, int size){
        this(amount, size, 0, 0);
    }
    public TwistFilter (int amount, int size, double offsetX, double offsetY)
    {
        amount = -amount ;
        _twist = amount * amount * ((amount > 0) ? 1 : -1) ;

        _size = 1.0 / (Function.FClamp(size, 1, 200) / 100.0) ;
        _offsetX = Function.FClampDouble(offsetX, -2.0, 2.0) ;
        _offsetY = Function.FClampDouble(offsetY, -2.0, 2.0) ;
    }

    public double[] calc_undistorted_coord (int x, int y, double un_x, double un_y)
    {
        double	width = clone.getWidth() / 2.0 ;
        double	height = clone.getHeight() / 2.0 ;
        double	invmaxrad = 1.0 / (width < height ? width : height) ;
        width += _offsetX * width ;
        height += _offsetY * height ;

        double   u = x - width ;
        double   v = y - height ;
        double   r = Math.sqrt(u*u + v*v) ;
        double   theta = Math.atan2(v, u) ;

        double   t = 1 - ((r * _size) * invmaxrad) ;
        t = (t < 0) ? 0 : (t * t * t) ;
        theta += (t * _twist) / 100.0 ;

        un_x = Function.FClampDouble (width + r * Math.cos(theta), 0.0, clone.getWidth()-1.0) ;
        un_y = Function.FClampDouble (height + r * Math.sin(theta), 0.0, clone.getHeight()-1.0) ;
        return new double[]{un_x, un_y};
    }
}

