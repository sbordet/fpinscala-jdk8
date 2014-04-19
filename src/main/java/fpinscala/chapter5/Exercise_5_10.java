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
package fpinscala.chapter5;

public class Exercise_5_10
{
    public static Flow<Integer> fibs()
    {
        return helpFibs(0, 1);
    }

    private static Flow<Integer> helpFibs(int i, int j)
    {
        return new Flow<>(() -> i, () -> helpFibs(j, i + j));
    }

    public static void main(String[] args)
    {
        Flow<Integer> flow = fibs();
        System.out.println(Exercise_5_02.take(flow, 10).toCons());
    }
}
