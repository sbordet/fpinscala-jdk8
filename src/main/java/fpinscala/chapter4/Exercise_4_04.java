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
package fpinscala.chapter4;

import fpinscala.chapter3.Cons;

public class Exercise_4_04
{
    public static <A> Option<Cons<A>> sequence(Cons<Option<A>> list)
    {
        // The idea is to "split" the Cons into head and tail, as usual.
        // If we call sequence(list.tail) we obtain an Option<Cons<A>>
        // that contains the tail of the final result; so we just need
        // to append it to the value inside the head.
        // How do we access the tail ? We must use map() to transform the
        // tail t into the final result:
        //
        // sequence(list.tail).map(t -> new Cons<>(???, t));
        //
        // The problem is that we don't have the head value.
        // But we can access it from the list's head (which is an Option<A>)
        // via map() or flatMap():
        //
        // h -> sequence(list.tail).map(t -> new Cons<>(h, t));
        //
        // The function above takes an A and returns an Option<A> so it
        // is clear that we must use flatMap().
        //
        // The idiom opt1.flatMap(a -> opt2.map(...)) is nothing more
        // than map2() defined in Exercise_4_03.

        if (list.isEmpty())
            return Option.of(Cons.empty());
//        return list.head.flatMap(h -> sequence(list.tail).map(t -> new Cons<>(h, t)));
        return list.head.map2(sequence(list.tail), Cons::new);
    }

    public static <A> Option<Cons<A>> sequence2(Cons<Option<A>> list)
    {
        // Another idea is to fold over the Cons<Option<A>> list, using an
        // accumulator function that instead of reducing the Options to a
        // single value, builds a Cons out of their values.
        // Again we encounter the idiom opt1.flatMap(a -> opt2.map(...))
        // that is map2().

//        return list.foldRight(Option.of(Cons.empty()), (element, accumulated) -> element.flatMap(h -> accumulated.map(t -> new Cons<>(h, t))));
        return list.foldRight(Option.of(Cons.empty()), (element, accumulated) -> element.map2(accumulated, Cons::new));
    }

    public static void main(String[] args)
    {
        Cons<Option<Integer>> list = Cons.of(Option.of(1), Option.of(2), Option.of(3));
        System.out.println(sequence(list));
        System.out.println(sequence2(list));
    }
}
