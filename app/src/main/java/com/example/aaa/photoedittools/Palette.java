package com.example.aaa.photoedittools;

/**
 * Created by aaa on 2016/1/22.
 */
public class Palette
{
    public int[] Blue;
    public int[] Green;
    public int Length;
    public int[] Red;

    public Palette(int length)
    {
        this.Length = length;
        this.Red = new int[length];
        this.Green = new int[length];
        this.Blue = new int[length];
    }
}
