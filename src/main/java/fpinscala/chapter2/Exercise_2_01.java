package fpinscala.chapter2;

public class Exercise_2_01
{
    public static int fib(int n)
    {
        return fib(n, 0, 1);
    }

    private static int fib(int n, int a, int b)
    {
        return n <= 0 ? a : fib(n-1, b, a + b);
    }

    public static void main(String[] args)
    {
        System.out.println(fib(15));
    }
}
