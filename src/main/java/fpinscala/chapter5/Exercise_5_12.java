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

import fpinscala.chapter4.Option;

public class Exercise_5_12
{
    public static Flow<Integer> fibs()
    {
        return Exercise_5_11.unfold(new Pair<>(0, 1), p -> Option.of(new Pair<>(p.x, new Pair<>(p.y, p.x + p.y))));
    }

    public static Flow<Integer> from(int n)
    {
        return Exercise_5_11.unfold(n, i -> Option.of(new Pair<>(i, i + 1)));
    }

    public static <S> Flow<S> constant(S s)
    {
        return Exercise_5_11.unfold(s, i -> Option.of(new Pair<>(s, s)));
    }

    public static void main(String[] args)
    {
        Flow<Integer> fibs = fibs();
        System.out.println(Exercise_5_02.take(fibs, 10).toCons());
        Flow<Integer> from = from(42);
        System.out.println(Exercise_5_02.take(from, 5).toCons());
        Flow<Integer> constant = constant(13);
        System.out.println(Exercise_5_02.take(constant, 5).toCons());
    }
}
