package com.shuishu.demo.easyexcel.service;


import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：shuishu
 * @date   ：2022/12/22 10:27
 * @IDE    ：IntelliJ IDEA
 * @Motto  ：ABC(Always Be Coding)
 * <p></p>
 * @Description -
 */
public interface ReadService {
    /**
     * 简单读取，读取所有Sheet
     * @param excel -
     */
    void simpleRead(MultipartFile excel);

    /**
     * 简单读取，指定Sheet名称
     * @param excel -
     */
    void simpleRead2(MultipartFile excel);

    /**
     * 简单读取，指定多个Sheet名称
     * @param excel -
     */
    void simpleRead3(MultipartFile excel);

    /**
     * 日期、数字或者自定义格式转换
     * @param excel -
     */
    void simpleRead4(MultipartFile excel);

    /**
     * 额外信息（批注、超链接、合并单元格信息读取）
     * 由于是流式读取，没法在读取到单元格数据的时候直接读取到额外信息，所以只能最后通知哪些单元格有哪些额外信息
     * @param excel -
     */
    void simpleRead5(MultipartFile excel);
}
