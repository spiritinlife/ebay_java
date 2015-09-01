package gr.geomike.ted.api;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by potis on 01-Sep-15.
 */
@ApplicationPath("api")
public class EbayApplication extends ResourceConfig {
    public EbayApplication() {
        packages("gr.geomike.ted.api");
    }
}
