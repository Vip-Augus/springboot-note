package cn.sevenyuan.demo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户 domain
 * @author JingQ at 2019-08-17
 */
@Data
public class User implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

    private String address;
}
