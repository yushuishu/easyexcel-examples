package com.shuishu.demo.easyexcel.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.read.metadata.holder.ReadSheetHolder;
import com.alibaba.fastjson.JSON;
import com.shuishu.demo.easyexcel.entity.ConverterData;
import com.shuishu.demo.easyexcel.entity.DemoExtraData;
import com.shuishu.demo.easyexcel.entity.User;
import com.shuishu.demo.easyexcel.service.ReadService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：shuishu
 * @date   ：2022/12/22 10:27
 * @IDE    ：IntelliJ IDEA
 * @Motto  ：ABC(Always Be Coding)
 * <p></p>
 * @Description -
 */
@Service
public class ReadServiceImpl implements ReadService {


    @Override
    public void simpleRead(MultipartFile excel) {
        List<User> userList = new ArrayList<>();
        try {
            EasyExcel.read(excel.getInputStream(), User.class, new ReadListener<User>() {
                @Override
                public void invoke(User user, AnalysisContext context) {
                    System.out.println("-------------->>> invoke方法开始读取");
                    userList.add(user);

                    /*
                     *
                     * context 应用，获取一些值
                     *
                     */
                    //
                    System.out.println("用户属性：" + context.getCustom());
                    System.out.println("当前指令：" + context.currentReadHolder());
                    System.out.println("事件处理器：" + context.analysisEventProcessor());

                    ReadSheetHolder readSheetHolder = context.readSheetHolder();
                    System.out.println("sheet名称：" + readSheetHolder.getSheetName());
                    System.out.println("sheet序号：" + readSheetHolder.getSheetNo());
                    System.out.println("当前sheet：" + readSheetHolder.getReadSheet());
                    System.out.println("读监听器：" + readSheetHolder.readListenerList());
                    System.out.println("获取总行数，数据可能不准确：" + readSheetHolder.getApproximateTotalRowNumber());
                    System.out.println("读取纸头中最大纸头的尺寸数据：" + readSheetHolder.getMaxDataHeadSize());
                    System.out.println("当前读取的行索引：" + readSheetHolder.getRowIndex());
                    /*
                     * Count the number of added heads when read sheet.
                     * （当读表时，计算增加的头数。）
                     *
                     * <p>
                     * 0 - This Sheet has no head ,since the first row are the data
                     * （这个Sheet没有标题，因为第一行是数据 ）
                     * <p>
                     * 1 - This Sheet has one row head , this is the default
                     * （此工作表有一个行头，这是默认值 ）
                     * <p>
                     * 2 - This Sheet has two row head ,since the third row is the data
                     * （这个Sheet有两个行头，因为第三行是数据 ）
                     */
                    System.out.println("计算增加的头数：" + readSheetHolder.getHeadRowNumber());

                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    System.out.println("-------------->>> invoke方法读取完毕，执行doAfterAllAnalysed方法");
                }
            }).doReadAll();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("-------------->>> 所有数据读取完毕，打印Excel数据");
        userList.forEach(System.out::println);
    }

    @Override
    public void simpleRead2(MultipartFile excel) {
        List<User> userList = new ArrayList<>();
        try {
            EasyExcel.read(excel.getInputStream(), User.class, new ReadListener<User>() {
                @Override
                public void invoke(User user, AnalysisContext context) {
                    System.out.println("-------------->>> invoke方法开始读取");
                    System.out.println("当前读取的行索引：" + context.readSheetHolder().getRowIndex());
                    System.out.println("读取当前行的数据：" + user);
                    userList.add(user);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    System.out.println("-------------->>> invoke方法读取完毕，执行doAfterAllAnalysed方法");
                }
            }).sheet("一班").doRead();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("-------------->>> 所有数据读取完毕，打印Excel数据");
        userList.forEach(System.out::println);
    }

    @Override
    public void simpleRead3(MultipartFile excel) {
        List<User> userList = new ArrayList<>();
        try {
            ExcelReader build = EasyExcel.read(excel.getInputStream()).build();

            ReadSheet readSheet1 = EasyExcel.readSheet("一班").head(User.class).registerReadListener(new ReadListener<User>() {
                @Override
                public void invoke(User user, AnalysisContext context) {
                    System.out.println("-------------->>> invoke方法开始读取 一班");
                    userList.add(user);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    System.out.println("-------------->>> invoke方法读取一班完毕，执行doAfterAllAnalysed方法");
                }
            }).build();

            ReadSheet readSheet2 = EasyExcel.readSheet("二班").head(User.class).registerReadListener(new ReadListener<User>() {
                @Override
                public void invoke(User user, AnalysisContext context) {
                    System.out.println("-------------->>> invoke方法开始读取 二班");
                    userList.add(user);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    System.out.println("-------------->>> invoke方法读取二班完毕，执行doAfterAllAnalysed方法");
                }
            }).build();

            // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
            build.read(readSheet1, readSheet2);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("-------------->>> 所有数据读取完毕，打印Excel数据");
        userList.forEach(System.out::println);
    }

    @Override
    public void simpleRead4(MultipartFile excel) {
        List<ConverterData> converterDataList = new ArrayList<>();
        try {
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet
            EasyExcel.read(excel.getInputStream(), ConverterData.class, new ReadListener<ConverterData>() {
                @Override
                public void invoke(ConverterData converterData, AnalysisContext context) {
                    System.out.println("-------------->>> invoke方法开始读取第一个sheet");
                    converterDataList.add(converterData);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    System.out.println("-------------->>> invoke方法读取第一个sheet完毕，执行doAfterAllAnalysed方法");
                }
            }).sheet().doRead();
            // 这里注意 我们也可以registerConverter来指定自定义转换器， 但是这个转换变成全局了， 所有java为string,excel为string的都会用这个转换器。
            // 如果就想单个字段使用请使用@ExcelProperty 指定converter
            // .registerConverter(new CustomStringStringConverter()).sheet().doRead()
            // 读取sheet
        }catch (Exception e){
            e.printStackTrace();
        }

        converterDataList.forEach(System.out::println);
    }

    @Override
    public void simpleRead5(MultipartFile excel) {
        try {
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet
            EasyExcel.read(excel.getInputStream(), DemoExtraData.class, new ReadListener<DemoExtraData>() {
                @Override
                public void invoke(DemoExtraData demoExtraData, AnalysisContext context) {
                }
                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                }
                @Override
                public void extra(CellExtra extra, AnalysisContext context) {
                    System.out.println(String.format("读取到了一条额外信息: %s", JSON.toJSONString(extra)));
                    switch (extra.getType()) {
                        case COMMENT:
                            System.out.println(String.format("额外信息是批注,在rowIndex: %s,columnIndex: %s,内容是: %s", extra.getRowIndex(), extra.getColumnIndex(), extra.getText()));
                            break;
                        case HYPERLINK:
                            System.out.println(String.format(
                                    "额外信息是超链接,而且覆盖了一个区间,在firstRowIndex:%s,firstColumnIndex:%s,lastRowIndex:%s,lastColumnIndex:%s, 内容是:%s",
                                    extra.getFirstRowIndex(), extra.getFirstColumnIndex(), extra.getLastRowIndex(), extra.getLastColumnIndex(), extra.getText()
                            ));
                            break;
                        case MERGE:
                            System.out.println(String.format(
                                    "额外信息是合并单元格,而且覆盖了一个区间,在firstRowIndex:%s,firstColumnIndex:%s,lastRowIndex:%s,lastColumnIndex:%s, 内容是:%s",
                                    extra.getFirstRowIndex(), extra.getFirstColumnIndex(), extra.getLastRowIndex(), extra.getLastColumnIndex(), extra.getText()
                            ));
                            break;
                        default:
                    }
                    System.out.println();
                }
            })
                    // 需要读取批注 默认不读取
                    .extraRead(CellExtraTypeEnum.COMMENT)
                    // 需要读取超链接 默认不读取
                    .extraRead(CellExtraTypeEnum.HYPERLINK)
                    // 需要读取合并单元格信息 默认不读取
                    .extraRead(CellExtraTypeEnum.MERGE).sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
