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

public class Exercise_2_01
{
    public static int fib(int n)
    {
        return fib(n, 0, 1);
    }

    private static int fib(int n, int a, int b)
    {
        return n <= 0 ? a : fib(n-1, b, a + b);
    }

    public static void main(String[] args)
    {
        System.out.println(fib(15));
    }
}
