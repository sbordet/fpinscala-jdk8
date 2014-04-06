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

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Cons<T>
{
    private static Cons<?> EMPTY = new Cons<>();

    public static <S> Cons<S> empty()
    {
        @SuppressWarnings("unchecked")
        Cons<S> result = (Cons<S>)EMPTY;
        return result;
    }

    @SafeVarargs
    public static <T> Cons<T> of(T... elements)
    {
        return helpOf(0, elements);
    }

    @SafeVarargs
    private static <T> Cons<T> helpOf(int offset, T... elements)
    {
        if (offset == elements.length)
            return empty();
        return new Cons<>(elements[offset], helpOf(offset + 1, elements));
    }

    public final T head;
    public final Cons<T> tail;

    // Only used by EMPTY.
    private Cons()
    {
        head = null;
        tail = null;
    }

    // Values cannot be null.
    public Cons(T head, Cons<T> tail)
    {
        this.head = Objects.requireNonNull(head);
        this.tail = Objects.requireNonNull(tail);
    }

    public boolean isEmpty()
    {
        return this == EMPTY;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Cons))
            return false;
        Cons that = (Cons)obj;
        if (isEmpty())
            return that.isEmpty();
        if (that.isEmpty())
            return false;
        return head.equals(that.head) && tail.equals(that.tail);
    }

    @Override
    public int hashCode()
    {
        return helpHashCode(this);
    }

    private int helpHashCode(Cons<T> list)
    {
        if (isEmpty())
            return 0;
        // Non tail recursive.
        return list.head.hashCode() + 31 * helpHashCode(list.tail);
    }

    @Override
    public String toString()
    {
        return "[" + helpToString(this) + "]";
    }

    private String helpToString(Cons<T> list)
    {
        if (list.isEmpty())
            return "";

        if (list.tail.isEmpty())
            return String.valueOf(list.head);

        // Non tail recursive.
        return String.valueOf(list.head) + "," + helpToString(list.tail);
    }

    public <S> Cons<S> map(Function<T, S> f)
    {
        return Exercise_3_18.map(this, f);
    }

    public <R> R foldRight(R value, BiFunction<T, R, R> f)
    {
        return Exercise_3_07.foldRight(this, value, f);
    }

    public <R> R foldLeft(R value, BiFunction<R, T, R> f)
    {
        return Exercise_3_10.foldLeft(this, value, f);
    }
}
