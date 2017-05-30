package ru.ztrap.rxslideup;


import com.mancj.slideup.SlideUp;

import io.reactivex.Observable;
import ru.ztrap.rxslideup.events.SlideUpEvent;

/**
 * @author zTrap
 * date 30.05.2017.
 */
public final class RxSlideUp {
    private RxSlideUp(){
        throw new AssertionError("o instances.");
    }
    
    public static Observable<Float> slide(SlideUp slideUp){
        return Observable.unsafeCreate(new SlideObservable(slideUp));
    }
    
    public static Observable<Integer> visibility(SlideUp slideUp){
        return Observable.unsafeCreate(new VisibilityObservable(slideUp));
    }
    
    public static Observable<SlideUpEvent> events(SlideUp slideUp){
        return Observable.unsafeCreate(new EventsObservable(slideUp));
    }
}
