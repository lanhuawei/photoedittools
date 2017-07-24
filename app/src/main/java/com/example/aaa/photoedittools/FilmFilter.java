package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/21.
 */
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.aaa.photoedittools.ImageBlender.BlendMode;
import android.util.Log;

/**
 * 电影院效果
 * @author daizhj
 *
 */
public class FilmFilter implements IImageFilter{
    private GradientFilter gradient;
    private SaturationModifyFilter saturationFx;

    public FilmFilter(float angle)
    {
        gradient = new GradientFilter();
        gradient.Gradient = Gradient.Fade();
        gradient.OriginAngleDegree = angle;

        saturationFx = new SaturationModifyFilter();
        saturationFx.SaturationFactor = -0.6f;
    }

    //@Override
    public Image process(Image imageIn)
    {
        Image clone = imageIn.clone();
        imageIn = gradient.process(imageIn);
        ImageBlender blender = new ImageBlender();
        blender.Mode = BlendMode.Multiply;
        return saturationFx.process(blender.Blend(clone, imageIn));
        //return imageIn;// saturationFx.process(imageIn);
    }

}
