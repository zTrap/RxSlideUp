package ru.ztrap.rxslideup;

import com.mancj.slideup.SlideUp;

import rx.Observable;

/**
 * @author zTrap
 * date 30.05.2017.
 */
abstract class SlideUpOnSubscribe<T> implements Observable.OnSubscribe<T> {
    final SlideUp mSlideUp;
    
    SlideUpOnSubscribe(SlideUp slideUp){
        mSlideUp = slideUp;
    }
}
