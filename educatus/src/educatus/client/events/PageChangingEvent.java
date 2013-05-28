package educatus.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class PageChangingEvent extends
GwtEvent<PageChangingEvent.PageChangeHandler> {
	public interface PageChangeHandler extends EventHandler {
		void onPageChange(PageChangingEvent event);
	}

	private static Type<PageChangeHandler> TYPE = new Type<PageChangeHandler>();

	public static void fire(HasHandlers source, String message) {
		if (TYPE != null) {
		  source.fireEvent(new PageChangingEvent(message));
		}
	}

	public static Type<PageChangeHandler> getType() {
		return TYPE;
	}

	private final String message;
	
	public PageChangingEvent(final String message) {
		this.message = message;
	}

	@Override
	public Type<PageChangeHandler> getAssociatedType() {
		return TYPE;
	}

	public String getMessage() {
		return message;
	}

	@Override
	protected void dispatch(PageChangeHandler handler) {
		handler.onPageChange(this);
	}
}