package ru.ztrap.rxslideup;

import android.os.Looper;

import io.reactivex.Observer;

/**
 * @author zTrap
 * date 30.05.2017.
 */
final class ThreadUtils {
    
    static boolean checkNotMainThread(Observer<?> observer) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            observer.onError(new IllegalStateException(
                    "Expected to be called on the main thread but was " + Thread.currentThread().getName()));
            return true;
        }
        return false;
    }
    
    private ThreadUtils() {
        throw new AssertionError("No instances.");
    }
}
