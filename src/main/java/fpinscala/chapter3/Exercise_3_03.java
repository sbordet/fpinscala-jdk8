package fpinscala.chapter3;

public class Exercise_3_03
{
    public static <T> Cons<T> setHead(Cons<T> list, T newHead)
    {
        return new Cons<>(newHead, list.isNil() ? list : list.tail);
    }
}
