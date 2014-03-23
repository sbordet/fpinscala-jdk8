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
