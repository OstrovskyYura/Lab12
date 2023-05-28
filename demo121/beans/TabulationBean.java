package com.example.demo121.beans;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
@Component
@Getter
@SessionScope
public class TabulationBean {
    private List<Point> points;
    public int steps (double end, double start, double step) {
        return (int)round((end - start) / step) + 1;
    }
    public double calcY (double x, double a, double b, double c) {
        double y;
        double eps = 0.0001;
        if (x < 1.4 - eps)
            y = a*x*x + b*x + c;
        else if (x > 1.4 + eps)
            y = (a + b*x) / sqrt(x*x + 1);
        else y = a/x + sqrt(x*x + 1);
        return y;
    }

    public double[] massX (double end, double start, double step) {
        double [] ar1 = new double[steps(end, start, step)];
        for (int i = 0; i < ar1.length; i++)
            ar1[i] = start + step*i;
        return ar1;
    }
    public double[] massY (double [] ar1, double a, double b, double c) {
        double [] ar2 = new double[ar1.length];
        for (int i = 0; i < ar2.length; i++)
            ar2[i] = calcY(ar1[i], a, b, c);
        return ar2;
    }
    public int maxYI (double [] ar2) {
        int maxi = 0;
        double max = ar2[0];
        for (int i = 1; i < ar2.length; i++)
            if (ar2[i] > max) {
                max = ar2[i];
                maxi = i;
            }
        return maxi;
    }
    public double getMaxY(double[] ar2) {
        int maxY = maxYI(ar2);
        return ar2[maxY];
    }
    public double getMaxX(double[] ar2, double[] ar1) {
        int maxY = maxYI(ar2);
        return ar1[maxY];
    }
    public int minYI (double [] ar2) {
        int mini = 0;
        double min = ar2[0];
        for (int i = 1; i < ar2.length; i++)
            if (ar2[i] < min) {
                min = ar2[i];
                mini = i;
            }
        return mini;
    }
    public double getMinY(double[] ar2) {
        int minY = minYI(ar2);
        return ar2[minY];
    }
    public double getMinX(double[] ar2, double[] ar1) {
        int minY = minYI(ar2);
        return ar1[minY];
    }
    public double sum (double [] ar2) {
        double sum = 0.0;
        for (double v : ar2) sum += v;
        return sum;
    }
    public double am (double [] ar2) {
        double sum = sum(ar2);
        double average = 0;
        average = sum / ar2.length;
        return average;
    }
    public void fillPoints(double a, double b, double c, double start, double end, double step){
        points = new ArrayList<Point>();
        int n = (int) round((end - start)/step +1);
        for (int i = 0; i<n;i++){
            double x = start  + i * step;
            double y = a * Math.pow(x, 2) + b * x + c;
            points.add(new Point(x,y));
        }
    }
}

