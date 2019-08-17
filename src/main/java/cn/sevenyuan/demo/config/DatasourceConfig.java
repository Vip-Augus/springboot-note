package cn.sevenyuan.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 数据库参数配置
 * @author JingQ at 2019-08-08
 */
@Configuration
public class DatasourceConfig {

    @Autowired
    private Environment env;

    @Autowired
    private DruidConfigBean druidConfigBean;

    @Bean
    public DruidDataSource druidDataSource() {
        // 加个注释，实际上 springboot 都已经处理好了 =-=
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
        dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setInitialSize(druidConfigBean.getInitialSize());//初始化时建立物理连接的个数
        dataSource.setMaxActive(druidConfigBean.getMaxActive());//最大连接池数量
        dataSource.setMinIdle(druidConfigBean.getMinIdle());//最小连接池数量
        dataSource.setMaxWait(druidConfigBean.getMaxWait());//获取连接时最大等待时间，单位毫秒。
        dataSource.setValidationQuery(druidConfigBean.getValidationQuery());//用来检测连接是否有效的sql
        dataSource.setTestOnBorrow(druidConfigBean.getTestOnBorrow());//申请连接时执行validationQuery检测连接是否有效
        dataSource.setTestWhileIdle(druidConfigBean.getTestWhileIdle());//建议配置为true，不影响性能，并且保证安全性。
        dataSource.setPoolPreparedStatements(druidConfigBean.getPoolPreparedStatements());//是否缓存preparedStatement，也就是PSCache
        return dataSource;
    }
}
