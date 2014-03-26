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

import java.util.function.Function;

public class Exercise_2_05
{
    public <A, B, R> Function<A, R> compose(Function<A, B> f1, Function<B, R> f2)
    {
        // Built-in JDK function.
        Function<A, R> result1 = f2.compose(f1);

        // Explicit implementation.
        Function<A, R> result2 = a -> f2.apply(f1.apply(a));

        return result2;
    }
}
