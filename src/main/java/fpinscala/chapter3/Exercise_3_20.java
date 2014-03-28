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

import java.util.function.Function;

public class Exercise_3_20
{
    public static <A, B> Cons<B> flatMap(Cons<A> list, Function<A, Cons<B>> f)
    {
        // Applying f results in a Cons, to which we need to append the accumulated value.
        // Therefore it's easy to use the append() function from Exercise_3_14.
        return Exercise_3_07.foldRight(list, Cons.nil(), (element, result) -> Exercise_3_14.append(f.apply(element), result));
    }

    public static <A, B> Cons<B> flatMap2(Cons<A> list, Function<A, Cons<B>> f)
    {
        // Alternatively if we map() each element with f, we obtain a Cons<Cons<>>,
        // which we have seen how to flatten in Exercise_3_15 using concat().
        return Exercise_3_15.concat(Exercise_3_18.map(list, f));
    }

    public static void main(String[] args)
    {
        System.out.println(flatMap(Cons.of(1, 2, 3, 4), e -> Cons.of(e, e)));
        System.out.println(flatMap2(Cons.of(1, 2, 3, 4), e -> Cons.of(e, e)));
    }
}
