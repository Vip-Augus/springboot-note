package cn.sevenyuan.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * springboot security 配置
 * @author JingQ at 2019-08-17
 */
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        // 不进行加密
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用硬编码方式，在内存中设定权限管理
        auth.inMemoryAuthentication()
                .withUser("admin").password("123456").roles("ADMIN", "USER")
                .and()
                .withUser("JingQi").password("123456").roles("USER");
    }
}
