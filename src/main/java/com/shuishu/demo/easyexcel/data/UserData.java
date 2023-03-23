package com.shuishu.demo.easyexcel.data;


import com.shuishu.demo.easyexcel.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：shuishu
 * @date   ：2022/12/22 10:06
 * @IDE    ：IntelliJ IDEA
 * @Motto  ：ABC(Always Be Coding)
 * <p></p>
 * @Description -
 */
public class UserData {

    private List<User> getUserList(){
        List<User> list = new ArrayList<>();
        list.add(new User(1001L, "刘备", 30, "男"));
        list.add(new User(1002L, "关羽", 25, "男"));
        list.add(new User(1003L, "张飞", 20, "男"));
        list.add(new User(1004L, "孙尚香", 15, "女"));
        list.add(new User(1005L, "貂蝉", 15, "女"));
        list.add(new User(1006L, "曹操", 35, "男"));
        list.add(new User(1007L, "袁绍", 33, "男"));
        list.add(new User(1008L, "诸葛亮", 27, "男"));
        list.add(new User(1009L, "周瑜", 20, "男"));
        list.add(new User(1010L, "小乔", 18, "女"));
        return list;
    }
}
