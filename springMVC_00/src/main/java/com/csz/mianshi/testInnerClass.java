package com.csz.mianshi;

public class testInnerClass {
    int outer;
    public testInnerClass(int outer){
        this.outer=outer;
    }

    void outerMethod(){
        System.out.println("我是外部类方法");
    }

    public  class innerClass{
        private int i;
        void pirntinner(){
            System.out.println(i);
        }
        void printouter(){
            System.out.println(outer);
        }

        public innerClass(int i){
            this.i=i;
        }
    }

    public static void main(String[] args) {
        testInnerClass t =new testInnerClass(10);
        testInnerClass.innerClass ic = t.new innerClass(100);
        t.outerMethod();
        ic.printouter();
        ic.pirntinner();
    }
}
