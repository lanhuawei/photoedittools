package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/22.
 */
public class SepiaFilter implements IImageFilter{

    private GradientMapFilter gradientMapFx;
    private SaturationModifyFilter saturationFx;
    public SepiaFilter(){
        gradientMapFx = new GradientMapFilter(Gradient.BlackSepia());
        gradientMapFx.ContrastFactor = 0.2f;
        gradientMapFx.BrightnessFactor = 0.1f;

        saturationFx = new SaturationModifyFilter();
        saturationFx.SaturationFactor = -0.6f;
    }

    //@Override
    public Image process(Image imageIn) {
        imageIn = gradientMapFx.process(imageIn);
        return saturationFx.process(imageIn);
    }

}
