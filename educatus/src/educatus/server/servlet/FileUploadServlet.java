package educatus.server.servlet;

import educatus.server.businesslogic.FileUploadCacheManager;
import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;
import gwtupload.shared.UConsts;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

public class FileUploadServlet extends UploadAction {
	
	private static final long serialVersionUID = 1L;

	Hashtable<String, String> receivedContentTypes = new Hashtable<String, String>();
	/**
	 * Maintain a list with received files and their content types.
	 */
	Hashtable<String, File> receivedFiles = new Hashtable<String, File>();
	
	/**
	 * Override executeAction to save the received files in a custom place and
	 * delete this items from session.
	 */
	@Override
	public String executeAction(HttpServletRequest request,
			List<FileItem> sessionFiles) throws UploadActionException {
		
		@SuppressWarnings("unused")
		int cont = 0;
		// Default is error generated Id
		String generatedId = "-1";
		
		for (FileItem item : sessionFiles) {
			if (false == item.isFormField()) {
				cont++;
				try {
					if (item.getContentType().contains("image")) {
						
						// TODO, prevent many request from the same user
						File file = File.createTempFile("upload-", ".bin");
						item.write(file);

						InputStream inputStream = new ByteArrayInputStream(item.get());
						BufferedImage bufferedImage = ImageIO.read(inputStream);
						generatedId = FileUploadCacheManager.getInstance().insertNewImage(bufferedImage);
												
						// / Save a list with the received files
						receivedFiles.put(item.getFieldName(), file);
						receivedContentTypes.put(item.getFieldName(),
								item.getContentType());
					}	
					else if(item.getContentType().contains("pdf")) {
						// TODO, prevent many request from the same user
						File file = File.createTempFile("upload-", ".bin");
						item.write(file);
						generatedId = FileUploadCacheManager.getInstance().insertNewPdf(item.get());
						
						// / Save a list with the received files
						receivedFiles.put(item.getFieldName(), file);
						receivedContentTypes.put(item.getFieldName(),
								item.getContentType());
					}
				} catch (Exception e) {
					throw new UploadActionException(e);
				}
			}
		}

		// / Remove files from session because we have a copy of the
		removeSessionFileItems(request);
		
		//Send your customized message to the client.
		return generatedId;
	}

	/**
	 * Get the content of an uploaded file.
	 */
	@Override
	public void getUploadedFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String fieldName = request.getParameter(UConsts.PARAM_SHOW);
		File f = receivedFiles.get(fieldName);
		if (f != null) {
			response.setContentType(receivedContentTypes.get(fieldName));
			FileInputStream is = new FileInputStream(f);
			copyFromInputStreamToOutputStream(is, response.getOutputStream());
		} else {
			renderXmlResponse(request, response, XML_ERROR_ITEM_NOT_FOUND);
		}
	}

	/**
	 * Remove a file when the user sends a delete request.
	 */
	@Override
	public void removeItem(HttpServletRequest request, String fieldName)
			throws UploadActionException {
		File file = receivedFiles.get(fieldName);
		receivedFiles.remove(fieldName);
		receivedContentTypes.remove(fieldName);
		if (file != null) {
			file.delete();
		}
	}
}
