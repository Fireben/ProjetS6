package educatus.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import educatus.shared.request.AbstractRequest;
import educatus.shared.response.AbstractResponse;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("requestservice")
public interface RequestService extends RemoteService {
	AbstractResponse sendRequest(AbstractRequest request) throws IllegalArgumentException;
}
