package com.geom.fencing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
/**
 * 经纬度输出图形
 * @author
 */
public class Geo2ImageHelper {
    // {"type":"Polygon","coordinates":[[[108.80069129,18.5200639],[108.80212735,18.51905788],[108.80250244,18.51966251],[108.80097529,18.52047037],[108.80093242,18.52042465],[108.80069129,18.5200639]]]}
    private int imageWidthHeight = 1000;
    private List<double[]> coordinates;
    private Color color;
    private OutputStream os;
    /**
     *
     * @param imageWidthHeight 图片大小像素  1000比较好
     * @param coordinates 经纬度数组
     * @param color 画的颜色
     * @param os  需要输出的流，记得输出完了关闭流，这里没有关，记得要byte[] 直接new一个bytearrayoutputstream。。。。
     */
    public Geo2ImageHelper(int imageWidthHeight, List<double[]> coordinates, Color color, OutputStream os) {
        super();
        this.imageWidthHeight = imageWidthHeight;
        this.coordinates = coordinates;
        this.color = color;
        this.os = os;
    }

    public void draw() throws IOException {
        BufferedImage image = new BufferedImage(imageWidthHeight, imageWidthHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D  g = (Graphics2D) image.getGraphics();
        g.setColor(Color.WHITE);//设置笔刷白色
        g.fillRect(0,0,imageWidthHeight,imageWidthHeight);//填充整个屏幕
        g.setColor(color);
        g.setStroke(new BasicStroke(5.0f));//线粗
        double minLng = 0D;
        double maxLng = 0D;
        double minLat = 0D;
        double maxLat = 0D;
        double xishu = 0D;//经纬度与图片的转换系数，图形不可能是方形的，要把图片放最中间，算个偏移量
        int x_pianyi = 0;
        int y_pianyi = 0;
        {// 计算最大最小经纬度

            for (double[] d : coordinates) {
                if (minLng == 0D || d[0] < minLng) {
                    minLng = d[0];
                }
                if (maxLng == 0D || d[0] > maxLng) {
                    maxLng = d[0];
                }
                if (minLat == 0D || d[1] < minLat) {
                    minLat = d[1];
                }
                if (maxLat == 0D || d[1] > maxLat) {
                    maxLat = d[1];
                }
            }

        }

        {// 算偏移量
            double t1 = maxLng - minLng;
            double t2 = maxLat - minLat;
            double t3 = t1 > t2 ? t1 : t2;
            xishu = imageWidthHeight / t3;
            if (t1 > t2) {
                //x轴心偏移
                y_pianyi = (int) ((imageWidthHeight / 2 - (int) (t2 * xishu/2))) + 1;
            } else {
                //y轴心偏移
                x_pianyi = (int) ((imageWidthHeight / 2 - (int) (t1 * xishu/2)) + 1);
            }
        }

        {// MB 开始画图
            double[] firstC = coordinates.get(0);
            coordinates.add(firstC);
            double[] tmpPoint = null;
            for (double[] d : coordinates) {
                if (tmpPoint == null) {
                    tmpPoint = d;
                    continue;
                }
                int x1 = (int) ((maxLng - tmpPoint[0]) * xishu);
                int y1 = (int) ((maxLat - tmpPoint[1]) * xishu);
                int x2 = (int) ((maxLng - d[0]) * xishu);
                int y2 = (int) ((maxLat - d[1]) * xishu);
                x1 = x1 >= 0 ? x1 : 1000 + x1;
                y1 = y1 >= 0 ? y1 : 1000 + y1;
                x2 = x2 >= 0 ? x2 : 1000 + x2;
                y2 = y2 >= 0 ? y2 : 1000 + y2;
                x1=+imageWidthHeight - x1-x_pianyi;
                y1=y_pianyi+y1;
                x2=imageWidthHeight - x2-x_pianyi;
                y2=y_pianyi+y2;
                {//防止线太靠边了
                    if(x1>imageWidthHeight-5){//减去线宽
                        x1=imageWidthHeight-5;
                    }else if(x1<5){//加上线宽
                        x1=5;
                    }
                    if(x2>imageWidthHeight-5){//减去线宽
                        x2=imageWidthHeight-5;
                    }else if(x2<5){//加上线宽
                        x2=5;
                    }
                    if(y1>imageWidthHeight-5){//减去线宽
                        y1=imageWidthHeight-5;
                    }else if(y1<5){//加上线宽
                        y1=5;
                    }
                    if(y2>imageWidthHeight-5){//减去线宽
                        y2=imageWidthHeight-5;
                    }else if(y2<5){//加上线宽
                        y2=5;
                    }
                }
                g.drawLine(x1, y1, x2, y2);
                tmpPoint = d;
            }
        }
        g.dispose();
        ImageIO.write(image, "JPG", os);
    }
}

