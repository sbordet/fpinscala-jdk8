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

public class Exercise_3_23
{
    public static <A, B, C> Cons<C> zipWith(Cons<A> list1, Cons<B> list2, BiFunction<A, B, C> f)
    {
        if (list1.isEmpty() || list2.isEmpty())
            return Cons.empty();
        // Non tail recursive.
        return new Cons<>(f.apply(list1.head, list2.head), zipWith(list1.tail, list2.tail, f));
    }

    public static void main(String[] args)
    {
        System.out.println(zipWith(Cons.of(1, 2, 3), Cons.of(4, 5, 6), (a, b) -> a + "+" + b));
    }
}
