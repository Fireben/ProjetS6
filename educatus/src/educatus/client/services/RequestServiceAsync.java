package educatus.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import educatus.shared.request.AbstractRequest;
import educatus.shared.response.AbstractResponse;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface RequestServiceAsync {
	void sendRequest(AbstractRequest request, AsyncCallback<AbstractResponse> responseCallback) throws IllegalArgumentException;
}
