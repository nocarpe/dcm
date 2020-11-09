package alg;

import alg.demo.Fire;
import alg.demo.Fire2;
import alg.demo.Node;
import alg.demo2.Point;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : yaoximing
 * @date : 2020/8/27
 * @description :
 **/
public class TestMain {


    public static void main(String[] args) {
        ArrayList<Point> points = getPointList();
        Node[] citys = new Node[points.size()];

        for (int i = 0; i < citys.length; i++) {
            Point point = points.get(i);
            citys[i] = new Node(point.getJ(), point.getW(), point.getSpot());
        }
        int times = 0;
        int i = 0;
        Map<String,Integer> map = new ConcurrentHashMap<>();
        while (true) {
            long time1 = System.currentTimeMillis();
            Fire2 a = new Fire2(citys, 0.998, 0.999, 50, 2000);
            long time2 = System.currentTimeMillis();
            System.out.println("构建时间："+(time2-time1));
            a.Cal();
            long time3 = System.currentTimeMillis();
            System.out.println("计算时间:"+(time3-time2));
            Node[] result = a.getWay();
            for (Node node : result) {
                System.out.print(node.getSpot() + "-->");
            }
            if(a.getLength()==6771.0){
                times++;
            }
            map.put(a.getLength()+"",1);
            i++;
            if(i>200){
                break;
            }
            System.out.println();
        }
//        Fire2 a = new Fire2(citys, 0.998, 0.999, 1000, 50);
//        a.Cal();
//        Node[] result = a.getWay();
//        for (Node node : result) {
//            System.out.print(node.getSpot() + "-->");
//        }
        System.out.println();
        System.out.println("计算次数:"+i);
        System.out.println("一共得出解个数："+map.size());
        System.out.println("最佳结果次数："+times);
       // System.out.println(a.getLength());
    }

    public static ArrayList<Point> getPointList() {
        ArrayList<Point> pointList = new ArrayList<>();
        Point sh = new Point(121.29, 31.14, "上海");
        pointList.add(sh);
        Point wh = new Point(114.21, 30.37, "武汉");
        pointList.add(wh);
//        Point bj = new Point(116.28, 39.54, "北京");
//        pointList.add(bj);
        Point hf = new Point(117.18, 31.51, "合肥");
        pointList.add(hf);
        Point nj = new Point(118.50, 32.02, "南京");
        pointList.add(nj);
        Point hz = new Point(120.09, 30.14, "杭州");
        pointList.add(hz);
        Point cs = new Point(113, 28.11, "长沙");
        pointList.add(cs);
        Point nc = new Point(115.52, 28.41, "南昌");
        pointList.add(nc);
        Point zz = new Point(113.42, 34.48, "郑州");
        pointList.add(zz);
        Point bj = new Point(116.28, 39.54, "北京");
        pointList.add(bj);
        Point tj = new Point(117.11, 39.09, "天津");
        pointList.add(tj);
        Point sjz = new Point(114.28, 38.02, "石家庄");
        pointList.add(sjz);


        Point lz = new Point(103.49, 36.03, "兰州");
        pointList.add(lz);
        Point yc = new Point(106.16, 38.20, "银川");
        pointList.add(yc);
        Point xn = new Point(101.45, 36.38, "西宁");
        pointList.add(xn);
        Point cd = new Point(104.05, 30.39, "成都");
        pointList.add(cd);
        Point gy = new Point(106.42, 26.35, "贵阳");
        pointList.add(gy);
        Point fz = new Point(119.18, 26.05, "福州");
        pointList.add(fz);
        Point gz = new Point(113.15, 23.08, "广州");
        pointList.add(gz);
        return pointList;
    }

}
