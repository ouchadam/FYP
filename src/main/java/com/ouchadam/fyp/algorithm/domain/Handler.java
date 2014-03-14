package com.ouchadam.fyp.algorithm.domain;

abstract class Handler<T> {
    private final T what;
    Handler(T what) {
        this.what = what;
    }
    protected T get() {
        return what;
    }
}
