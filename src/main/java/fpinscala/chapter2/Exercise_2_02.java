/*
 * Copyright (c) 2008-2014 the original author or authors.
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
package fpinscala.chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public class Exercise_2_02
{
    public static <A> boolean isSorted(List<A> list, BiPredicate<A, A> greaterThan)
    {
        return list.isEmpty() || isSorted(list, greaterThan, 1);
    }

    private static <A> boolean isSorted(List<A> list, BiPredicate<A, A> greaterThan, int index)
    {
        if (index == list.size())
            return true;
        if (!greaterThan.test(list.get(index - 1), list.get(index)))
            return isSorted(list, greaterThan, index + 1);
        else
            return false;
    }

    public static void main(String[] args)
    {
        System.out.println(isSorted(Arrays.asList(1, 2, 4, 8), (x, y) -> x > y));
        System.out.println(isSorted(Arrays.asList(1, 2, 8, 4), (x, y) -> x > y));
    }
}
