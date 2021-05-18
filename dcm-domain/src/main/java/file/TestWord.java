package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : yaoximing
 * @date : 2021-04-21
 * @description :
 **/
public class TestWord {


    public static void main(String[] args) throws Exception {
        WordUtils wordUtils = new WordUtils();
        Map<String, String> params = new HashMap<>();
        params.put("${agreementManName}", "张三a");
        params.put("${agreementManCardNo}", "3123打131231");
        params.put("${account}", "12988832189");
        params.put("${amount}", "190");
        params.put("${monthNum}", "331");
        params.put("${amountContent}", "金额");
        params.put("${carModel}", "V5");
        params.put("${carNo}", "沪Adsa");
        params.put("${carBrand}", "宝马");
        params.put("${carSn}","dsadasd");
        params.put("${carCount}","1");
        String path = "D:/hire_agree_file.docx";
        //wordUtils.getWord(path,params,null,"");
        File file = new File(path);
        InputStream is = new FileInputStream(file);
        File file1 = new File("D:/1.docx");
        WordUtils.replaceText(is, new FileOutputStream(file1), params);
    }


}
