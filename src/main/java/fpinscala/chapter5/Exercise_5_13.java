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

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import fpinscala.chapter4.Option;

public class Exercise_5_13
{
    public static <T, S> Flow<S> map(Flow<T> flow, Function<T, S> f)
    {
        // Function unfold() takes a state and a function that builds the flow.
        // The only way to access the elements of a flow is via its head, and
        // then recursively on the head of the tail and so on.
        // So here we use the given flow as the state, and we pass around its
        // tail as the next state, operating on its head.
        return Exercise_5_11.unfold(flow, s ->
                s.isEmpty() ? Option.empty() : Option.of(new Pair<>(f.apply(s.head.get()), s.tail.get())));
    }

    public static <S> Flow<S> take(Flow<S> flow, int n)
    {
        // Like map(), here the state is composed by the flow *and* n, so that we can
        // deconstruct the flow by referencing its tail *and* decrease n as we unfold.
        return Exercise_5_11.unfold(new Pair<>(flow, n), s ->
                !s.x.isEmpty() && s.y > 0 ? Option.of(new Pair<>(s.x.head.get(), new Pair<>(s.x.tail.get(), s.y - 1))) : Option.empty());
    }

    public static <S> Flow<S> takeWhile(Flow<S> flow, Predicate<S> p)
    {
        return Exercise_5_11.unfold(flow, s ->
                !s.isEmpty() && p.test(s.head.get()) ? Option.of(new Pair<>(s.head.get(), s.tail.get())) : Option.empty());
    }

    public static <A, B, C> Flow<C> zipWith(Flow<A> flow1, Flow<B> flow2, BiFunction<A, B, C> f)
    {
        return Exercise_5_11.unfold(new Pair<>(flow1, flow2), s ->
                s.x.isEmpty() || s.y.isEmpty() ? Option.empty() :
                        Option.of(new Pair<>(f.apply(s.x.head.get(), s.y.head.get()), new Pair<>(s.x.tail.get(), s.y.tail.get()))));
    }

    public static <A, B, C> Flow<C> zipAllWith(Flow<A> flow1, Flow<B> flow2, BiFunction<Option<A>, Option<B>, C> f)
    {
        return Exercise_5_11.unfold(new Pair<>(flow1, flow2), s ->
        {
            if (s.x.isEmpty() && s.y.isEmpty())
                return Option.empty();
            if (s.x.isEmpty())
                return Option.of(new Pair<>(f.apply(Option.empty(), Option.of(s.y.head.get())), new Pair<>(Flow.empty(), s.y.tail.get())));
            if (s.y.isEmpty())
                return Option.of(new Pair<>(f.apply(Option.of(s.x.head.get()), Option.empty()), new Pair<>(s.x.tail.get(), Flow.empty())));
            return Option.of(new Pair<>(f.apply(Option.of(s.x.head.get()), Option.of(s.y.head.get())), new Pair<>(s.x.tail.get(), s.y.tail.get())));
        });
    }

    public static void main(String[] args)
    {
        System.out.println(map(Flow.of(1, 2, 3, 4), i -> i * 2).toCons());
        System.out.println(take(Flow.of(1, 2, 3, 4, 5), 3).toCons());
        System.out.println(takeWhile(Flow.of(1, 2, 3, 4, 5), i -> i < 3).toCons());
        System.out.println(zipWith(Flow.of("1", "2", "3"), Flow.of("A", "B", "C", "D"), (a, b) -> a + b).toCons());
        System.out.println(zipAllWith(Flow.of("1", "2", "3"), Flow.of("A", "B", "C", "D"), (a, b) -> a.getOrElse("-") + b.getOrElse("-")).toCons());
    }
}
