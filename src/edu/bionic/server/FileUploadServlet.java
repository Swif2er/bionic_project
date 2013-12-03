package edu.bionic.server;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUploadServlet extends HttpServlet {

	private long FILE_SIZE_LIMIT = 20 * 1024 * 1024; // 20 MiB
	private final Logger logger = Logger.getLogger("UploadServlet");
	private File file;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setSizeMax(FILE_SIZE_LIMIT);

			List<FileItem> items = fileUpload.parseRequest(req);

			for (FileItem item : items) {
				if (item.isFormField()) {
					logger.log(Level.INFO, "Received form field:");
					logger.log(Level.INFO, "Name: " + item.getFieldName());
					logger.log(Level.INFO, "Value: " + item.getString());
				} else {
					String fileName = item.getName();
					String contentType = item.getContentType();
					boolean isInMemory = item.isInMemory();
					long sizeInBytes = item.getSize();

					logger.log(Level.INFO, "Received file:");
					logger.log(Level.INFO, "Name: " + fileName);
					logger.log(Level.INFO, "contentType:" + contentType);
					logger.log(Level.INFO, "Size: " + sizeInBytes + " Bytes");

					String root = getServletContext().getRealPath("/");
					File path = new File(root + "/UserSoundClips");
					if (!path.exists()) {
						boolean status = path.mkdirs();
					}

					file = new File(path + "/" + fileName);
					item.write(file);
					logger.log(Level.INFO, "Uploaded to " + path);

				}

				if (!item.isFormField()) {
					if (item.getSize() > FILE_SIZE_LIMIT) {
						resp.sendError(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE,
								"File size exceeds limit");

						return;
					}

					// Typically here you would process the file in some way:
					// InputStream in = item.getInputStream();
					// ...

					if (!item.isInMemory())
						item.delete();
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Throwing servlet exception for unhandled exception", e);
			throw new ServletException(e);
		}
	}

}
