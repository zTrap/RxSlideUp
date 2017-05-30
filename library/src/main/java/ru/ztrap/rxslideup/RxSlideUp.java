package ru.ztrap.rxslideup;


import com.mancj.slideup.SlideUp;

import ru.ztrap.rxslideup.events.SlideUpEvent;
import rx.Observable;

/**
 * @author zTrap
 * date 30.05.2017.
 */
public class RxSlideUp {
    private RxSlideUp(){
        throw new AssertionError("no instance");
    }
    
    public static Observable<Float> slide(SlideUp slideUp){
        return Observable.unsafeCreate(new SlideOnSubscribe(slideUp));
    }
    
    public static Observable<Integer> visibility(SlideUp slideUp){
        return Observable.unsafeCreate(new VisibilityOnSubscribe(slideUp));
    }
    
    public static Observable<SlideUpEvent> events(SlideUp slideUp){
        return Observable.unsafeCreate(new EventsOnSubscribe(slideUp));
    }
}
