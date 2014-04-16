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

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import fpinscala.chapter3.Cons;

public class Flow<T>
{
    private static Flow<?> EMPTY = new Flow<>();

    public static <S> Flow<S> empty()
    {
        @SuppressWarnings("unchecked")
        Flow<S> result = (Flow<S>)EMPTY;
        return result;
    }

    @SafeVarargs
    public static <S> Flow<S> of(S... elements)
    {
        return helpOf(0, elements);
    }

    @SafeVarargs
    private static <S> Flow<S> helpOf(int offset, S... elements)
    {
        if (offset == elements.length)
            return empty();
        return new Flow<>(() -> elements[offset], () -> helpOf(offset + 1, elements));
    }

    public final Supplier<T> head;
    public final Supplier<Flow<T>> tail;

    private Flow()
    {
        this.head = null;
        this.tail = null;
    }

    public Flow(Supplier<T> head, Supplier<Flow<T>> tail)
    {
        this.head = Objects.requireNonNull(head);
        this.tail = Objects.requireNonNull(tail);
    }

    public boolean isEmpty()
    {
        return this == EMPTY;
    }

    public <S> S foldRight(S value, BiFunction<T, Supplier<S>, Supplier<S>> f)
    {
        if (isEmpty())
            return value;
        // The result of f.apply() is always "realized" by calling get().
        // In order to proceed with the iteration, the user-supplied function f
        // must either explicitly call get() on the second argument (which "realizes"
        // the tail) or the return value of f must depend on the second argument so
        // that "realizing" the return value will also "realize" the second argument.
        return f.apply(head.get(), () -> tail.get().foldRight(value, f)).get();
    }

    public Cons<T> toCons()
    {
        return Exercise_5_01.toCons(this);
    }
}
