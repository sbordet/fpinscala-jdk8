package fpinscala.chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public class Exercise_2_02
{
    public static <A> boolean isSorted(List<A> list, BiPredicate<A, A> greaterThan)
    {
        return list.isEmpty() || isSorted(list, greaterThan, 1);
    }

    private static <A> boolean isSorted(List<A> list, BiPredicate<A, A> greaterThan, int index)
    {
        if (index == list.size())
            return true;
        if (!greaterThan.test(list.get(index - 1), list.get(index)))
            return isSorted(list, greaterThan, index + 1);
        else
            return false;
    }

    public static void main(String[] args)
    {
        System.out.println(isSorted(Arrays.asList(1, 2, 4, 8), (x, y) -> x > y));
        System.out.println(isSorted(Arrays.asList(1, 2, 8, 4), (x, y) -> x > y));
    }
}
