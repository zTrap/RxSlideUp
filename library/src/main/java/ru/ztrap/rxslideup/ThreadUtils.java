package ru.ztrap.rxslideup;

import android.os.Looper;

import io.reactivex.Observer;

/**
 * @author zTrap
 * @date 30.05.2017.
 */
final class ThreadUtils {
    
    static boolean checkMainThread(Observer<?> observer) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            observer.onError(new IllegalStateException(
                    "Expected to be called on the main thread but was " + Thread.currentThread().getName()));
            return false;
        }
        return true;
    }
    
    private ThreadUtils() {
        throw new AssertionError("No instances.");
    }
}
