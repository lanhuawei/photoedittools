package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/22.
 */
import java.util.ArrayList;
import java.util.List;
import android.graphics.Color;

/**
 * 夜视效果
 * @author daizhj
 *
 */
public class NightVisionFilter implements IImageFilter{

    private NoiseFilter noisefx = new NoiseFilter();
    private ImageBlender blender = new ImageBlender();
    private VignetteFilter vignetteFx = new VignetteFilter();
    private GradientMapFilter gradientFx = new GradientMapFilter();

    public NightVisionFilter(){
        noisefx.Intensity = 0.15f;

        vignetteFx.Size = 1f;

        List<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.BLACK);
        colors.add(Color.GREEN);
        gradientFx.Map = new Gradient(colors);
        gradientFx.BrightnessFactor = 0.2f;
    }

    //@Override
    public Image process(Image imageIn) {
        imageIn = noisefx.process(imageIn);
        imageIn = gradientFx.process(imageIn);
        imageIn = vignetteFx.process(imageIn);
        return imageIn;
    }
}
