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

import fpinscala.chapter3.Cons;

public class Exercise_4_02
{
    public static Option<Double> variance(Cons<Double> list)
    {
        return mean(list).flatMap(m -> mean(list.map(x -> Math.pow(x - m, 2))));
    }

    private static Option<Double> mean(Cons<Double> list)
    {
        if (list.isEmpty())
            return Option.empty();
        return Option.unit(sum(list) / length(list));
    }

    private static double sum(Cons<Double> list)
    {
        if (list.isEmpty())
            return 0.0;
        return list.head + sum(list.tail);
    }

    private static long length(Cons<Double> list)
    {
        if (list.isEmpty())
            return 0;
        return 1 + length(list.tail);
    }

    public static void main(String[] args)
    {
        System.out.println(variance(Cons.of(1.0, 3.0, 3.0, 1.0)));
    }
}
