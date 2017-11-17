package com.upsmart.part1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by pxh on 2017/10/20.
 */
public class LambdaTest {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, (a, b) -> a.compareTo(b));

        System.out.println(names.toString());

        int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);

        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");

        Predicate<String> predicate = (s) -> s.length() > 0;
    }
}
