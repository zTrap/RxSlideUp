package ru.ztrap.rxslideup;

import com.mancj.slideup.SlideUp;

import io.reactivex.Observable;
import io.reactivex.Observer;
import ru.ztrap.rxslideup.events.SlideUpEvent;

/**
 * @author zTrap
 * date 30.05.2017.
 */
final class EventsObservable  extends Observable<SlideUpEvent> {
    private final SlideUp mSlideUp;
    
    EventsObservable(SlideUp slideUp) {
        mSlideUp = slideUp;
    }
    
    @Override
    protected void subscribeActual(Observer<? super SlideUpEvent> observer) {
        if (!ThreadUtils.checkMainThread(observer)) {
            return;
        }
        Listener listener = new Listener(mSlideUp, observer);
        observer.onSubscribe(listener);
        mSlideUp.addSlideListener(listener);
    }
    
    final class Listener extends SlideUpObservable<SlideUpEvent> implements SlideUp.Listener.Events {
        private int mVisibility = mSlideUp.getSliderView().getVisibility();
        private float mPercent = mSlideUp.isVisible() ? 0 : 100;
        
        Listener(SlideUp slideUp, Observer<? super SlideUpEvent> observer) {
            super(slideUp, observer);
        }
        
        @Override
        protected void onDispose() {
            mSlideUp.removeSlideListener(this);
        }
        
        @Override
        public void onSlide(float percent) {
            this.mPercent = percent;
            if (!isDisposed()) {
                mObserver.onNext(new SlideUpEvent(mSlideUp, mPercent, mVisibility));
            }
        }
    
        @Override
        public void onVisibilityChanged(int visibility) {
            this.mVisibility = visibility;
            if (!isDisposed()) {
                mObserver.onNext(new SlideUpEvent(mSlideUp, mPercent, mVisibility));
            }
        }
    }
}
