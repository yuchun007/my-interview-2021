package com.yc.interview.stringconstant;

public class StringConstant {

    public static void main(String[] args) {
        String strtest = new StringBuilder("58").append("tongcheng").toString();
        System.out.println(strtest);
        System.out.println(strtest.intern());
        System.out.println(strtest == strtest.intern());

        System.out.println("===================================");

        String strtest1 = new StringBuilder("ja").append("va").toString();
        System.out.println(strtest1);
        //jdk自带常量池中有“java”常量，在加载sun.misc.Version这个类型的时候进入常量池
        System.out.println(strtest1.intern());
        System.out.println(strtest1 == strtest1.intern());

        System.out.println("===================================");

        String str1 = "a";
        String str2 = "b";
        String str3 = "ab";
        String str4 = str1 + str2;
        String str5 = new String("ab");

        System.out.println(str5.equals(str3));
        System.out.println(str5 == str3);
        System.out.println(str5.intern() == str3);
        System.out.println(str5.intern() == str4);

        System.out.println("==================================");

        //采用new 创建的字符串对象不进入字符串池
        String a = new String("ab");
        String b = new String("ab");
        String c = "ab";
        String d = "a" + "b";
        String e = "b";
        String f = "a" + e;

        System.out.println(b.intern() == a);
        System.out.println(b.intern() == c);
        System.out.println(b.intern() == d);
        System.out.println(b.intern() == f);
        System.out.println(b.intern() == a.intern());

        System.out.println("==================================");

        String a1 = "abc";
        String b1 = "abc";
        String c1 = "a" + "b" + "c";
        String d1 = "a" + "bc";
        String e1 = "ab" + "c";

        System.out.println(a1 == b1);
        System.out.println(a1 == c1);
        System.out.println(a1 == d1);
        System.out.println(a1 == e1);
        System.out.println(c1 == d1);
        System.out.println(c1 == e1);
        //===================================================================================
        /**
         * 备注:
         * 参考网址：https://www.runoob.com/java/java-string-intern.html
         * intern() 方法返回字符串对象的规范化表示形式。
         * 它遵循以下规则：对于任意两个字符串 s 和 t，当且仅当 s.equals(t) 为 true 时，s.intern() == t.intern() 才为 true。
         *  这个方法会首先检查字符串池中是否有”ab”这个字符串，如果存在则返回这个字符串的引用，否则就将这个字符串添加到字符串池中，然会返回这个字符串的引用。
         */
    }
}
