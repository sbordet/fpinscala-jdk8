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

import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Exercise_3_29
{
    public static <A, B> B fold(Node<A> tree, Function<A, B> f, BinaryOperator<B> op)
    {
        if (tree.isLeaf())
            return f.apply(tree.value);
        return op.apply(fold(tree.left, f, op), fold(tree.right, f, op));
    }

    public static <T> long size(Node<T> tree)
    {
        // Need to specify the long suffix to make the compiler happy.
        return fold(tree, a -> 1L, (l, r) -> 1L + l + r);
    }

    public static long maximum(Node<Integer> tree)
    {
        // Need to use Long to make the compiler happy.
        return fold(tree, Long::valueOf, Math::max);
    }

    public static <T> long depth(Node<T> tree)
    {
        // Need to specify the long suffix to make the compiler happy.
        return fold(tree, a -> 0L, (l, r) -> 1L + Math.max(l, r));
    }

    public static <A, B> Node<B> map(Node<A> tree, Function<A, B> f)
    {
        return fold(tree, a -> Node.lf(f.apply(a)), Node::br);
    }

    public static void main(String[] args)
    {
        Node<Integer> tree = Node.br(Node.br(Node.br(Node.lf(1), Node.lf(2)), Node.lf(6)), Node.lf(3));
        System.out.println(size(tree));
        System.out.println(maximum(tree));
        System.out.println(depth(tree));
        System.out.println(map(tree, v -> "S" + v));
    }
}
