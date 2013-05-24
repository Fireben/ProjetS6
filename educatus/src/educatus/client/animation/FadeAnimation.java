package educatus.client.animation;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class FadeAnimation extends Animation {
	private Widget widget;
	
	private double beginOpacity;
	private double finalOpacity;
	
	private int delay = 0;
	private double delta;
	private int duration;
	private int totalDuration;
	
	public FadeAnimation(Widget widget, double beginOpacity, double finalOpacity, int duration) {
		this.widget = widget;	
		this.beginOpacity = beginOpacity;
		this.finalOpacity = finalOpacity;
		this.duration = duration;
	}
	
	@Override
	protected void onUpdate(double progress) {
		widget.getElement().getStyle().setOpacity(progress * delta + beginOpacity);
		if(progress > delay/totalDuration) {
			if(widget instanceof Button && beginOpacity == 0) {
				((Button) widget).setEnabled(true);
			}
		}
	}
	
	protected void onComplete() {
		widget.getElement().getStyle().setOpacity(finalOpacity);
		if(widget instanceof Button && finalOpacity == 0) {
			((Button) widget).setEnabled(false);
		}
	}
	
	protected void onCancel() {
		if(widget instanceof Button) {
			((Button) widget).setEnabled(false);
		}
		widget.getElement().getStyle().setOpacity(0);
	}
	
	protected double interpolate(double progress) {
		if(progress*totalDuration > delay) {
			return (progress - delay/totalDuration);
		}
		else{
			return 0;
		}
	}
	
	public void start() {
		delta = finalOpacity - beginOpacity;
		totalDuration = duration + delay;
		run(totalDuration);
	}
	
	public void startAfterDelay(int delay) {
		this.delay = delay;
		start();
	}
}
