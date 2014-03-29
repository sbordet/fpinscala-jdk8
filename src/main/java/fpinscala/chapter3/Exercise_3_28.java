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

import java.util.function.Function;

public class Exercise_3_28
{
    public static <A, B> Node<B> map(Node<A> tree, Function<A, B> f)
    {
        if (tree.isLeaf())
            return Node.lf(f.apply(tree.value));
        return Node.br(map(tree.left, f), map(tree.right, f));
    }

    public static void main(String[] args)
    {
        Node<Integer> tree = Node.br(Node.br(Node.br(Node.lf(1), Node.lf(2)), Node.lf(6)), Node.lf(3));
        System.out.println(map(tree, v -> "S" + v));
    }
}
