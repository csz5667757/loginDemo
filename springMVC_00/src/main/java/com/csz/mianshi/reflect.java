package com.csz.mianshi;

public class reflect {
    public static void main(String[] args) throws ClassNotFoundException {
        Class c1 = "Carson".getClass();
        Class c2 = Class.forName("java.lang.String");
        Class<?> classType = Byte.TYPE;
        System.out.println(classType);
        System.out.println(c1==c2);
    }
}
