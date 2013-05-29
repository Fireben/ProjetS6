package educatus.client.animation;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class FadeAnimation extends Animation {
	public static final int VERY_QUICK = 150;
	public static final int QUICK = 250;
	public static final int MEDIUM = 500;
	public static final int LONG = 1000;
	public static final int VERY_LONG = 1500;	
	
	public static final double MIN_OPACITY = 0.0;
	public static final double MEDIUM_OPACITY = 0.75;
	public static final double MAX_OPACITY = 1.0;
	
	private Widget widget;
	
	private double beginOpacity;
	private double finalOpacity;
	
	private double delta;
	private int duration;
	
	public FadeAnimation(Widget widget, double beginOpacity, double finalOpacity, int duration) {
		this.widget = widget;	
		this.beginOpacity = beginOpacity;
		this.finalOpacity = finalOpacity;
		this.duration = duration;
	}
	
	@Override
	protected void onUpdate(double progress) {
		widget.getElement().getStyle().setOpacity(progress * delta + beginOpacity);
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
		return progress;
	}
	
	public void start() {
		delta = finalOpacity - beginOpacity;
		run(duration);
	}
	
	public void startAfterDelay(int delay) {
		Timer timer = new Timer() {
			public void run() {
				((Button) widget).setEnabled(true);
				start();
			}
		};
		timer.schedule(delay);
	}
}
