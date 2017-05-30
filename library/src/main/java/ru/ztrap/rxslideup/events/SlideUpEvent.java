package ru.ztrap.rxslideup.events;

import com.mancj.slideup.SlideUp;

/**
 * @author zTrap
 * date 30.05.2017.
 */
public class SlideUpEvent {
    private final float mPercent;
    private final int mVisibility;
    private final SlideUp mSlideUp;
    
    public SlideUpEvent(SlideUp slideUp, float percent, int visibility) {
        mSlideUp = slideUp;
        mPercent = percent;
        mVisibility = visibility;
    }
    
    public int visibility() {
        return mVisibility;
    }
    
    public float percent() {
        return mPercent;
    }
    
    public SlideUp slideUp() {
        return mSlideUp;
    }
}
