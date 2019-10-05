package com.csz.mianshi;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {
    public static void main(String[] args) {
        /*
         *  通过反射调用方法
         **/
//        Class studentClass = null;
//        // 1. 获取Student类的Class对象
//        studentClass = Student.class;
//
//        Object mStudent  = null;
//        try {
//            // 2. 通过Class对象创建Student类的对象
//            mStudent = studentClass.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        // 3.1 通过Class对象获取方法setName1（）的Method对象:需传入方法名
//        Method msetName1 = null;//obj无须转换类型
//        try {
//            msetName1 = studentClass.getMethod("setName1");
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//            //  通过Method对象调用setName1（）：需传入创建的实例
//            msetName1.invoke(mStudent);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//
//        // 3.2 通过Class对象获取方法setName2（）的Method对象:需传入方法名 & 参数类型
//        Method msetName2 = null;//obj无须转换类型
//        try {
//            msetName2 = studentClass.getMethod("setName2",String.class);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            // 通过Method对象调用setName2（）：需传入创建的实例 & 参数值
//            msetName2.invoke(mStudent,"Carson_Ho");
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

        /*
         *  通过反射调用构造方法
         **/

//        Class studentClass = null;
//        // 1. 获取Student类的Class对象
//        studentClass = Student.class;
//
//        try {
//            // 2. 通过Class对象获取Constructor类对象，从而调用无参构造方法
//            Object cObj = studentClass.getConstructor().newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//
//
//        Object caobj = null;//调用含参的构造方法.
//        try {
//            caobj=studentClass.getConstructor(String.class,Integer.class).newInstance("Carson",5);
//            caobj = studentClass.getConstructor(String.class).newInstance("Carson");
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }


        /*
         *通过反射获取属性 & 赋值
         **/
            // 1. 获取Student类的Class对象
        Class studentClass = Student.class;
        Object mStudent  = null;
        try {
            // 2. 通过Class对象创建Student类的对象
            mStudent = studentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field f = null;
        try {
            // 3. 通过Class对象获取Student类的name属性
            f = studentClass.getDeclaredField("name");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        assert f != null;
        // 4. 设置私有访问权限
        f.setAccessible(true);
        try {
            // 5. 对新创建的Student对象设置name值
            f.set(mStudent, "Carson_Ho");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            // 6. 获取新创建Student对象的的name属性
            System.out.println(f.get(mStudent));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
