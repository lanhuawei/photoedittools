package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/22.
 */
public class MonitorFilter implements IImageFilter {

    //@Override
    public Image process(Image imageIn) {
        int r,g,b;
        for (int x = 0; x < imageIn.getWidth(); x++) {
            for (int y = 0; y < imageIn.getHeight(); y+=3) {
                r=0;
                g=0;
                b=0;
                for(int w=0; w<3; w++){
                    if(y+w < imageIn.getHeight() ){
                        r += (imageIn.getRComponent(x, y+w))/2;
                        g += (imageIn.getGComponent(x, y+w))/2;
                        b += (imageIn.getBComponent(x, y+w))/2;
                    }
                }
                r = getValidInterval(r);
                g = getValidInterval(g);
                b = getValidInterval(b);

                for(int w=0; w<3; w++){
                    if(y+w < imageIn.getHeight()){
                        if(w == 0){
                            imageIn.setPixelColor(x,y+w,r,0,0);
                        }
                        else if(w ==1){
                            imageIn.setPixelColor(x,y+w,0,g,0);
                        }
                        else if(w==2){
                            imageIn.setPixelColor(x,y+w,0,0,b);
                        }
                    }
                }
            }
        }
        return imageIn;
    }


    /**
     * method to calculate an appropriate interval for flicker lines
     *
     * @param a_value
     * @return
     */
    public int getValidInterval(int a_value){
        if(a_value < 0) return 0;
        if(a_value > 255) return 255;
        return a_value;
    }

}
