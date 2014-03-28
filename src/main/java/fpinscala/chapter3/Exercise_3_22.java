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

public class Exercise_3_22
{
    public static Cons<Integer> addPairwise(Cons<Integer> list1, Cons<Integer> list2)
    {
        // Since we need to access both lists at the same time, we cannot use
        // fold functions because they run through the whole list they get passed.
        // So we just need to rely on the structure of Cons, summing the heads and
        // then recurse on the tails.

        if (list1.isNil() || list2.isNil())
            return Cons.nil();
        // Non tail recursive.
        return new Cons<>(list1.head + list2.head, addPairwise(list1.tail, list2.tail));
    }

    public static void main(String[] args)
    {
        System.out.println(addPairwise(Cons.of(1, 2, 3), Cons.of(4, 5, 6)));
    }
}
