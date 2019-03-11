package com.java;

public class test {
    public static void  test1(){
        int[] a = {1,2,3,4};
        int[] b = new int[5];
        System.arraycopy(a,1,b,3,2);
        for(int x :b){
            System.out.print(x);
        }
    }
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));
        System.setProperty("test","vale");
        System.out.println(System.getProperty("test"));

//        test1();
//        System.out.println(System.getProperty("java.class.path").toLowerCase().contains("rt"));
    }
}
