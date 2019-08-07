package cn.sevenyuan.demo.command;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 带有优先级的命令行启动器 2
 * @author JingQ at 2019-08-06
 */
@Component
@Order(2)
public class MyCommandLineRunner2 implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Runner2>>>" + Arrays.toString(args));
    }
}
