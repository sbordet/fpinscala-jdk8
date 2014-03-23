package fpinscala.chapter3;

public class Exercise_3_02
{
    public static <T> Cons<T> tail(Cons<T> list)
    {
        return list.isNil() ? list : list.tail;
    }
}
