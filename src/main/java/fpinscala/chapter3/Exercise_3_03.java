package fpinscala.chapter3;

import java.util.ArrayList;
import java.util.List;

public class Exercise_3_03
{
    public static <T> List<T> setHead(List<T> list, T newHead)
    {
        List<T> result = new ArrayList<>();
        result.add(newHead);
        result.addAll(Exercise_3_02.tail(list));
        return result;
    }
}
