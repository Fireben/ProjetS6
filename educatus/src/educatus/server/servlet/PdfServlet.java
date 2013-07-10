package educatus.server.servlet;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.dynamiccontent.DynamicSection;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionPDF;

public class PdfServlet extends HttpServlet {

	private static final long serialVersionUID = -2182576910144267368L;
	private static final int DEFAULT_BUFFER_SIZE = 50240;

	@Inject
	EntityManager entityManager;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		DaoModule module = new DaoModule("db-manager-localhost");
		Injector dbInjector = Guice.createInjector(module);
		dbInjector.getInstance(JpaInitializer.class);		
		entityManager = dbInjector.getInstance(EntityManager.class);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// Get ID from request.
		String pdfId = request.getParameter("id");

		// Check if ID is supplied to the request.
		if (pdfId == null) {
			// Do your thing if the ID is not supplied to the request.
			// Throw an exception, or send 404, or show default/warning image,
			// or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		// Lookup Image by ImageId in database.
		// Do your "SELECT * FROM Image WHERE ImageID" thing.
		Integer id = Integer.valueOf(pdfId);
		DynamicSection dynamicSection = entityManager.find(DynamicSection.class, id);

		// Check if image is actually retrieved from database.
		if (dynamicSection == null || dynamicSection.getDynamicSectionType().getId() != 5) {
			// Do your thing if the image does not exist in database.
			// Throw an exception, or send 404, or show default/warning image,
			// or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		DynamicSectionPDF dynamicSectionPDF = (DynamicSectionPDF)dynamicSection;
		// Init servlet response.
		response.reset();
		response.setBufferSize(DEFAULT_BUFFER_SIZE);
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=\"" + dynamicSectionPDF.getId() + "DynamicSectionPDF" + "\"");

		// Prepare streams.
		BufferedOutputStream output = null;

		try {
			// Open streams.
			output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

			// Write file contents to response.
			output.write(dynamicSectionPDF.getPdfData());
		} finally {
			// Gently close streams.
			// FIXME, check why we get error if we try to close the output
			//close(output);
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
