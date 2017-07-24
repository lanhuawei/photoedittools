package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/22.
 */
import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import com.example.aaa.photoedittools.Gradient.TintColors;
import com.example.aaa.photoedittools.ImageBlender.BlendMode;

/**
 * X光效果
 * @author daizhj
 *
 */
public class XRadiationFilter implements IImageFilter{

    private GradientMapFilter gradientMapFx = new GradientMapFilter();
    private ImageBlender blender = new ImageBlender();

    public XRadiationFilter(){
        List<Integer> colors = new ArrayList<Integer>();
        colors.add(TintColors.LightCyan());
        colors.add(Color.BLACK);
        gradientMapFx.Map = new Gradient(colors);
        blender.Mode = BlendMode.ColorBurn;
        blender.Mixture = 0.8f;
    }

    //@Override
    public Image process(Image imageIn) {
        imageIn = this.gradientMapFx.process(imageIn);
        imageIn = this.blender.Blend(imageIn, imageIn);
        return imageIn;
    }
}
