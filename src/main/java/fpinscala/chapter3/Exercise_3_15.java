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

public class Exercise_3_15
{
    public static <T> Cons<T> concat(Cons<Cons<T>> lists)
    {
        // Exactly like we can fold a Cons<Integer> using 0 and Integer::sum,
        // we should be able to fold a Cons<Cons<T>> using the "zero" of Cons,
        // which is Cons.nil(), and the operator "+" of Cons, which is append()
        // (see Exercise_3_14).

        return Exercise_3_07.foldRight(lists, Cons.nil(), Exercise_3_14::append);
    }

    public static void main(String[] args)
    {
        System.out.println(concat(Cons.of(Cons.of(1, 2), Cons.of(4, 8), Cons.of(16, 32))));
    }
}
