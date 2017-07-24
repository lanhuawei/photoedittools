package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/22.
 */
/**
 * 光照效果
 * @author daizhj
 *
 */
public class LightFilter extends RadialDistortionFilter{

    /**
     * 光照亮度
     */
    public float Light = 150.0f;

    //@Override
    public Image process(Image imageIn) {
        int width = imageIn.getWidth();
        int halfw = width / 2;
        int height = imageIn.getHeight();
        int halfh = height / 2;
        int R = Math.min(halfw, halfh);
        //中心点，发亮此值会让强光中心发生偏移
        Point point = new Point(halfw, halfh);
        int r = 0, g = 0, b = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                float length = (float)Math.sqrt(Math.pow((x -point.X), 2) + Math.pow((y -point.Y), 2));
                r = imageIn.getRComponent(x, y);
                g = imageIn.getGComponent(x, y);
                b = imageIn.getBComponent(x, y);
                //位于光晕中
                if(length < R) {
                    float pixel = Light * (1.0f - length /R);
                    r = r + (int)pixel;
                    r = Math.max(0, Math.min(r, 255));
                    g = g + (int)pixel;
                    g = Math.max(0, Math.min(g, 255));
                    b = b + (int)pixel;
                    b = Math.max(0, Math.min(b, 255));
                }
                imageIn.setPixelColor(x, y, r, g, b);
            }
        }
        return imageIn;
    }
}
