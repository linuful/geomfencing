package com.geom.fencing;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.*;

import static com.geom.fencing.PolygonAlgorithm.*;

public class GeoAreaPolygon {
    //<name, graph>
    private static final Map<String,GeneralPath> areaPolygons;
    static {
        areaPolygons = new HashMap<>();
    }

    public static void buildGeoArea(String areaName,List<Map<String, Object>> polygonPoints){
        List<Point2D.Double> geoPolygon = buildPolygon(polygonPoints);
        GeneralPath graph = buildGraph(geoPolygon);
        areaPolygons.put(areaName,graph);
    }

    public static String acquireAreaOfPoint(double pointX, double pointY){
        if (areaPolygons.isEmpty()){
            System.out.println("Please build geo area first.");
            return EMPTY_;
        }
        Point2D.Double point = buildPoint(pointX,pointY);
        for (Map.Entry<String, GeneralPath> area : areaPolygons.entrySet()) {
            String name = area.getKey();
            GeneralPath p = area.getValue();
            if (p.contains(point)){
                return name;
            }
        }
        return EMPTY_;
    }

    public static ArrayList<String> acquireAreasOfPoint(double pointX, double pointY){
        ArrayList<String> areas = new ArrayList<>();
        if (areaPolygons.isEmpty()){
            System.out.println("Please build geo area first.");
            return areas;
        }
        Point2D.Double point = buildPoint(pointX,pointY);
        areaPolygons.forEach((name,graph) ->{
            if (graph.contains(point)){
                areas.add(name);
            }
        });
        return areas;
    }

    public static Point2D.Double buildPoint(double x, double y) {
        return new Point2D.Double(x, y);
    }

    public static List<Point2D.Double> buildPolygon(List<Map<String, Object>> polygonPoints) {
        List<Point2D.Double> geoPolygon = new ArrayList<>();
        for (Map<String, Object> map : polygonPoints) {
            double lng = Double.parseDouble(Objects.toString(map.get(LNG_)));
            double lat = Double.parseDouble(Objects.toString(map.get(LAT_)));
            geoPolygon.add(buildPoint(lng,lat));
        }
        return geoPolygon;
    }

    public static GeneralPath buildGraph(List<Point2D.Double> polygon){
        GeneralPath p = new GeneralPath();
        Point2D.Double first = polygon.get(0);
        p.moveTo(first.x, first.y);
        polygon.remove(0);
        for (Point2D.Double pg : polygon) {
            p.lineTo(pg.x, pg.y);
        }
        p.lineTo(first.x, first.y);
        p.closePath();
        return p;
    }
}
