package ru.ztrap.rxslideup;

import com.mancj.slideup.SlideUp;

import rx.Subscriber;
import rx.android.MainThreadSubscription;

/**
 * @author zTrap
 * date 30.05.2017.
 */
class VisibilityOnSubscribe extends SlideUpOnSubscribe<Integer> {
    
    VisibilityOnSubscribe(SlideUp slideUp) {
        super(slideUp);
    }
    
    @Override
    public void call(final Subscriber<? super Integer> subscriber) {
        MainThreadSubscription.verifyMainThread();
        final SlideUp.Listener eventListener = new SlideUp.Listener.Visibility() {
            @Override
            public void onVisibilityChanged(int v) {
                if(!subscriber.isUnsubscribed()) {
                    subscriber.onNext(v);
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
        if (!mSlideUp.isAnimationRunning()){
            if(!subscriber.isUnsubscribed()) {
                subscriber.onNext(mSlideUp.getSliderView().getVisibility());
            }
        }
    }
}
