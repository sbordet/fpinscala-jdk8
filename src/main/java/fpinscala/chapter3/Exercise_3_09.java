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

public class Exercise_3_09
{
    public static <A> int length(Cons<A> list)
    {
        return Exercise_3_07.foldRight(list, 0, (a, l) -> l + 1);
    }

    public static void main(String[] args)
    {
        System.out.println(length(Cons.of(1, 2, 3, 4, 5)));
    }
}
