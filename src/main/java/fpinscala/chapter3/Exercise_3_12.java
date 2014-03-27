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

public class Exercise_3_12
{
    public static <T> Cons<T> reverse(Cons<T> list)
    {
        // See Exercise_3_08: if foldRight() was returning the same list, foldLeft() should reverse it.
        // Cannot use Cons::new as last argument because the parameter order is inverted.
        return Exercise_3_10.foldLeft(list, Cons.nil(), (t, h) -> new Cons<>(h, t));
    }

    public static void main(String[] args)
    {
        System.out.println(reverse(Cons.of(1, 2, 3, 4)));
    }
}
