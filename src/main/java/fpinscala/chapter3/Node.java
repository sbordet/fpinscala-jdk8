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

public class Node<T>
{
    public static <S> Node<S> lf(S value)
    {
        return new Node<>(value);
    }

    public static <S> Node<S> br(Node<S> left, Node<S> right)
    {
        return new Node<>(left, right);
    }

    public final T value;
    public final Node<T> left;
    public final Node<T> right;

    private Node(T value)
    {
        this.value = Objects.requireNonNull(value);
        this.left = null;
        this.right = null;
    }

    private Node(Node<T> left, Node<T> right)
    {
        this.value = null;
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
    }

    public boolean isLeaf()
    {
        return value != null;
    }
}
