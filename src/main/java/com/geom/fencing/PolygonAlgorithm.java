package com.geom.fencing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface PolygonAlgorithm {
    String LNG_ = "lng";
    String LAT_ = "lat";
    String EMPTY_ = "default";

    /**
     * 判断一个点是否在某个多边形内
     * @param pointX
     * @param pointY
     * @param polygon
     * @return
     */
    boolean isPointInPolygon(double pointX, double pointY,
                             List<Map<String, Object>> polygon);

    /**
     * 判断某个点是否在多边形内
     * @param pointX
     * @param pointY
     * @param polygonXA
     * @param polygonYA
     * @return
     */
    boolean isPointInPolygon(double pointX, double pointY,
                             ArrayList<Double> polygonXA, ArrayList<Double> polygonYA);
}
