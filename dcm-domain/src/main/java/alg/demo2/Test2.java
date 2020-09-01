package alg.demo2;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : yaoximing
 * @date : 2020/8/27
 * @description :
 **/
public class Test2 {

    public static void main(String[] args) {
        ArrayList<String> pointList = new ArrayList<>();
        pointList.add("上海");
        pointList.add("武汉");
        pointList.add("南京");
        pointList.add("合肥");
        pointList.add("杭州");
        pointList.add("北京");
        pointList.add("郑州");
        pointList.add("天津");
        pointList.add("石家庄");
        pointList.add("南昌");
        pointList.add("长沙");
        int[][] pointPath = new int[pointList.size()][pointList.size()];
        String pointOne;
        String pointTwo;
        if (pointList != null) {
            for (int i = 0; i < pointList.size(); i++) {
                pointOne = pointList.get(i);
                for (int j = 0; j < pointList.size(); j++) {
                    pointTwo = pointList.get(j);
                    pointPath[i][j] = DistanceUtil.getDistance(pointOne, pointTwo);
                }

            }
            int times = 0;
            int i = 0;
            Map<String,Integer> map = new ConcurrentHashMap<>();
            while (true) {

                Tsp tsp = new Tsp(pointPath, pointList.size(), pointList);
                String rstr = tsp.run();
                map.put(rstr,1);
                if (rstr.equals("2653")) {
                    times++;
                }
                i++;
                if (i > 1500) {
                    break;
                }
            }
            System.out.println(map.size());
            System.out.println("最佳结果次数"+times);

            //System.out.println("最短路劲"+bestPath);
        }


    }


    public static void test() {
        System.out.println((int) (Math.random() * 3));

    }

}
