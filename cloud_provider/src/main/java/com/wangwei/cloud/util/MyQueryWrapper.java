package com.wangwei.cloud.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wangwei.cloud.interceptor.UserThreadLocal;
import com.wangwei.cloud.sys.entity.Role;
import com.wangwei.cloud.sys.entity.User;

import java.lang.reflect.Field;
import java.util.List;

public class MyQueryWrapper<T> extends QueryWrapper {
    public MyQueryWrapper<T> allEq2(T queryParam, PageRequest pageRequest) throws IllegalAccessException {
        Class userCla = (Class) queryParam.getClass();
        Field[] fs = userCla.getDeclaredFields();
        for(int i = 0;i < fs.length;i++)
        {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = f.get(queryParam);// 得到此属性的值
            System.out.println("name:" + f.getName() + "\t value = " + val);
            String type = f.getType().toString();

            if(f.getName()!="serialVersionUID"&&val!=null&&!"".equals(val)) {

                if(type.endsWith("String")) {
                    this.like(this.camelToUnderline(f.getName()),val);
                }else if(type.endsWith("int")||type.endsWith("Int")){
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
        return this;
    }
    public MyQueryWrapper<T> allEq2(T queryParam) throws IllegalAccessException {
        Class userCla2 = (Class) queryParam.getClass();
        Field[] fs = userCla2.getDeclaredFields();
        for(int i = 0;i < fs.length;i++)
        {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = f.get(queryParam);// 得到此属性的值
            System.out.println("name:" + f.getName() + "\t value = " + val);
            String type = f.getType().toString();
            System.out.println(type);
            if(f.getName()!="serialVersionUID"&&val!=null&&val!="") {
                if(type.endsWith("String")) {
                    this.like(this.camelToUnderline(f.getName()),val);
                }else if(type.endsWith("int")||type.endsWith("Integer")){
                    this.eq(this.camelToUnderline(f.getName()),val);
                }else if( type.endsWith("Long")){
                    this.eq(this.camelToUnderline(f.getName()),val);
                }
            }
        }

        return this;

    }
    public void setAuthority(){
        User user=UserThreadLocal.get();
        Role role=user.getDefaultRole();
         if("company".equals(role.getAuthorityCode())){
            this.eq("company_id",role.getCompanyId());
        }else if("unit".equals(role.getAuthorityCode())){
             this.eq("unit_id",role.getUnitId());
        }else if("owner".equals(role.getAuthorityCode())){
             this.eq("created_by",user.getUserId());
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
