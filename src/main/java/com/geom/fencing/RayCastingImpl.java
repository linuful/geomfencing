package com.geom.fencing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RayCastingImpl implements PolygonAlgorithm {
    /**
     * 判断点是否在polygon内
     *
     * @param pointX
     * @param pointY
     * @param polygon
     * @return
     */
    @Override
    public boolean isPointInPolygon(double pointX, double pointY, List<Map<String, Object>> polygon) {

        ArrayList<Double> polygonXA = new ArrayList<>(), polygonYA = new ArrayList<>();
        for (Map<String, Object> pointEntry : polygon) {
            double lng = Double.parseDouble(Objects.toString(pointEntry.get("lng")));
            double lat = Double.parseDouble(Objects.toString(pointEntry.get("lat")));
            polygonXA.add(lng);
            polygonYA.add(lat);
        }
        return isPointInPolygon(pointX, pointY, polygonXA, polygonYA);
    }

    /**
     * 判断点是否在polygon内:射线法判断
     * @param pointX
     * @param pointY
     * @param polygonXA
     * @param polygonYA
     * @return
     */
    @Override
    public boolean isPointInPolygon(double pointX, double pointY,
                                    ArrayList<Double> polygonXA, ArrayList<Double> polygonYA) {
        int  num = polygonXA.size();
        boolean result = false;
        for (int i = 0, j = num - 1; i < num; j = i++) {
            if ((polygonYA.get(i) > pointY) != (polygonYA.get(j) > pointY)
                    && (pointX < (polygonXA.get(j) - polygonYA.get(i)) * (pointY - polygonYA.get(i))
                    / (polygonYA.get(j) - polygonYA.get(i)) + polygonXA.get(i))) {
                result = !result;
            }
        }
        return result;
    }

    private boolean isIntersect(double px1, double py1, double px2, double py2,
                                double px3, double py3, double px4, double py4) {
        boolean flag = false;
        double d = (px2 - px1) * (py4 - py3) - (py2 - py1) * (px4 - px3);
        if (d != 0) {
            double r = ((py1 - py3) * (px4 - px3) - (px1 - px3) * (py4 - py3))
                    / d;
            double s = ((py1 - py3) * (px2 - px1) - (px1 - px3) * (py2 - py1))
                    / d;
            if ((r >= 0) && (r <= 1) && (s >= 0) && (s <= 1)) {
                flag = true;
            }
        }
        return flag;
    }
}
