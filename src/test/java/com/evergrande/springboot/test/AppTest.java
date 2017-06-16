package com.evergrande.springboot.test;

import io.swagger.models.Model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017/6/2
 * @Time: 18:33
 * @Description:
 */
public class AppTest {

    private String base = "{field: 'new_trans_nums', title: '交易人数', width: 80, sortable: true},";

    public static void main(String[] args) throws Exception {

        Map<String, Model> map = new HashMap<>();

//        File file = new File("E:\\Users\\IdeaProjects\\mp-spring-boot\\src\\test\\java\\com\\evergrande\\springboot\\test\\mobile_review.txt");
        File file = new File("E:\\Users\\IdeaProjects\\mp-spring-boot\\src\\test\\java\\com\\evergrande\\springboot\\test\\final_review.txt");

        if (file.isFile() && file.exists()) { //判断文件是否存在

            InputStreamReader read = new InputStreamReader(new FileInputStream(file));

            BufferedReader bufferedReader = new BufferedReader(read);

            String lineTxt = null;

            while ((lineTxt = bufferedReader.readLine()) != null) {
//                System.out.println(lineTxt);
                String[] strs = lineTxt.split(",");
                String s1 = strs[0];
                String s2 = strs[1];

                String field = "{field: '"+s2+"', title: '"+s1+"', width: 80, sortable: true},";

                System.err.println(field);
            }

//            while ((lineTxt = bufferedReader.readLine()) != null) {
//                String[] strs = lineTxt.split(",");
//                String s1 = strs[0];
//                String s2 = strs[1];
//
//                String field = "{field: '"+s2+"', title: '"+s1+"', width: 80, sortable: true},";
//
//                System.err.println(field);
//            }


        }
    }


}




