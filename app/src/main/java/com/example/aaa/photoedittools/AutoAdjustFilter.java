package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/21.
 */
/**
 * 自动校正效果
 *
 *
 */
public class AutoAdjustFilter implements IImageFilter{
    //@Override
    public Image process(Image imageIn) {
        HistogramEqualFilter hee = new HistogramEqualFilter();
        hee.ContrastIntensity = 0.5f;
        imageIn = hee.process(imageIn);

        AutoLevelFilter ale = new AutoLevelFilter();
        ale.Intensity = 0.5f;
        return ale.process(imageIn);
    }
}
