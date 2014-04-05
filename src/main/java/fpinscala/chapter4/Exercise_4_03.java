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

import java.util.Arrays;
import java.util.function.BiFunction;

public class Exercise_4_03
{
    public static <A, B, C> Option<C> map2(Option<A> oa, Option<B> ob, BiFunction<A, B, C> f)
    {
        return oa.flatMap(a -> ob.map(b -> f.apply(a, b)));
    }

    public static void main(String[] args)
    {
        System.out.println(map2(Option.unit(Arrays.asList("foo", "bar")), Option.unit("string"), (c, s) -> c.size() + s.length()));
    }
}
