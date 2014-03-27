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
package fpinscala.chapter2;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Exercise_2_03
{
    public static <A, B, R> Function<A, Function<B, R>> curry(BiFunction<? super A, ? super B, ? extends R> function)
    {
        return a -> (b -> function.apply(a, b));
    }

    public static Carrot tradeVegetables(Apple apple, Banana banana)
    {
        return new Carrot();
    }

    public static class Apple
    {

    }

    public static class Banana
    {

    }

    public static class Carrot
    {
    }

    public static void main(String[] args)
    {
        Function<Banana, Carrot> rightToTradeBananaForCarrot = curry(Exercise_2_03::tradeVegetables).apply(new Apple());
        System.out.println(rightToTradeBananaForCarrot.apply(new Banana()));
    }
}
