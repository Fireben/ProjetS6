package educatus.client.animation;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ListFadeAnimation<T extends HasWidgets> {
	T widgetList;
	ArrayList<FadeAnimation> animations;
	
	public ListFadeAnimation(T widgetList) {
		this.widgetList = widgetList;
	}
	
	public void start(int duration, double beginOpacity, double targetOpacity) {
		animations = new ArrayList<FadeAnimation>();
		
		Iterator<Widget> it = widgetList.iterator();		
		Widget widget;		
		int count = 0;
		while(it.hasNext()) {
			widget = it.next();
			animations.add(new FadeAnimation(widget, beginOpacity, targetOpacity, duration));
			animations.get(count).startAfterDelay(duration * (count++));
		}
	}
	
	public void killAnimations()
	{
		for(int i=0; i<animations.size(); i++)
		{
			animations.get(i).cancel();
		}
	}
}
