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

public class Exercise_5_04
{
    public static <S> boolean exists(Flow<S> flow, Predicate<S> p)
    {
        // The key point to understand is that the we need to "realize" the second argument of f(),
        // either directly (by calling get()) or indirectly (for example by returning it form f(),
        // since the foldRight() algorithm "realizes" the return value of f()), in order to proceed
        // with the iteration. If the second argument is not "realized", the iteration stops.
        //
        // In exists(), the predicate will fail until the element is found, therefore each time the
        // predicate fails, the iteration will continue.
        return flow.foldRight(() -> false, (element, lazyResult) -> p.test(element) ? () -> true : lazyResult);

        // Another way of writing it, more similar to the Scala version.
        // Note how in this version the "realization" of the second argument is explicit.
//        return flow.foldRight(false, (element, lazyResult) -> () -> p.test(element) || lazyResult.get());
    }

    public static <S> boolean forAll(Flow<S> flow, Predicate<S> p)
    {
        // Like above, we need to "realize" the second argument to iterate.
        // When the predicate fails, iteration is stopped.
        return flow.foldRight(() -> true, (element, lazyResult) -> p.test(element) ? lazyResult : () -> false);
    }

    public static void main(String[] args)
    {
        Flow<Integer> flow = Flow.of(1, 2, 3, 4);
        System.out.println(exists(flow, i -> i == 3));
        System.out.println(forAll(flow, i -> i < 2));
        System.out.println(forAll(flow, i -> i < 5));
    }
}
