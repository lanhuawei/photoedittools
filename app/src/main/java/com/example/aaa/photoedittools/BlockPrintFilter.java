package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/21.
 */
import com.example.aaa.photoedittools.ImageBlender.BlendMode;

/**
 * 版画效果
 *
 *
 */
public class BlockPrintFilter implements IImageFilter {
    //@Override
    public Image process(Image imageIn) {
        ParamEdgeDetectFilter pde = new ParamEdgeDetectFilter();
        pde.K00 = 1;
        pde.K01 = 2;
        pde.K02 = 1;
        pde.Threshold = 0.25f;
        pde.DoGrayConversion = false;
        ImageBlender ib = new ImageBlender();
        ib.Mode = (int)BlendMode.Multiply;
        return ib.Blend(imageIn.clone(), pde.process(imageIn));
    }
}

