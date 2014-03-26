/*
 * Copyright (c) 2008-2014 the original author or authors.
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

public class Exercise_3_07
{
    // The way foldRight() works is to first "explode" the Cons recursively until the last element.
    // Once the whole Cons is exploded , it first applies f to the last element.
    // So with Cons.of(1, 2, 3, 4) and f being the product function, explodes into:
    // 1*(2*(3*(4*value)))
    // Even if the last element is 0, we have to unwind the stack anyway so we cannot short-circuit.
    public static <T, R> R foldRight(Cons<T> list, R value, BiFunction<T, R, R> f)
    {
        if (list.isNil())
            return value;
        // Non tail recursive.
        return f.apply(list.head, foldRight(list.tail, value, f));
    }

    public static void main(String[] args)
    {
        Cons<Integer> list = Cons.of(1, 2, 4, 8, 16);
        System.out.println(foldRight(list, 0, Integer::sum));
    }
}
