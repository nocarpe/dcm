package alg.ga;

import java.io.File;
import jxl.Sheet;
import jxl.Workbook;

public class excelData {

    public static String getType(Object o) { //获取变量类型方法
        return o.getClass().toString(); //使用int类型的getClass()方法
    }

    public static int cha_int(String temp) {
        Integer one = Integer.parseInt(temp);
        return one;
    }

    public static int backIndex(String temp) { //获取目标地在excel中的索引
        int tempIndex = 0;
        //try { 
        //Workbook book;
        //book= Workbook.getWorkbook(new File("F:/test/summer_task/dis.xls")); 
        //    Sheet sheet = book.getSheet(0);
        //    int n=sheet.getColumns();//返回列数
        //    int m=sheet.getRows();//返回行数
        //    int back;
        //    for(int i=0;i<n;i++)
        //    {
        //      if(sheet.getCell(i,0).getContents().equals(temp)==true)
        //        {
        //          tempIndex=i;
        //        }
        //      
        //    }
        //    book.close(); 
        //}
        //catch(Exception e)  { } 
        for (int i = 0; i < excelData.length; i++) {
            if (excelData[0][i].equals(temp) == true) {
                tempIndex = i;
            }
        }

        return tempIndex;
    }

    public static int backDis(int n, int m) {//返回目标值
        int back = cha_int(excelData[n][m]);
        //try { 
        //  Workbook book;
        //    book= Workbook.getWorkbook(new File("F:/test/summer_task/dis.xls")); 
        //    Sheet sheet = book.getSheet(0);
        //    back=cha_int(sheet.getCell(n,m).getContents());
        //    
        //    book.close(); 
        // }
        //catch(Exception e)  { } 

        return back;
    }

    public static String[][] backExcel() {
        String[][] temp = null;
        try {
            Workbook book;
            book = Workbook.getWorkbook(new File("dcm-domain/resources/dis.xls"));
            Sheet sheet = book.getSheet(0);
            int n = sheet.getColumns();//返回列数
            int m = sheet.getRows();//返回行数
            temp = new String[m][n];
            int back;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = sheet.getCell(j, i).getContents();
                }

            }
            book.close();
        } catch (Exception e) {
        }
        return temp;
    }

    public static String[][] excelData = backExcel();

    public static int[] keyInit() {
        String[] location = {"南京", "上海", "苏州", "无锡", "常州", "南通", "镇江", "盐城", "泰州", "淮安", "杭州", "宁波"};
        int[] loc = new int[location.length];
        for (int i = 0; i < location.length; i++) {
            loc[i] = backIndex(location[i]);
        }
        return loc;
    }

}