package cn.sevenyuan.demo.servlet;

import javax.servlet.annotation.WebListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @author JingQ at 2019-08-06
 */
@WebListener
public class MyWebListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("MyWebListener>>>requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("MyWebListener>>>initialized");
    }
}
