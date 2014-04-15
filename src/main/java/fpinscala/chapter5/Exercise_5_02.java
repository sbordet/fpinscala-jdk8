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

public class Exercise_5_02
{
    public static <S> Flow<S> take(Flow<S> flow, int n)
    {
        // The implementation must use head and tail to "split" the flow and
        // recursively call itself changing the value of n at each recursion,
        // accumulating into a new Flow (it is evident that this is a fold right).
        //
        // The problem lies in the fact that take() takes a Flow<T>, while the
        // Flow constructor takes a Supplier<Flow<T>>.
        // In Java we have to create the Supplier<Flow<T>> explicitly
        // to avoid the invocation of get() to "realize" the tail.
        //
        // As result, this method is not really recursive when called because
        // it just builds the first element of the flow.
        // It is recursive only when the flow is "realized", for example via
        // toCons().

        if (flow.isEmpty())
            return flow;
        if (n == 0)
            return Flow.empty();
        return new Flow<>(flow.head, () -> take(flow.tail.get(), n - 1));
    }

    public static <S> Flow<S> drop(Flow<S> flow, int n)
    {
        if (flow.isEmpty())
            return flow;
        if (n == 0)
            return flow;
        return drop(flow.tail.get(), n - 1);
    }

    public static void main(String[] args)
    {
        Flow<Integer> flow = Flow.of(1, 2, 3, 4);

        System.out.println(take(Flow.empty(), 2).toCons());
        System.out.println(take(flow, 0).toCons());
        System.out.println(take(flow, 2).toCons());
        System.out.println(take(flow, 5).toCons());
        System.out.println();
        System.out.println(drop(Flow.empty(), 2).toCons());
        System.out.println(drop(flow, 0).toCons());
        System.out.println(drop(flow, 2).toCons());
        System.out.println(drop(flow, 5).toCons());
    }
}
