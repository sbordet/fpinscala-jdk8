package fpinscala.chapter3;

import java.util.Objects;

public class Cons<T>
{
    private static Cons<?> NIL = new Cons<>();

    public static <S> Cons<S> nil()
    {
        @SuppressWarnings("unchecked")
        Cons<S> result = (Cons<S>)NIL;
        return result;
    }

    @SafeVarargs
    public static <T> Cons<T> of(T... elements)
    {
        return helpOf(0, elements);
    }

    @SafeVarargs
    private static <T> Cons<T> helpOf(int offset, T... elements)
    {
        if (offset == elements.length)
            return nil();
        return new Cons<>(elements[offset], helpOf(offset + 1, elements));
    }

    public final T head;
    public final Cons<T> tail;

    // Only used by NIL.
    private Cons()
    {
        head = null;
        tail = null;
    }

    // Values cannot be null.
    public Cons(T head, Cons<T> tail)
    {
        this.head = Objects.requireNonNull(head);
        this.tail = Objects.requireNonNull(tail);
    }

    public boolean isNil()
    {
        return this == NIL;
    }

    @Override
    public String toString()
    {
        return "[" + helpToString(this) + "]";
    }

    private String helpToString(Cons<T> list)
    {
        if (list.isNil())
            return "";

        if (list.tail.isNil())
            return String.valueOf(list.head);

        // Non tail recursive.
        return String.valueOf(list.head) + "," + helpToString(list.tail);
    }
}
