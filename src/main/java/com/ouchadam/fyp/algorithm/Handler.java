package com.ouchadam.fyp.algorithm;

abstract class Handler<T> {
    private final T what;
    Handler(T what) {
        this.what = what;
    }
    protected T get() {
        return what;
    }
}
