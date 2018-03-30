package ru.ztrap.rxslideup;

import com.mancj.slideup.SlideUp;

import io.reactivex.Observable;
import io.reactivex.Observer;


/**
 * @author zTrap
 * date 30.05.2017.
 */
final class SlideObservable extends Observable<Float> {
    private final SlideUp mSlideUp;
    
    SlideObservable(SlideUp slideUp) {
        mSlideUp = slideUp;
    }
    
    @Override
    protected void subscribeActual(Observer<? super Float> observer) {
        if (ThreadUtils.checkNotMainThread(observer)) {
            return;
        }
        Listener listener = new Listener(mSlideUp, observer);
        observer.onSubscribe(listener);
        mSlideUp.addSlideListener(listener);
        
        if (!mSlideUp.isAnimationRunning()){
            if(!listener.isDisposed()) {
                listener.mObserver.onNext(mSlideUp.isVisible() ? 0 : 100f);
            }
        }
    }
    
    final class Listener extends SlideUpObservable<Float> implements SlideUp.Listener.Slide {
        
        Listener(SlideUp slideUp, Observer<? super Float> observer) {
            super(slideUp, observer);
        }
    
        @Override
        protected void onDispose() {
            mSlideUp.removeSlideListener(this);
        }
    
        @Override
        public void onSlide(float v) {
            if (!isDisposed()) {
                mObserver.onNext(v);
            }
        }
    }
}
