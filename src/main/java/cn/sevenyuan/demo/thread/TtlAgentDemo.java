package cn.sevenyuan.demo.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author JingQ at 7/24/20
 */
public class TtlAgentDemo {

    public static void main(String[] args) {
        test1();
    }


    private static void test1() {
        TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();
        threadLocal.set("wahaha");

        ExecutorService executors = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 2; i++) {
            executors.submit(TtlRunnable.get(() -> {
                System.out.println(threadLocal.get());
            }));
        }

        threadLocal.set("new value ice cream");

        for (int i = 0; i < 2; i++) {
            executors.submit(TtlRunnable.get(() -> {
                System.out.println(threadLocal.get());
            }));
        }
    }
}
