package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/22.
 */
import java.util.List;

import com.example.aaa.photoedittools.ImageBlender.BlendMode;


public class RainBowFilter implements IImageFilter{

    public ImageBlender blender = new ImageBlender();
    public boolean IsDoubleRainbow = false;
    private GradientFilter gradientFx;
    public float gradAngleDegree = 40f;

    public RainBowFilter(){
        blender.Mixture = 0.25f;
        blender.Mode = BlendMode.Additive;

        IsDoubleRainbow = true;

        List<Integer> rainbowColors = Gradient.RainBow().MapColors;
        if (this.IsDoubleRainbow)
        {
            rainbowColors.remove(rainbowColors.size() - 1);//remove red
            rainbowColors.addAll(Gradient.RainBow().MapColors);
        }
        gradientFx = new GradientFilter();
        gradientFx.OriginAngleDegree = gradAngleDegree;
        gradientFx.Gradient = new Gradient(rainbowColors);
    }

    //@Override
    public Image process(Image imageIn) {
        Image clone =  gradientFx.process(imageIn.clone());
        return blender.Blend(imageIn, clone);
    }
}
