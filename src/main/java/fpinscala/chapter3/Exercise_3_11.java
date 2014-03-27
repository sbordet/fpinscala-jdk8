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

public class Exercise_3_11
{
    public static int sum(Cons<Integer> list)
    {
        return Exercise_3_10.foldLeft(list, 0, Integer::sum);
    }

    public static int product(Cons<Integer> list)
    {
        return Exercise_3_10.foldLeft(list, 1, (result, element) -> result * element);
    }

    public static <T> int length(Cons<T> list)
    {
        return Exercise_3_10.foldLeft(list, 0, (result, element) -> result + 1);
    }

    public static void main(String[] args)
    {
        Cons<Integer> list = Cons.of(1, 2, 4, 8, 16);
        System.out.println(sum(list));
        System.out.println(product(list));
        System.out.println(length(list));
    }
}
