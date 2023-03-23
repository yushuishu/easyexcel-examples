package com.shuishu.demo.easyexcel.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

/**
 * @author ：shuishu
 * @date   ：2022/12/22 10:03
 * @IDE    ：IntelliJ IDEA
 * @Motto  ：ABC(Always Be Coding)
 * <p></p>
 * @Description -
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @ExcelProperty("id")
    private Long userId;
    @ExcelProperty("姓名")
    private String userName;
    @ExcelProperty("年龄")
    private Integer userAge;
    @ExcelProperty("性别")
    private String userSex;

}
