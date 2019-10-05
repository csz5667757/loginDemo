package com.csz.mianshi;

public class Student {
    private String s;
    private Integer i;
    private String name="ss";

    public Student() {
        System.out.println("创建了一个Student实例");
    }

    public Student(String str){
        System.out.println("创建了一个Student有参实例");
        this.s=str;
        System.out.println(s);
    }

    public Student(String str,Integer i){
        System.out.println("创建了一个Student多参实例");
        this.s=str;
        this.i=i;
        System.out.println(s+"  "+i);

    }

    // 无参数方法
    public void setName1 (){
        System.out.println("调用了无参方法：setName1（）");
    }

    // 有参数方法
    public void setName2 (String str){
        System.out.println("调用了有参方法setName2（String str）:" + str);
    }

    public String getName() {
        return name;
    }
}