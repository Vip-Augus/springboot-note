package cn.sevenyuan.demo.command;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 应用启动时进行执行
 * 带有优先级的命令行启动器 1
 * @author JingQ at 2019-08-06
 */
@Component
@Order(1)
public class MyCommandLineRunner1 implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Runner1>>>" + Arrays.toString(args));
    }
}
