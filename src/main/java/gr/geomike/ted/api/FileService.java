package gr.geomike.ted.api;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.*;

@Path("/files")
public class FileService {

    private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C:/Code/ebay_java/web/images/";

    @POST
    @Path("upload")
    @Consumes("multipart/form-data")
    public Response uploadFile(
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

        String filePath = SERVER_UPLOAD_LOCATION_FOLDER	+ contentDispositionHeader.getFileName();

        saveFile(fileInputStream, filePath);

        String output = "File saved to server location : " + filePath;
        System.err.println(output);

        return Response.status(200).entity(output).build();
    }

    private void saveFile(InputStream uploadedInputStream, String serverLocation) {
        try {
            int read = 0;
            byte[] bytes = new byte[1024];

            OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}