package file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;

/**
 * 通过word模板生成新的word工具类
 */
public class WordUtils {

    /**
     * 根据模板生成word
     *
     * @param path 模板的路径
     * @param params 需要替换的参数
     * @param tableList 需要插入的参数
     * @param fileName 生成word文件的文件名
     */
    public void getWord(String path, Map<String, Object> params, List<String[]> tableList, String fileName)
        throws Exception {
        File file = new File(path);
        InputStream is = new FileInputStream(file);
        CustomXWPFDocument doc = new CustomXWPFDocument(is);
        this.replaceInPara(doc, params);    //替换文本里面的变量
        this.replaceInTable(doc, params, tableList); //替换表格里面的变量

        File file1 = new File("D:/test.docx");
        doc.write(new FileOutputStream(file1));//文档写入流


    }

    /**
     * 替换段落里面的变量
     *
     * @param doc 要替换的文档
     * @param params 参数
     */
    private void replaceInPara(CustomXWPFDocument doc, Map<String, Object> params) {
        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
        XWPFParagraph para;
        while (iterator.hasNext()) {
            para = iterator.next();
            this.replaceInPara(para, params, doc);
        }
    }

    /**
     * 替换段落里面的变量
     *
     * @param para 要替换的段落
     * @param params 参数
     */
    private void replaceInPara(XWPFParagraph para, Map<String, Object> params, CustomXWPFDocument doc) {
        List<XWPFRun> runs;
        Matcher matcher;
        if (this.matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();
            int start = -1;
            int end = -1;
            String str = "";
            /*for (int i = 0; i < runs.size(); i++) {
                XWPFRun run = runs.get(i);
                String runText = run.toString();
                System.out.println(runText);
                System.out.println("======");
                if ('$' == runText.charAt(0) && '{' == runText.charAt(1)) {
                    start = i;
                }
                if ((start != -1)) {
                    str += runText;
                }
                if ('}' == runText.charAt(runText.length() - 1)) {
                    if (start != -1) {
                        end = i;
                        break;
                    }
                }
            }

            for (int i = start; i <= end; i++) {
                para.removeRun(i);
                i--;
                end--;
            }*/

            for (Map.Entry<String, Object> entry : params.entrySet()) {
                System.out.println(para.getText());
                String key = entry.getKey();
                if (para.getText().indexOf(key) != -1) {
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        str = str.replace(key, value.toString());
                        System.out.println(str + "----------");
                        para.createRun().setText(str, 0);
                        break;
                    } else if (value instanceof Map) {
                        str = str.replace(key, "");
                        Map pic = (Map) value;
                        int width = Integer.parseInt(pic.get("width").toString());
                        int height = Integer.parseInt(pic.get("height").toString());
                        int picType = getPictureType(pic.get("type").toString());
                        byte[] byteArray = (byte[]) pic.get("content");
                        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteArray);
                        try {
                            //int ind = doc.addPicture(byteInputStream,picType);
                            //doc.createPicture(ind, width , height,para);
                            doc.addPictureData(byteInputStream, picType);
                            doc.createPicture(doc.getAllPictures().size() - 1, width, height, para);
                            para.createRun().setText(str, 0);
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    /**
     * 为表格插入数据，行数不够添加新行
     *
     * @param table 需要插入数据的表格
     * @param tableList 插入数据集合
     */
    private static void insertTable(XWPFTable table, List<String[]> tableList) {
        //创建行,根据需要插入的数据添加新行，不处理表头
        if (tableList == null) {
            return;
        }
        for (int i = 0; i < tableList.size(); i++) {
            XWPFTableRow row = table.createRow();
        }
        //遍历表格插入数据
        List<XWPFTableRow> rows = table.getRows();
        int length = table.getRows().size();
        for (int i = 1; i < length - 1; i++) {
            XWPFTableRow newRow = table.getRow(i);
            List<XWPFTableCell> cells = newRow.getTableCells();
            for (int j = 0; j < cells.size(); j++) {
                XWPFTableCell cell = cells.get(j);
                String s = tableList.get(i - 1)[j];
                cell.setText(s);
            }
        }
    }

    /**
     * 替换表格里面的变量
     *
     * @param doc 要替换的文档
     * @param params 参数
     */
    private void replaceInTable(CustomXWPFDocument doc, Map<String, Object> params, List<String[]> tableList) {
        Iterator<XWPFTable> iterator = doc.getTablesIterator();
        XWPFTable table;
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        List<XWPFParagraph> paras;
        while (iterator.hasNext()) {
            table = iterator.next();
            if (table.getRows().size() > 1) {
                //判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
                if (this.matcher(table.getText()).find()) {
                    rows = table.getRows();
                    for (XWPFTableRow row : rows) {
                        cells = row.getTableCells();
                        for (XWPFTableCell cell : cells) {
                            paras = cell.getParagraphs();
                            for (XWPFParagraph para : paras) {
                                this.replaceInPara(para, params, doc);
                            }
                        }
                    }
                } else {
                    insertTable(table, tableList);  //插入数据
                }
            }
        }
    }


    /**
     * 正则匹配字符串
     */
    private Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }


    /**
     * 根据图片类型，取得对应的图片类型代码
     *
     * @return int
     */
    private static int getPictureType(String picType) {
        int res = CustomXWPFDocument.PICTURE_TYPE_PICT;
        if (picType != null) {
            if (picType.equalsIgnoreCase("png")) {
                res = CustomXWPFDocument.PICTURE_TYPE_PNG;
            } else if (picType.equalsIgnoreCase("dib")) {
                res = CustomXWPFDocument.PICTURE_TYPE_DIB;
            } else if (picType.equalsIgnoreCase("emf")) {
                res = CustomXWPFDocument.PICTURE_TYPE_EMF;
            } else if (picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")) {
                res = CustomXWPFDocument.PICTURE_TYPE_JPEG;
            } else if (picType.equalsIgnoreCase("wmf")) {
                res = CustomXWPFDocument.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }

    /**
     * 将输入流中的数据写入字节数组
     */
    public static byte[] inputStream2ByteArray(InputStream in, boolean isClose) {
        byte[] byteArray = null;
        try {
            int total = in.available();
            byteArray = new byte[total];
            in.read(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isClose) {
                try {
                    in.close();
                } catch (Exception e2) {
                    e2.getStackTrace();
                }
            }
        }
        return byteArray;
    }

    public static void replaceText(InputStream inputStream, OutputStream outputStream, Map<String, String> map) {
        try {
            XWPFDocument document;//= new XWPFDocument(POIXMLDocument.openPackage(srcPath));
            document = new XWPFDocument(inputStream);
            //1. 替换段落中的指定文字
            Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
            Set<String> set;
            XWPFParagraph paragraph;
            while (itPara.hasNext()) {
                paragraph = itPara.next();
                List<XWPFRun> runs = paragraph.getRuns();
                for (int i = 0; i < runs.size(); i++) {
                    String text0 = runs.get(i).getText(runs.get(i).getTextPosition());
                    if (text0 != null && text0.contains("$")) {
                        int startIndex = text0.lastIndexOf("$");
                        int endIndex = 1;
                        if (startIndex != -1) {
                            endIndex = text0.substring(startIndex).indexOf("}");
                        }
                        if (endIndex < 0) {
                            // 记录分隔符中间跨越的runs数量，用于字符串拼接和替换
                            int num = 0;
                            int j = i + 1;
                            for (; j < runs.size(); j++) {
                                String text1 = runs.get(j).getText(runs.get(j).getTextPosition());
                                if (text1 != null && text1.contains("}")) {
                                    num = j - i;
                                    break;
                                }
                            }
                            if (num != 0) {
                                // num!=0说明找到了@@配对，需要替换
                                StringBuilder newText = new StringBuilder();
                                for (int s = i; s <= i + num; s++) {
                                    String text2 = runs.get(s).getText(runs.get(s).getTextPosition());
                                    String replaceText = text2;
                                    if (s == i && text2.contains("$") && text2.contains("}")) {
                                        newText.append(text2);
                                    } else if (s == i && text2.contains("$")) {
                                        replaceText = text2.substring(0, text2.indexOf("$"));
                                        newText.append(text2.substring(text2.indexOf("$")));
                                    } else if (text2.contains("}")) {
                                        replaceText = text2.substring(replaceText.indexOf("}") + 1);
                                        newText.append(text2.substring(0, text2.indexOf("}") + 1));
                                    } else {
                                        replaceText = "";
                                        newText.append(text2);
                                    }
                                    runs.get(s).setText(replaceText, 0);
                                }
                                runs.get(i).setText(newText.toString(), 0);
                                i = i - 1;
                            }
                        }
                    }
                }
                for (int j = 0; j < runs.size(); j++) {
                    String text = runs.get(j).getText(runs.get(j).getTextPosition());
                    boolean isSetText = false;
                    set = map.keySet();
                    Iterator<String> iterator = set.iterator();
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        if (text != null) {
                            if (text.indexOf(key) >= 0) {
                                String value = map.get(key);
                                text = text.replace(key, value);
                                runs.get(j).setText(text, 0);
                            }
                        }
                    }
                }
            }
            //2. 替换表格中的指定文字
            Iterator<XWPFTable> itTable = document.getTablesIterator();
            XWPFTable table;
            int rowsCount;
            while (itTable.hasNext()) {
                table = itTable.next();
                rowsCount = table.getNumberOfRows();
                for (int i = 0; i < rowsCount; i++) {
                    XWPFTableRow row = table.getRow(i);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        for (Map.Entry<String, String> e : map.entrySet()) {
                            if (cell.getText().equals(e.getKey())) {
                                cell.removeParagraph(0);
                                cell.setText(e.getValue());
                            }
                        }
                    }
                }
            }
            //3.输出流
            document.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}