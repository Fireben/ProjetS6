package educatus.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.AbstractResponse;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface RequestServiceAsync {
	void sendRequest(AbstractRequest request, AsyncCallback<AbstractResponse> responseCallback) throws IllegalArgumentException;
}
