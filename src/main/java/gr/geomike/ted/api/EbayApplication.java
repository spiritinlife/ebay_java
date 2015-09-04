package gr.geomike.ted.api;

import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class EbayApplication extends ResourceConfig {

    public EbayApplication() {
        packages("gr.geomike.ted.api");

        register(AuthenticationFilter.class);
    }
}
