package fpinscala.chapter3;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import fpinscala.chapter2.Exercise_2_03;

public class Exercise_3_05
{
    // While the predicate is true, drop elements.
    // At the first false occurrence of the predicate, we are done.
    public static <T> List<T> dropWhile(List<T> list, Predicate<T> predicate)
    {
        if (list.isEmpty())
            return list;

        T head = list.get(0);
        if (!predicate.test(head))
            return list;

        return dropWhile(Exercise_3_02.tail(list), predicate);
    }

    public static <T> List<T> curryableDropWhile(Predicate<T> predicate, List<T> list)
    {
        return dropWhile(list, predicate);
    }

    public static void main(String[] args)
    {
        List<Integer> list = Arrays.asList(1, 2, 4, 8, 16);

        // FP in Scala reports that in Scala the type for "e" must be explicitly declared.
        // This is not necessary in JDK 8.

        System.out.println(dropWhile(list, e -> e < 5));

        // However, it seems that the JDK 8 compiler falls short in figuring out the types for curry().

        // Does not compile, compiler cannot infer types.
//        Function<List<Integer>, List<Integer>> dropWhileLessThan5 = Exercise_2_03.curry(Exercise_3_05::curryableDropWhile).apply(e -> e < 5);

        // The single expression above should be split for the compiler to understand it.

        // Split 1.
        BiFunction<Predicate<Integer>, List<Integer>, List<Integer>> f = Exercise_3_05::curryableDropWhile;
        Function<List<Integer>, List<Integer>> dropWhileLessThan5 = Exercise_2_03.curry(f).apply(e -> e < 5);

        // Split 2.
        Function<Predicate<Integer>, Function<List<Integer>, List<Integer>>> curried = Exercise_2_03.curry(Exercise_3_05::curryableDropWhile);
        dropWhileLessThan5 = curried.apply(e -> e < 5);

        System.out.println(dropWhileLessThan5.apply(list));
    }
}
