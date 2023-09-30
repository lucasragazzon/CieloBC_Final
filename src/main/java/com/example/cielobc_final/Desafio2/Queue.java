package com.example.cielobc_final.Desafio2;

public interface Queue<T> {

    void enqueue (T item);

    T dequeue();

    boolean isFull();

    boolean isEmpty();
}
