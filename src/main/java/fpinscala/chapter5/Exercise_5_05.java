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

import java.util.function.Predicate;

public class Exercise_5_05
{
    public static <S> Flow<S> takeWhile(Flow<S> flow, Predicate<S> p)
    {
        // We iterate over the given flow, and for each element we need to build another flow.
        // Therefore it is evident that the accumulator function must build a new Flow when the
        // predicate is true, or return an empty Flow when it's false.
        // Note how we never "realize" lazyResult, so the iteration does not happen when invoking
        // takeWhile() - we only "iterate" on the first element, but only when calling toCons().
        return flow.foldRight(Flow.empty(), (element, lazyResult) -> () -> p.test(element) ? new Flow<>(() -> element, lazyResult) : Flow.empty());

        // Another way of writing it.
//        return flow.foldRight(Flow.empty(), (element, lazyResult) -> p.test(element) ? () -> new Flow<>(() -> element, lazyResult) : Flow::empty);
    }

    public static void main(String[] args)
    {
        Flow<Integer> flow = Flow.of(1, 2, 3, 4);
        System.out.println(takeWhile(flow, i -> i < 3).toCons());
    }
}
