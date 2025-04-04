package com.dcm.thread.alg.d24;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.util.Strings;

/**
 * @author : yyyao
 * @date : 2025/1/15
 * @description :
 **/
public class Ip {

    List<String> ans = new ArrayList<>();
    List<String> t = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 4);
        return ans;
    }

    private void dfs(String s, int i, int k) {
        if (k == 0) {
            if (i == s.length()) {
                ans.add(String.join(".", t));
                return;
            }
        }
        for (int j = i; j < i + 3 && j < s.length(); j++) {
            if (s.charAt(i) == '0' && j > i) {//忽略头
                return;
            }
            int v = Integer.parseInt(s.substring(i, j + 1));
            if (v >= 0 && v <= 255) {
                t.add(s.substring(i, j + 1));
                dfs(s, j + 1, k - 1);
                t.remove(t.size() - 1);
            }
        }
    }







    public static void main(String[] args) {
        System.out.println(new Ip().restoreIpAddresses("101023"));
    }


}
