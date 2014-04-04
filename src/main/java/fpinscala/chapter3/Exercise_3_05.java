/*
 * Copyright (c) 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fpinscala.chapter3;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import fpinscala.chapter2.Exercise_2_03;

public class Exercise_3_05
{
    public static <T> Cons<T> dropWhile(Cons<T> list, Predicate<T> predicate)
    {
        if (list.isEmpty())
            return list;

        if (!predicate.test(list.head))
            return list;

        // Tail recursive.
        return dropWhile(list.tail, predicate);
    }

    public static <T> Cons<T> curryableDropWhile(Predicate<T> predicate, Cons<T> list)
    {
        return dropWhile(list, predicate);
    }

    public static void main(String[] args)
    {
        Cons<Integer> list = Cons.of(1, 2, 4, 8, 16);

        // FP in Scala reports that in Scala the type for "e" must be explicitly declared.
        // This is not necessary in JDK 8.

        System.out.println(dropWhile(list, e -> e < 5));

        // However, it seems that the JDK 8 compiler falls short in figuring out the types for curry().

        // Does not compile, the compiler cannot infer types.
//        Function<List<Integer>, List<Integer>> dropWhileLessThan5 = Exercise_2_03.curry(Exercise_3_05::curryableDropWhile).apply(e -> e < 5);

        // The single expression above should be split for the compiler to understand it.

        // A split possibility.
        BiFunction<Predicate<Integer>, Cons<Integer>, Cons<Integer>> methodReference = Exercise_3_05::curryableDropWhile;
        Function<Cons<Integer>, Cons<Integer>> dropWhileLessThan5 = Exercise_2_03.curry(methodReference).apply(e -> e < 5);
        System.out.println(dropWhileLessThan5.apply(list));

        // Another split possibility.
        Function<Predicate<Integer>, Function<Cons<Integer>, Cons<Integer>>> curried = Exercise_2_03.curry(Exercise_3_05::curryableDropWhile);
        dropWhileLessThan5 = curried.apply(e -> e < 5);
        System.out.println(dropWhileLessThan5.apply(list));
    }
}
