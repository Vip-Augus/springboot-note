package cn.sevenyuan.demo.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author JingQ at 2019-10-31
 */
public class ExceptionUtil {

    public  ModelAndView handleException() throws Exception {
        throw new Exception("服务器正忙");
    }


    public static void main(String[] args) throws Exception {
        String baseStr = "%";
        /* 读入TXT文件 */
        String pathname = "/Users/yejingqi/Downloads/1.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        File filename = new File(pathname); // 要读取以上路径的input。txt文件
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line = "";
        line = br.readLine();
        List<Integer> accountSetIdList = Lists.newArrayList();
        while (line != null) {
            line = br.readLine(); // 一次读入一行数据
            if (StringUtils.isEmpty(line)) {
                break;
            }
            accountSetIdList.add(Integer.valueOf(line));
        }
        System.out.println(accountSetIdList.size());

        /* 写入Txt文件 */
        File writename = new File("/Users/yejingqi/Downloads/Fix_3.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
        writename.createNewFile(); // 创建新文件
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));

        List<List<Integer>> partitions = Lists.partition(accountSetIdList, 300);

        int index = 0;
        for (List<Integer> partition : partitions) {
            Joiner joiner = Joiner.on("\",\"");
            String temp = joiner.join(partition);
            out.write(String.format(baseStr, temp) + "\r\n");
            index++;
            if (index % 30 == 0) {
                out.write("\r\n");
            }
            // \r\n即为换行
        }

        out.flush(); // 把缓存区内容压入文件
        out.close(); // 最后记得关闭文件

    }
}
