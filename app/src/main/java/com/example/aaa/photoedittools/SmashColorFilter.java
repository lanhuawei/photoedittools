package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/22.
 */
import com.example.aaa.photoedittools.ImageBlender.BlendMode;

/**
 * 碎花效果
 * @author daizhj
 *
 */
public class SmashColorFilter implements IImageFilter{
    //@Override
    public Image process(Image imageIn) {
        ParamEdgeDetectFilter pde = new ParamEdgeDetectFilter();
        pde.K00 = 1;
        pde.K01 = 2;
        pde.K02 = 1;
        pde.Threshold = 0.25f;
        pde.DoGrayConversion = false;
        pde.DoInversion = false;
        ImageBlender ib = new ImageBlender();
        ib.Mode = (int)BlendMode.LinearLight;
        ib.Mixture = 2.5f;
        return ib.Blend(imageIn.clone(), pde.process(imageIn));
    }
}