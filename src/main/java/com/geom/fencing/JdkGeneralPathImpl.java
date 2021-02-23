package com.geom.fencing;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdkGeneralPathImpl implements PolygonAlgorithm {

    @Override
    public boolean isPointInPolygon(double pointX, double pointY,
                                    List<Map<String, Object>> polygon) {
        Point2D.Double       geoPoint   = AreaGeographic.buildPoint(pointX, pointY);
        List<Point2D.Double> geoPolygon = AreaGeographic.buildPolygon(polygon);
        return isPointInPolygon(geoPoint, geoPolygon);
    }

    @Override
    public boolean isPointInPolygon(double pointX, double pointY,
                                    ArrayList<Double> polygonXA, ArrayList<Double> polygonYA) {
        if (polygonXA.size() != polygonYA.size()) {
            throw new IllegalArgumentException("参数错误");
        }
        List<Map<String, Object>> polygonLst = new ArrayList<>();
        for (int i = 0, len = polygonXA.size(); i < len; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put(LNG_, polygonXA.get(i));
            map.put(LAT_, polygonYA.get(i));
            polygonLst.add(map);
        }
        return isPointInPolygon(pointX, pointY, polygonLst);
    }

    private boolean isPointInPolygon(Point2D.Double point, List<Point2D.Double> polygon) {
        GeneralPath graph = AreaGeographic.buildGraph(polygon);
        return graph.contains(point);
    }

}