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

public class Exercise_4_07
{
    public static <E, A, B> Either<E, Cons<B>> traverse(Cons<A> list, Function<A, Either<E, B>> f)
    {
        // We need to use foldRight() to preserve the order in case of all right values.
        // We call map2() on the result of f() in order to be able to return the *first* error encountered.
        return list.foldRight(Either.of(Cons.empty()), (element, accumulated) -> f.apply(element).map2(accumulated, Cons::new));
    }

    public static <E, A> Either<E, Cons<A>> sequence(Cons<Either<E, A>> list)
    {
        // Reimplementation of sequence() in terms of traverse().
        return traverse(list, a -> a);
    }

    public static void main(String[] args)
    {
        System.out.println(traverse(Cons.of(2, 4, 6), Either::of));

        Cons<Either<Throwable, Integer>> list = Cons.of(Either.of(1), Either.fail(new Error()), Either.fail(new Exception()));
        System.out.println(sequence(list));
    }
}
