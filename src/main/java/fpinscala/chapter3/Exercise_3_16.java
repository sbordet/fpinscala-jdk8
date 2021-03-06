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

public class Exercise_3_16
{
    public static Cons<Integer> add1(Cons<Integer> list)
    {
        // We have seen that foldRight() can clone a list (Exercise_3_08).
        // We just need to tweak the accumulator function to add 1 to the element.

        return Exercise_3_07.foldRight(list, Cons.empty(), (element, result) -> new Cons<>(element + 1, result));
    }

    public static void main(String[] args)
    {
        System.out.println(add1(Cons.of(1, 2, 3)));
    }
}
