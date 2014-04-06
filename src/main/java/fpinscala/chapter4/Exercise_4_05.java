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

import java.util.function.Function;

import fpinscala.chapter3.Cons;

public class Exercise_4_05
{
    public static <A, B> Option<Cons<B>> traverse(Cons<A> list, Function<A, Option<B>> f)
    {
        // In order to run through the Cons once we "fold" it.
        // We need to apply f() *and* build the resulting Cons in one computation.
        // Start by applying f() to element of the Cons we obtain from folding over it;
        // this is the new head wrapped by an Option, so we map() over it to build the Cons:
        //
        // f.apply(head).map(newHead -> new Cons(newHead, ???))
        //
        // Where do we take the tail ?
        // The tail is the accumulated result, and we just need flatMap() to extract it.
        //
        // Again, flatMap() + map() is map2, so we can write:

//        return list.foldRight(Option.of(Cons.empty()), (head, accumulated) -> accumulated.flatMap(tail -> f.apply(head).map(newHead -> new Cons<>(newHead, tail))));
        return list.foldRight(Option.of(Cons.empty()), (head, accumulated) -> accumulated.map2(f.apply(head), Cons::new));
    }

    public static <A, B> Option<Cons<B>> slow_traverse(Cons<A> list, Function<A, Option<B>> f)
    {
        // Straightforward implementation, but it runs through the list twice:
        // once during map() and twice during sequence().
        return Exercise_4_04.sequence(list.map(a -> f.apply(a)));
    }

    public static <A> Option<Cons<A>> sequence(Cons<Option<A>> list)
    {
        // Reimplementation of sequence() in terms of traverse.
        return traverse(list, a -> a);
    }

    public static void main(String[] args)
    {
        System.out.println(traverse(Cons.of(2, 4, 6), Option::of));
        System.out.println(slow_traverse(Cons.of(2, 4, 6), Option::of));

        Cons<Option<Integer>> list = Cons.of(Option.of(1), Option.of(2), Option.of(3));
        System.out.println(Exercise_4_04.sequence(list));
        System.out.println(sequence(list));
    }
}
