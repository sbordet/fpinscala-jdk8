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

import java.util.function.Predicate;

public class Exercise_3_21
{
    public static <A> Cons<A> filterViaFlatMap(Cons<A> list, Predicate<A> f)
    {
        // The function passed to flatMap() converts an element into a Cons.
        // We can return Cons.nil() when the predicate fails.
        return Exercise_3_20.flatMap(list, e -> f.test(e) ? Cons.of(e) : Cons.empty());
    }

    public static void main(String[] args)
    {
        System.out.println(filterViaFlatMap(Cons.of(1, 2, 3, 4), e -> e % 2 == 0));
    }
}
