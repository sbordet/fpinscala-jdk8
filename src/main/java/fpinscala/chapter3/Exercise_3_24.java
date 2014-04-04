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

import java.util.function.BiPredicate;

public class Exercise_3_24
{
    public static <A> boolean hasSubSequence(Cons<A> list1, Cons<A> list2)
    {
        return hasSubSequence(list1, list2, Exercise_3_24::startsWith);
    }

    private static <A> boolean hasSubSequence(Cons<A> list1, Cons<A> list2, BiPredicate<Cons<A>, Cons<A>> startsWith)
    {
        if (list1.isEmpty() || list2.isEmpty())
            return false;
        if (startsWith.test(list1, list2))
            return true;
        return hasSubSequence(list1.tail, list2);
    }

    private static <A> boolean startsWith(Cons<A> list1, Cons<A> list2)
    {
        // Canonical implementation based on the structure of Cons.
        if (list2.isEmpty())
            return true;
        if (list1.isEmpty())
            return false;
        if (!list1.head.equals(list2.head))
            return false;
        return startsWith(list1.tail, list2.tail);
    }

    private static <A> boolean startsWith2(Cons<A> list1, Cons<A> list2)
    {
        // In Exercise_3_23, zipWith() allows us to create a Cons from two other Cons
        // by applying a function to their elements positionally.
        // The idea would be to test for equality of the heads, and return a Cons
        // of that element or Cons.nil(), then flatten the result.
        // If we end up with list2, there is a sub sequence.

        // Could be tempting, given Cons<Cons<>>, to use flatMap(), but it will not work,
        // because zipWith() steps in lock between the two lists (first element of both lists,
        // second element of both lists, etc.), while flatMap() will iterate over the first list
        // and for each element iterate over the second list, which is not the semantic we want.

        Cons<Cons<A>> result = Exercise_3_23.zipWith(list1, list2, (a1, a2) -> a1.equals(a2) ? Cons.of(a2) : Cons.empty());
        return Exercise_3_15.concat(result).equals(list2);
    }

    public static void main(String[] args)
    {
        perform(Exercise_3_24::startsWith);
        perform(Exercise_3_24::startsWith2);
    }

    private static <A> void perform(BiPredicate<Cons<Integer>, Cons<Integer>> startsWith)
    {
        Cons<Integer> list = Cons.of(1, 2, 3, 4);
        if (hasSubSequence(list, Cons.empty(), startsWith))
            throw new Error();
        if (!hasSubSequence(list, Cons.of(1), startsWith))
            throw new Error();
        if (!hasSubSequence(list, Cons.of(4), startsWith))
            throw new Error();
        if (!hasSubSequence(list, Cons.of(1, 2), startsWith))
            throw new Error();
        if (!hasSubSequence(list, Cons.of(2, 3), startsWith))
            throw new Error();
        if (hasSubSequence(list, Cons.of(2, 4), startsWith))
            throw new Error();
        if (!hasSubSequence(list, Cons.of(3, 4), startsWith))
            throw new Error();
        if (!hasSubSequence(list, Cons.of(1, 2, 3, 4), startsWith))
            throw new Error();
        if (hasSubSequence(list, Cons.of(1, 2, 3, 4, 5), startsWith))
            throw new Error();
    }
}
