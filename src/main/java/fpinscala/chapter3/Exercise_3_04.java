package fpinscala.chapter3;

import java.util.List;

public class Exercise_3_04
{
    public static <T> List<T> drop1(List<T> list, int n)
    {
        // Built-in JDK function.
        return list.subList(n, list.size());
    }

    public static <T> List<T> drop2(List<T> list, int n)
    {
        if (n == 0)
            return list;
        // Tail recursive.
        return drop2(Exercise_3_02.tail(list), n - 1);
    }
}
