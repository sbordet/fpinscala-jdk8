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

import fpinscala.chapter4.Option;

public class Exercise_5_11
{
    public static <A, S> Flow<A> unfold(S s, Function<S, Option<Pair<A, S>>> f)
    {
        Option<Pair<A, S>> opt = f.apply(s);
        if (opt.isEmpty())
            return Flow.empty();

        // The problem here is that Option does not have a function that re-wraps the element
        // into something different from an Option, something like:
        // <R> R morph(R alt, Function<T, R> f) { return isEmpty() ? alt : f.apply(value); }
        // otherwise we could have written:
        // return opt.morph(Flow.empty(), pair -> new Flow<>(() -> pair.x, () -> unfold(pair.y, f)));

        // Guaranteed to never be null by the check above.
        Pair<A, S> pair = opt.getOrElse(null);
        return new Flow<>(() -> pair.x, () -> unfold(pair.y, f));
    }

    public static class Pair<X, Y>
    {
        public final X x;
        public final Y y;

        public Pair(X x, Y y)
        {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args)
    {
        Flow<Integer> naturals = unfold(0, i -> Option.of(new Pair<>(i, i + 1)));
        System.out.println(Exercise_5_02.take(naturals, 10).toCons());
    }
}
