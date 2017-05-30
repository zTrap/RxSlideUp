package ru.ztrap.rxslideup;

import com.mancj.slideup.SlideUp;

import ru.ztrap.rxslideup.events.SlideUpEvent;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/**
 * @author zTrap
 * date 30.05.2017.
 */
class EventsOnSubscribe extends SlideUpOnSubscribe<SlideUpEvent> {
    
    EventsOnSubscribe(SlideUp slideUp) {
        super(slideUp);
    }
    
    @Override
    public void call(final Subscriber<? super SlideUpEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final SlideUp.Listener eventListener = new SlideUp.Listener.Events() {
            int visibility = mSlideUp.getSliderView().getVisibility();
            float percent = mSlideUp.isVisible() ? 0 : 100;
            @Override
            public void onSlide(float percent) {
                this.percent = percent;
                if(!subscriber.isUnsubscribed()) {
                    subscriber.onNext(new SlideUpEvent(mSlideUp, percent, visibility));
                }
            }
        
            @Override
            public void onVisibilityChanged(int visibility) {
                this.visibility = visibility;
                if(!subscriber.isUnsubscribed()) {
                    subscriber.onNext(new SlideUpEvent(mSlideUp, percent, visibility));
                }
            }
        };
        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                mSlideUp.removeSlideListener(eventListener);
            }
        });
        mSlideUp.addSlideListener(eventListener);
    }
}
