package cocontravariance;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class NonReifiable {
//    public static <E> E[] toArray(List<E> le, Class<E> clazz) {
//        E[] rv = (E[])Array.newInstance(clazz, le.size());
//        for (int i = 0; i < rv.length; i++) {
//            rv[i] = le.get(i);
//        }
//        return rv;
//    }

    public static <F, E extends F> F[] toArray(List<E> le, Class<F> clazz) {
        F[] rv = (F[])Array.newInstance(clazz, le.size());
        for (int i = 0; i < rv.length; i++) {
            rv[i] = le.get(i);
        }
        return rv;
    }

    public static void main(String[] args) {
        List<String> ls = Arrays.asList("Fred", "Jim", "Sheila");

        CharSequence[] sa = toArray(ls, CharSequence.class);
        System.out.println("Type of sa is " + sa.getClass().getName());

        sa[0] = new StringBuilder("After the fact");
        for (CharSequence s : sa) {
            System.out.println("> " + s);
        }
    }
}
