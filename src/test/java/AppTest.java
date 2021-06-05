import com.geom.fencing.AreaGeographic;
import com.geom.fencing.Geo2ImageHelper;
import com.geom.fencing.JdkGeneralPathImpl;
import com.geom.fencing.PolygonAlgorithm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.geom.fencing.PolygonAlgorithm.LAT_;
import static com.geom.fencing.PolygonAlgorithm.LNG_;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * 计算2点之间的距离
     */
    @Test
    public void test1(){
//        Scanner input= new Scanner(System.in);
//        System.out.println("Enter point1's x-,y-coordinates:");
//        double x1=input.nextDouble();
//        double y1=input.nextDouble();
//        System.out.println("Enter point2's x-,y-coordinates:");
//        double x2=input.nextDouble();
//        double y2=input.nextDouble();
        double x1 = 120.212061,y1 = 30.272734;
        double x2 = 120.197185,y2 = 30.270925;
        Point2D p1 =new Point2D.Double(x1,y1);
        Point2D p2 =new Point2D.Double(x2,y2);
        System.out.println("p1 is"+p1.toString());
        System.out.println("p2 is"+p2.toString());
        System.out.println("distance is"+p1.distance(p2));
    }
    private static List<Map<String, Object>> polygon = new ArrayList<>();

    @BeforeAll
    public static void buildArea1(){
        Map<String, Object> point1 = new HashMap<>();
        //打铁关，120.183171,30.290885
        point1.put(LNG_,120.183171);
        point1.put(LAT_,30.290885);
        polygon.add(point1);
        //闸弄口，120.198909,30.290136
        Map<String, Object> point2 = new HashMap<>();
        point2.put(LNG_,120.198909);
        point2.put(LAT_,30.290136);
        polygon.add(point2);
        //艮秋立交桥，120.206311,30.282402
        Map<String, Object> point3 = new HashMap<>();
        point3.put(LNG_,120.206311);
        point3.put(LAT_,30.282402);
        polygon.add(point3);
        //凤起广场，120.206922,30.269334
        Map<String, Object> point4 = new HashMap<>();
        point4.put(LNG_,120.206922);
        point4.put(LAT_,30.269334);
        polygon.add(point4);
        //庆菱路 120.1997,30.263376
        Map<String, Object> point5 = new HashMap<>();
        point5.put(LNG_,120.1997);
        point5.put(LAT_,30.263376);
        polygon.add(point5);
        //建国北路 120.187698,30.270301
        Map<String, Object> point6 = new HashMap<>();
        point6.put(LNG_,120.187698);
        point6.put(LAT_,30.270301);
        polygon.add(point6);
        //滨河广场，120.186836,30.2742
        Map<String, Object> point7 = new HashMap<>();
        point7.put(LNG_,120.186836);
        point7.put(LAT_,30.2742);
        polygon.add(point7);
        //艮山运河公园，120.192981,30.281872
        Map<String, Object> point8 = new HashMap<>();
        point8.put(LNG_,120.192981);
        point8.put(LAT_,30.281872);
        polygon.add(point8);
        AreaGeographic.buildGeoArea("Area1",polygon);


        try {
            List<double[]> coordinates = AreaGeographic.buildCoordinates(polygon);
            FileOutputStream fos = null;
            fos = new FileOutputStream(new File("aaaa.jpg"));
            Geo2ImageHelper h = new Geo2ImageHelper(1000, coordinates, Color.RED, fos);
            h.draw();
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @BeforeAll
    public static void buildArea2(){
        List<Map<String, Object>> polygon = new ArrayList<>();
        Map<String, Object> point1 = new HashMap<>();
        //P1,120.214109,30.293036
        point1.put(LNG_,120.214109);
        point1.put(LAT_,30.293036);
        polygon.add(point1);
        //P8,120.21648,30.290292
        Map<String, Object> point2 = new HashMap<>();
        point2.put(LNG_,120.21648);
        point2.put(LAT_,30.290292);
        polygon.add(point2);
        //P7,120.220864,30.289419
        Map<String, Object> point3 = new HashMap<>();
        point3.put(LNG_,120.220864);
        point3.put(LAT_,30.289419);
        polygon.add(point3);
        //p5,120.227763,30.284492
        Map<String, Object> point4 = new HashMap<>();
        point4.put(LNG_,120.227763);
        point4.put(LAT_,30.284492);
        polygon.add(point4);
        //P6,120.22302,30.280313
        Map<String, Object> point5 = new HashMap<>();
        point5.put(LNG_,120.22302);
        point5.put(LAT_,30.280313);
        polygon.add(point5);
        //P4,120.226829,30.273077
        Map<String, Object> point6 = new HashMap<>();
        point6.put(LNG_,120.226829);
        point6.put(LAT_,30.273077);
        polygon.add(point6);
        //P3,120.214324,30.270831
        Map<String, Object> point7 = new HashMap<>();
        point7.put(LNG_,120.214324);
        point7.put(LAT_,30.270831);
        polygon.add(point7);
        //P2,120.213749,30.281872
        Map<String, Object> point8 = new HashMap<>();
        point8.put(LNG_,120.213749);
        point8.put(LAT_,30.281872);
        polygon.add(point8);
        AreaGeographic.buildGeoArea("Area2",polygon);


        try {
            List<double[]> coordinates = AreaGeographic.buildCoordinates(polygon);
            FileOutputStream fos = null;
            fos = new FileOutputStream(new File("bbbb.jpg"));
            Geo2ImageHelper h = new Geo2ImageHelper(1000, coordinates, Color.RED, fos);
            h.draw();
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    /**
     * 计算是否在某个多边形内
     */
    @Test
    public void test2(){
        double tx1=120.187375,ty1=30.284055;//流水苑，120.187375,30.284055, 外
        double tx2=120.192981,ty2=30.281872;//艮山运河公园，120.192981,30.281872 点上
        double tx3=120.213821,ty3=30.281997;//新塘，120.213821,30.281997 外
        double tx4=120.198909,ty4=30.290136;//闸弄口，120.198909,30.290136，点上
        double tx5=120.192693,ty5=30.275323;//艮园社区, 120.192693,30.275323，内
        double tx6=120.186836,ty6=30.2742;//滨河广场，120.186836,30.2742 点上
        double tx7=120.192621,ty7=30.27551;//120.18795,30.279938 外面、120.192621,30.27551
        PolygonAlgorithm algorithm = new JdkGeneralPathImpl();
        System.out.printf("\n1. %f,%f isPointInPolygon: %b.\n",tx1,ty1,
                algorithm.isPointInPolygon(tx1,ty1,polygon));
        System.out.printf("\n2. %f,%f isPointInPolygon: %b.\n",tx2,ty2,
                algorithm.isPointInPolygon(tx2,ty2,polygon));
        System.out.printf("\n3. %f,%f isPointInPolygon: %b.\n",tx3,ty3,
                algorithm.isPointInPolygon(tx3,ty3,polygon));
        System.out.printf("\n4. %f,%f isPointInPolygon: %b.\n",tx4,ty4,
                algorithm.isPointInPolygon(tx4,ty4,polygon));
        System.out.printf("\n5. %f,%f isPointInPolygon: %b.\n",tx5,ty5,
                algorithm.isPointInPolygon(tx5,ty5,polygon));
        System.out.printf("\n6. %f,%f isPointInPolygon: %b.\n",tx6,ty6,
                algorithm.isPointInPolygon(tx6,ty6,polygon));
        System.out.printf("\n7. %f,%f isPointInPolygon: %b.\n",tx7,ty7,
                algorithm.isPointInPolygon(tx7,ty7,polygon));

    }

    /**
     * 计算点的归属
     */
    @Test
    public void test3(){
        double tx1=120.177673,ty1=30.286425;    //120.177673,30.286425  左外
        double tx2=120.209222,ty2=30.289232;    //120.209222,30.289232  中外
        double tx3=120.19952,ty3=30.283494;     //120.19952,30.283494   Area1
        double tx4=120.217271,ty4=30.276508;    //120.217271,30.276508  Area2
        double tx5=120.236387,ty5=30.273825;    //120.236387,30.273825  右外
        double tx6=120.217199,ty6=30.275135;    //120.217199,30.275135  Area2
        double tx7=120.193699,ty7=30.27133;     //120.193699,30.27133   Area1

        System.out.println("current:" + System.currentTimeMillis());
        System.out.printf("\n1. %f,%f in Area: %s.\n",tx1,ty1,
                AreaGeographic.acquireAreaOfPoint(tx1,ty1));
        System.out.printf("\n2. %f,%f in Area: %s.\n",tx2,ty2,
                AreaGeographic.acquireAreaOfPoint(tx2,ty2));
        System.out.printf("\n3. %f,%f in Area: %s.\n",tx3,ty3,
                AreaGeographic.acquireAreaOfPoint(tx3,ty3));
        System.out.printf("\n4. %f,%f in Area: %s.\n",tx4,ty4,
                AreaGeographic.acquireAreaOfPoint(tx4,ty4));
        System.out.printf("\n5. %f,%f in Area: %s.\n",tx5,ty5,
                AreaGeographic.acquireAreaOfPoint(tx5,ty5));
        System.out.printf("\n6. %f,%f in Area: %s.\n",tx6,ty6,
                AreaGeographic.acquireAreaOfPoint(tx6,ty6));
        System.out.printf("\n7. %f,%f in Area: %s.\n",tx7,ty7,
                AreaGeographic.acquireAreaOfPoint(tx7,ty7));
        System.out.println("current:" + System.currentTimeMillis());
        //1613819250149 - 1613819250142
    }


    /**
     * 测试对象内存布局
     */
    @Test
    public void test4(){
        Object obj = new Object();
        //System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }


}

