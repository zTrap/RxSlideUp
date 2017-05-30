package ru.ztrap.rxslideup;

import com.mancj.slideup.SlideUp;

import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;


/**
 * @author zTrap
 * date 30.05.2017.
 */
abstract class SlideUpObservable<T> extends MainThreadDisposable {
    final SlideUp mSlideUp;
    final Observer<? super T> mObserver;
    
    SlideUpObservable(SlideUp slideUp, Observer<? super T> observer){
        mObserver = observer;
        mSlideUp = slideUp;
    }
}
