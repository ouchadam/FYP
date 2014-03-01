package com.ouchadam.fyp.presentation;

class GenerationThread {

    private Thread thread;

    public void start(Runnable algorithm) {
        if (thread != null) {
            stop();
        }
        this.thread = new Thread(algorithm);
        thread.start();
    }

    public void stop() {
        if (thread != null) {
            try {
                this.thread.join();
                this.thread = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void reset() {
        stop();
        thread = null;
    }

    public boolean isRunning() {
        return thread != null;
    }
}
