package educatus.shared.request;

import java.io.Serializable;

public abstract class AbstractRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	public abstract RequestTypeEnum GetRequestType();
}
