package fpinscala.chapter3;

public class Exercise_3_06
{
    public static <T> Cons<T> init(Cons<T> list)
    {
        if (list.tail.isNil())
            return Cons.nil();
        // Non tail recursive.
        return new Cons<>(list.head, init(list.tail));
    }

    public static void main(String[] args)
    {
        Cons<Integer> list = Cons.of(1, 2, 4, 8, 16);
        System.out.println(init(list));
    }
}
