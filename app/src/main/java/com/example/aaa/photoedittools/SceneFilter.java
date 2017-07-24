package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/22.
 */
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.aaa.photoedittools.ImageBlender.BlendMode;
import android.util.Log;

/**
 * 情景效果（用于自然景色渲染）
 * @author daizhj
 *
 */
public class SceneFilter implements IImageFilter{
    private GradientFilter gradientFx;
    private SaturationModifyFilter saturationFx;

    public SceneFilter(float angle, Gradient gradient)
    {
        gradientFx = new GradientFilter();
        gradientFx.Gradient = gradient;
        gradientFx.OriginAngleDegree = angle;

        saturationFx = new SaturationModifyFilter();
        saturationFx.SaturationFactor = -0.6f;
    }

    //@Override
    public Image process(Image imageIn)
    {
        Image clone = imageIn.clone();
        imageIn = gradientFx.process(imageIn);
        ImageBlender blender = new ImageBlender();
        blender.Mode = BlendMode.Subractive;
        return saturationFx.process(blender.Blend(clone, imageIn));
        //return imageIn;// saturationFx.process(imageIn);
    }
}