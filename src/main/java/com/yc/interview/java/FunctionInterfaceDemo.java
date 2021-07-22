package com.yc.interview.java;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 四大函数式接口
 *    名称                        参数类型         返回类型            用途
 * 1.函数型接口  Function<T,R>         T               R            有参数和返回值，R apply（T t）
 * 2.断定型接口  Predicate<T>         T               boolean      确定参数为T的对象是否满足约束，并返回boolean值，boolean test（T t）
 * 3.供给型接口  Supplier<T>          无              T            返回类型为T的对象，T get（）
 * 4.消费型接口  Consumer<T>          T               void         对类型为T的对象进行操作，void accept（T t）
 */
public class FunctionInterfaceDemo {
    public static void main(String[] args) {
        //1.function
        //Function<String,Integer> function = s -> {return s.length();};
        //Function<String,Integer> function = s -> s.length();
        Function<String,Integer> function = String::length;
        System.out.println("function apply result: " + function.apply("java"));

        //2.predicate
        //Predicate<String> predicate = s -> {return "java".equals(s);};
        //Predicate<String> predicate = s -> "java".equals(s);
        Predicate<String> predicate = "java"::equals;
        System.out.println("predicate test result: " + predicate.test("java"));

        //3.supplier
        //Supplier<String> supplier = ()->{return "java";};
        Supplier<String> supplier = ()->"java";
        System.out.println("supplier get result: " + supplier.get());

        //4.consumer
        //Consumer<String> consumer = s -> {System.out.println("consumer accept result: " + s);};
        Consumer<String> consumer = s -> System.out.println("consumer accept result: " + s);
        consumer.accept("java");

    }
}
