package fpinscala.chapter3;

public class Exercise_3_04
{
    public static <T> Cons<T> drop(Cons<T> list, int n)
    {
        if (list.isNil() || n == 0)
            return list;
        // Tail recursive.
        return drop(list.tail, n - 1);
    }
}
