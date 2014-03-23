package fpinscala.chapter3;

import java.util.Collections;
import java.util.List;

public class Exercise_3_02
{
    public static <T> List<T> tail(List<T> list)
    {
        return list == null ? Collections.emptyList() : list.subList(1, list.size());
    }
}
