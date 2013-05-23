package educatus.client.ui;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.Element;

public class FadeAnimation extends Animation {
	private Element element;
	
	private double beginOpacity;
	private double finalOpacity;
	
	private double delta;
	private int duration;
	
	public FadeAnimation(Element element, double finalOpacity, int duration) {
		this.element = element;	
		this.finalOpacity = finalOpacity;
		this.duration = duration;
	}
	
	@Override
	protected void onUpdate(double progress) {
		element.getStyle().setOpacity(progress * delta + beginOpacity);
	}
	
	protected double interpolate(double progress) {
	    return progress;
	}
	
	public void start() {
		beginOpacity = 0;
		delta = finalOpacity - beginOpacity;
		run(duration);
	}
}
