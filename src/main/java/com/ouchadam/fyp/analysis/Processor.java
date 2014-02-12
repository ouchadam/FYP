package com.ouchadam.fyp.analysis;

public interface Processor<T, F> {
    T process(F what);
}
