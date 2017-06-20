package com.evergrande.springboot.test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/2
 * @Time: 18:33
 * @Description:
 */
public class AppTest {

//    File file = new File("E:\\Users\\IdeaProjects\\mp-spring-boot\\" +
//            "src\\test\\java\\com\\evergrande\\springboot\\test\\homepage.txt");

    File file = new File("E:\\Workspace\\intellij2017\\mp-spring-boot\\src" +
            "\\test\\java\\com\\evergrande\\springboot\\test\\contract_sign.txt");

    @Test
    public void testHtml() throws Exception {

        if (file.isFile() && file.exists()) { //判断文件是否存在

            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;

            while ((lineTxt = bufferedReader.readLine()) != null) {
                String[] strs = lineTxt.split(",");
                String s1 = strs[0];
                String s2 = strs[1];

                String field = "{field: '" + s2 + "', title: '" + s1 + "', width: 80, sortable: true},";
                System.err.println(field);
            }

        }

    }

    @Test
    public void testField() throws Exception {

        if (file.isFile() && file.exists()) { //判断文件是否存在

            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;

            while ((lineTxt = bufferedReader.readLine()) != null) {
                String[] strs = lineTxt.split(",");
                String s1 = strs[0];
                String s2 = strs[1];

                System.err.println("// " + s1);
                System.err.println("private String " + s2 + ";");
            }

        }

    }


}





