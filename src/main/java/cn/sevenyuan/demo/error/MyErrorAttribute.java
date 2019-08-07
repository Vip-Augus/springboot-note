package cn.sevenyuan.demo.error;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义错误属性
 * @author JingQ at 2019-08-05
 */
@Component
public class MyErrorAttribute extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        // 可以在这里添加自定义的错误信息，然后在 view 中取出来
        errorAttributes.put("customMessage", "出错啦");
        return errorAttributes;
    }
}
