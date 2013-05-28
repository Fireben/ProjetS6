package educatus.shared.services.requestservice;

import java.io.Serializable;

public abstract class AbstractResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	public abstract ResponseTypeEnum GetResponseType();

}
