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
import java.util.function.Function;
import java.util.function.Predicate;

public class Option<A>
{
    private static final Option<?> EMPTY = new Option<>();

    public static <S> Option<S> empty()
    {
        @SuppressWarnings("unchecked")
        Option<S> result = (Option<S>)EMPTY;
        return result;
    }

    public static <S> Option<S> unit(S value)
    {
        // Static method because it must not depend on type parameter T.

        if (value == null)
            return empty();
        return new Option<>(value);
    }

    private final A value;

    private Option()
    {
        this.value = null;
    }

    private Option(A value)
    {
        this.value = Objects.requireNonNull(value);
    }

    public boolean isEmpty()
    {
        return this == EMPTY;
    }

    public <B> Option<B> flatMap(Function<A, Option<B>> f)
    {
        if (isEmpty())
            return empty();
        return f.apply(value);
    }

    public <B> Option<B> map(Function<A, B> f)
    {
        return flatMap(a -> unit(f.apply(value)));
    }

    public A getOrElse(A alternative)
    {
        if (isEmpty())
            return alternative;
        return value;
    }

    public Option<A> orElse(Option<A> alternative)
    {
        if (isEmpty())
            return alternative;
        return this;
    }

    private Option<A> orElse2(Option<A> alternative)
    {
        // Note how similar getOrElse() and orElse() are in their canonical implementation.
        //
        // If we define A==Option<T>, then the signature of getOrElse() becomes:
        // public Option<T> getOrElse(Option<T> alt)
        // which is exactly the signature of orElse().
        //
        // So if we have an Option<Option<A>> oo, we can call:
        // oo.getOrElse(alternative);
        // How do we get the Option<Option<A>> ?
        // A naive attempt would be:
        //
        // return unit(this).getOrElse(alternative);
        //
        // But this won't work because unit(this) does not return the correct value if
        // "this" is empty(): it would "wrap" Option.empty() into an Option, which would
        // then *not* be empty because Option.empty() is not null.
        //
        // What we need to do is to "lift" the interval value from A to Option<A>:

        return map(Option::unit).getOrElse(alternative);
    }

    public Option<A> filter(Predicate<A> p)
    {
        return flatMap(a -> p.test(a) ? this : empty());
    }

    @Override
    public String toString()
    {
        return String.format("[%s]", isEmpty() ? "" : value);
    }
}
