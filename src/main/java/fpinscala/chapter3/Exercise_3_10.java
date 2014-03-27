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

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class Exercise_3_10
{
    public static <A, B> B foldLeft(Cons<A> list, B value, BiFunction<B, A, B> f)
    {
        if (list.isNil())
            return value;
        // Tail recursive
        return foldLeft(list.tail, f.apply(value, list.head), f);
    }

    public static void main(String[] args)
    {
        System.out.println(foldLeft(Cons.of(1, 2, 4, 8), 0, (v, e) -> {
            System.out.println("e = " + e);
            return v;
        }));


        List<Integer> numbers = Arrays.asList(1, 2, 4);
        System.out.println(numbers.stream().reduce(0, (length, element) -> length + 1, Integer::sum));

        System.out.println(foldLeft(0, numbers, (v, e) -> v + 1));
    }

    // JDK 8 has List.stream().reduce() which is equivalent to foldLeft().
    // Below an implementation of foldLeft() using List.

    public static <A, B> B foldLeft(B value, List<A> list, BiFunction<B, A, B> accumulator)
    {
        if (list.isEmpty())
            return value;
        return foldLeft(accumulator.apply(value, list.get(0)), list.subList(1, list.size()), accumulator);
    }
}
