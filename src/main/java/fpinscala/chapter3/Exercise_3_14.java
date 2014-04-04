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

public class Exercise_3_14
{
    public static <T> Cons<T> append(Cons<T> list1, Cons<T> list2)
    {
        if (list1.isEmpty())
            return list2;

        // The fold operations take a Cons, a value and an accumulator function.
        // We need to copy the elements of the first Cons, and then just append the second Cons at the tail.
        // We reuse what has been done in Exercise_3_08: foldRight() was creating a copy of the
        // list, and the value was accumulated at the end.

        // Note how append() can be considered the "+" operator for Cons.

        return Exercise_3_07.foldRight(list1, list2, Cons::new);
    }

    public static void main(String[] args)
    {
        System.out.println(append(Cons.of(1, 2, 4), Cons.of(8, 16, 32)));
    }
}
