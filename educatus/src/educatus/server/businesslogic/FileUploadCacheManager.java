package educatus.server.businesslogic;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FileUploadCacheManager {
	
	private static FileUploadCacheManager instance = new FileUploadCacheManager();
	
	private FileUploadCacheManager(){
		
	}
	
	public static FileUploadCacheManager getInstance(){
		return instance;
	}

	private HashMap<String, BufferedImage> imageCache = new HashMap<String, BufferedImage>();

	private static int MAX_CACHE_SIZE = 50;
	// TODO, Add MD5 or SHA-1 hash for id
	private volatile AtomicInteger atomicInteger = new AtomicInteger(0);

	public String insertNewImage(BufferedImage image) throws Exception {

		if (imageCache.size() > MAX_CACHE_SIZE) {
			throw new Exception("Max cache size");
		} else {
			int id = atomicInteger.incrementAndGet();
			String sId = String.valueOf(id);
			imageCache.put(sId, image);
			return sId;
		}
	}

	public BufferedImage removeImage(String imageId) throws Exception {		
		BufferedImage image = imageCache.remove(imageId);
		return image;
	}
}
