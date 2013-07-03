package educatus.server.businesslogic;

import educatus.server.persist.dao.internationalization.Image;
import educatus.server.persist.dao.internationalization.ImageExternal;
import educatus.server.persist.dao.internationalization.ImageInternal;

public class ImageAdapter {
	
	public static String getUrlFromAbstractImage(Image image) {
		
		String url = "";
		
		if (image.getType().getId() == 2){
			ImageExternal imageExternal = (ImageExternal) image;
			url = imageExternal.getUrl();			
		} else if (image.getType().getId() == 1){
			ImageInternal imageInternal = (ImageInternal) image;			
			url = "/internalImageServlet?id=" + imageInternal.getId();
		}	
		
		return url;
	}

}
