package fpinscala.chapter2;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Exercise_2_03
{
    public static <A, B, R> Function<A, Function<B, R>> curry(BiFunction<? super A, ? super B, ? extends R> function)
    {
        return a -> (b -> function.apply(a, b));
    }

    public static Carrot tradeVegetables(Apple apple, Banana banana)
    {
        return new Carrot();
    }

    public static class Apple
    {

    }

    public static class Banana
    {

    }

    public static class Carrot
    {
    }

    public static void main(String[] args)
    {
        Function<Banana, Carrot> rightToTradeBananaForCarrot = curry(Exercise_2_03::tradeVegetables).apply(new Apple());
        System.out.println(rightToTradeBananaForCarrot.apply(new Banana()));
    }
}
