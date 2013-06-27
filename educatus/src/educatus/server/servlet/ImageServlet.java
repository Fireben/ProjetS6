package educatus.server.servlet;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.internationalization.ImageInternal;

public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = -2182576910144267368L;
	private static final int DEFAULT_BUFFER_SIZE = 10240;

	@Inject
	InternationalizationDao internationalizationDao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		DaoModule module = new DaoModule("db-manager-localhost");
		Injector dbInjector = Guice.createInjector(module);
		dbInjector.getInstance(JpaInitializer.class);		
		internationalizationDao = dbInjector.getInstance(InternationalizationDao.class);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// Get ID from request.
		String imageId = request.getParameter("id");

		// Check if ID is supplied to the request.
		if (imageId == null) {
			// Do your thing if the ID is not supplied to the request.
			// Throw an exception, or send 404, or show default/warning image,
			// or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		// Lookup Image by ImageId in database.
		// Do your "SELECT * FROM Image WHERE ImageID" thing.
		Integer id = Integer.valueOf(imageId);
		ImageInternal image = internationalizationDao.findImageInternalById(id);

		// Check if image is actually retrieved from database.
		if (image == null) {
			// Do your thing if the image does not exist in database.
			// Throw an exception, or send 404, or show default/warning image,
			// or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		// Init servlet response.
		response.reset();
		response.setBufferSize(DEFAULT_BUFFER_SIZE);
		response.setContentType("image/jpg");
		response.setHeader("Content-Disposition", "inline; filename=\"" + image.getImageName() + "InternalImage" + "\"");

		// Prepare streams.
		BufferedOutputStream output = null;

		try {
			// Open streams.
			output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

			// Write file contents to response.
			output.write(image.getImageContent());
		} finally {
			// Gently close streams.
			close(output);
		}
	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				// Do your thing with the exception. Print it, log it or mail
				// it.
				e.printStackTrace();
			}
		}
	}
}
