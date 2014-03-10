package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.Log;
import com.ouchadam.fyp.algorithm.AlgorithmParams;

class GenerationThread {

    private Thread thread;

    public void start(GenerationRunnable algorithm, AlgorithmParams params) {
        if (thread != null) {
            stop();
        }
        this.thread = new Thread(wrapToRunnable(algorithm, params));
        thread.start();
    }

    private Runnable wrapToRunnable(final GenerationRunnable algorithm, final AlgorithmParams params) {
        return new Runnable() {
            @Override
            public void run() {
                algorithm.run(params);
            }
        };
    }

    public void stop() {
        if (thread != null) {
            try {
                this.thread.join();
                this.thread = null;
            } catch (InterruptedException e) {
                Log.w("Invalid midi event", e);
            }
        }
    }

    public void reset() {
        thread = null;
    }

    public boolean isRunning() {
        return thread != null;
    }
}
