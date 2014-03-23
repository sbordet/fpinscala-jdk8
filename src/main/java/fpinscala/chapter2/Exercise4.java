package fpinscala.chapter2;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Exercise4
{
    public static <A, B, R> BiFunction<A, B, R> uncurry(Function<A, Function<B, R>> function)
    {
        return (a, b) -> function.apply(a).apply(b);
    }
}
