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
	private HashMap<String, byte[]> pdfCache = new HashMap<String, byte[]>();

	private static int MAX_CACHE_SIZE = 50;
	// TODO, Add MD5 or SHA-1 hash for id
	private volatile AtomicInteger imageAtomicInteger = new AtomicInteger(0);
	private volatile AtomicInteger pdfAtomicInteger = new AtomicInteger(0);

	public String insertNewImage(BufferedImage image) throws Exception {

		if (imageCache.size() > MAX_CACHE_SIZE) {
			throw new Exception("Max cache size");
		} else {
			int id = imageAtomicInteger.incrementAndGet();
			String sId = String.valueOf(id);
			imageCache.put(sId, image);
			return sId;
		}
	}

	public BufferedImage removeImage(String imageId) throws Exception {		
		BufferedImage image = imageCache.remove(imageId);
		return image;
	}
	
	public String insertNewPdf(byte [] pdf) throws Exception {

		if (pdfCache.size() > MAX_CACHE_SIZE) {
			throw new Exception("Max cache size");
		} else {
			int id = pdfAtomicInteger.incrementAndGet();
			String sId = String.valueOf(id);
			pdfCache.put(sId, pdf);
			return sId;
		}
	}

	public byte[] removePdf(String pdfId) throws Exception {		
		byte[] pdf = pdfCache.remove(pdfId);
		return pdf;
	}
}
