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
import java.util.function.Function;

public class Exercise_3_13
{
    public static <A, B> B foldLeftViaFoldRight(Cons<A> list, B value, BiFunction<B, A, B> f)
    {
        // Applying reverse() + foldRight() does the trick, along
        // with permutation of the arguments of the function.
        // Also works for foldRightViaFoldLeft().
        Cons<A> reversed = Exercise_3_12.reverse(list);
        return Exercise_3_07.foldRight(reversed, value, (element, result) -> f.apply(result, element));
    }

    public static <A, B> B foldRightViaFoldLeft(Cons<A> list, B value, BiFunction<A, B, B> f)
    {
        // With Cons.of(1, 2, 3), foldRight() must apply f on the last element first: f.apply(3, value).
        // This operation returns a result that must be passed to f along with the second-to-last element:
        // f.apply(2, f.apply(3, value)) and eventually:
        //
        // f.apply(1, f.apply(2, f.apply(3, value))).
        //
        // Here we need to implement foldRight in terms of foldLeft, and from the structure above
        // it is clear that it's the composition that happens in foldLeft order, while the application
        // (the f.apply() calls) happens in foldRight order.
        //
        // What we need to do is to use foldLeft() to compose functions that apply f,
        // rather than applying f immediately.
        //
        // The function passed to foldLeft() is the accumulator function: it takes an initial "value"
        // and then invokes it for each element with the accumulated "value".
        // In this case we need to accumulate function composition.

        // This means that foldLeft() must return a function, not a value: it's the accumulated function
        // that represents the compositions of all the applications of f, sort of the "outermost" function.
        // Then we can call apply() on that "outermost" function to get the value.
        //
        // We need to compose the applications of f in this way:
        // XXX = f.apply(3, WWW)
        // YYY = f.apply(2, XXX)
        // ZZZ = f.apply(1, YYY)
        //
        // So the application of f is a Function<B, B> that takes a parameter that is passed to f:
        // (xxx) -> f.apply(N, xxx);
        //
        // Now we need to accumulate these Function<B, B> together, which is done by composing them
        // via compose(), so the accumulator function to pass to foldLeft() is:
        //
        // (applier, element) -> applier.compose(b -> f.apply(element, b))
        //
        // By the definition of compose(), this can be reduced to:
        //
        // (applier, element) -> b -> applier.apply(f.apply(element, b))

        BiFunction<Function<B, B>, A, Function<B, B>> fn = (applier, element) -> applier.compose(b -> f.apply(element, b));
//        BiFunction<Function<B, B>, A, Function<B, B>> fn = (applier, element) -> b -> f.apply(element, b);
        return Exercise_3_10.foldLeft(list, b -> b, fn).apply(value);
    }

    public static void main(String[] args)
    {
        System.out.println(foldLeftViaFoldRight(Cons.of(1, 2, 3), 0, Integer::sum));
        System.out.println(foldRightViaFoldLeft(Cons.of(1, 2, 3), 0, Integer::sum));
    }
}
