package ru.ztrap.rxslideup;

import com.mancj.slideup.SlideUp;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * @author zTrap
 * date 30.05.2017.
 */
final class VisibilityObservable extends Observable<Integer> {
    private final SlideUp mSlideUp;
    
    VisibilityObservable(SlideUp slideUp) {
        mSlideUp = slideUp;
    }
    
    @Override
    protected void subscribeActual(Observer<? super Integer> observer) {
        if (!ThreadUtils.checkMainThread(observer)) {
            return;
        }
        Listener listener = new Listener(mSlideUp, observer);
        observer.onSubscribe(listener);
        mSlideUp.addSlideListener(listener);
    
        if (!mSlideUp.isAnimationRunning()){
            if(!listener.isDisposed()) {
                listener.mObserver.onNext(mSlideUp.getSliderView().getVisibility());
            }
        }
    }
    
    final class Listener extends SlideUpObservable<Integer> implements SlideUp.Listener.Visibility {
        
        Listener(SlideUp slideUp, Observer<? super Integer> observer) {
            super(slideUp, observer);
        }
        
        @Override
        protected void onDispose() {
            mSlideUp.removeSlideListener(this);
        }
    
        @Override
        public void onVisibilityChanged(int i) {
            if (!isDisposed()) {
                mObserver.onNext(i);
            }
        }
    }
}
