package com.upsmart.part1;

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}


