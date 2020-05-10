package com.wangwei.cloud.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.lang.reflect.Field;
import java.util.List;

public class MyQueryWrapper<T> extends QueryWrapper {
    public void allEq2(T queryParam, PageRequest pageRequest) throws IllegalAccessException {
        Class userCla = (Class) queryParam.getClass();
        Field[] fs = userCla.getDeclaredFields();
        for(int i = 0;i < fs.length;i++)
        {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = f.get(queryParam);// 得到此属性的值
            System.out.println("name:" + f.getName() + "\t value = " + val);
            String type = f.getType().toString();
            if(f.getName()!="serialVersionUID"&&val!=null&&val!="") {
                if(type.endsWith("String")) {
                    this.like(this.camelToUnderline(f.getName()),val);
                }else if(type.endsWith("int")){
                    this.eq(this.camelToUnderline(f.getName()),val);
            }
            }
        }
        List<Sort> list=pageRequest.getSorts();
         if(pageRequest.getOrder()!=null&&!"".equals(pageRequest.getOrder())) {
           // for (Sort sort : list) {
                if ("desc".equals(pageRequest.getDirection())) {
                    this.orderByDesc(this.camelToUnderline(pageRequest.getOrder()));
                } else {
                    this.orderByAsc(this.camelToUnderline(pageRequest.getOrder()));
         //       }
            }
        }

    }
    public static String camelToUnderline(String str) {
        if (str == null || str.trim().isEmpty()){
            return "";
        }
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        sb.append(str.substring(0, 1).toLowerCase());
        for (int i = 1; i < len; i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
