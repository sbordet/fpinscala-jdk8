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
package fpinscala.chapter4;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Either<E, A>
{
    public static <X, V> Either<X, V> of(V value)
    {
        return new Either<>(null, Objects.requireNonNull(value));
    }

    public static <X, V> Either<X, V> fail(X problem)
    {
        return new Either<>(Objects.requireNonNull(problem), null);
    }

    private final E problem;
    private final A value;

    private Either(E problem, A value)
    {
        this.problem = problem;
        this.value = value;
    }

    public boolean isRight()
    {
        return value != null;
    }

    public <B> Either<E, B> flatMap(Function<A, Either<E, B>> f)
    {
        return isRight() ? f.apply(value) : fail(problem);
    }

    public <B> Either<E, B> map(Function<A, B> f)
    {
//        return isRight() ? of(f.apply(value)) : fail(problem);
        return flatMap(a -> of(f.apply(a)));
    }

    public <B, C> Either<E, C> map2(Either<E, B> that, BiFunction<A, B, C> f)
    {
        return flatMap(a -> that.map(b -> f.apply(a, b)));
    }

    @Override
    public String toString()
    {
        return "|" + (isRight() ? value : problem) + "|";
    }
}
