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
package fpinscala.chapter5;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Exercise_5_07
{
    public static <T, S> Flow<S> map(Flow<T> flow, Function<T, S> f)
    {
        // The fold only iterates over the first element, because we never "realize" lazyResult.
        // In this way, it creates the first flow object of the new Flow<S>, and f() is never
        // invoked; only when the new Flow<S> is realized then the computation happens.
        return flow.foldRight(Flow::empty, (element, lazyResult) -> () -> new Flow<>(() -> f.apply(element), lazyResult));
    }

    public static <T> Flow<T> filter(Flow<T> flow, Predicate<T> p)
    {
        // Test the element: if false, just iterate by returning the lazyResult.
        // In this case, the iteration continues until the first element tests to true,
        // then it returns a Flow that will continue the iteration only when "realized".
        return flow.foldRight(Flow::empty, (element, lazyResult) -> p.test(element) ? () -> new Flow<>(() -> element, lazyResult) : lazyResult);
    }

    public static <T extends S, S> Flow<S> append(Flow<T> flow1, Supplier<Flow<S>> flow2)
    {
        // The key thing to remember here is how fold right works: it decomposes the sequence and
        // operates on the last element first, then on the second to last, etc. backwards.
        //
        // append((1, 2, 3), (4, 5, 6))
        //
        // means:
        //
        // (1 append (2 append (3 append (4, 5, 6))))
        //
        // As such we need to fold on flow1 and use flow2 as "starting point" for the fold.

        return flow1.foldRight(flow2::get, (element, lazyResult) -> () -> new Flow<>(() -> element, lazyResult));
    }

    public static <T, S> Flow<S> flatMap(Flow<T> flow, Function<T, Flow<S>> f)
    {
        // In this case it is more difficult to derive flatMap() without dependencies to other functions
        // (like append() here). Here is it natural to use append() since f() returns a Flow and we need
        // to append the lazy result to the result of f().
        return flow.foldRight(Flow::empty, (element, lazyResult) -> () -> append(f.apply(element), lazyResult));
    }

    public static void main(String[] args)
    {
        Flow<Integer> flow = Flow.of(1, 2, 3, 4);
        System.out.println(map(flow, i -> i * 2).toCons());
        System.out.println(filter(flow, i -> i % 2 == 0).toCons());
        System.out.println(append(flow, () -> Flow.of(11, 12, 13, 14)).toCons());
        System.out.println(flatMap(flow, i -> Flow.of(i, i)).toCons());
    }
}
