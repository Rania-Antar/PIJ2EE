package tn.esprit.pitwin.ressource.util;

import javax.ws.rs.core.Response.ResponseBuilder;


public class HeaderUtil {


    public static ResponseBuilder createAlert(ResponseBuilder builder, String message, String param) {
        builder.header("X-app-alert", message);
        builder.header("X-app-params", param);
        return builder;
    }

    public static ResponseBuilder createEntityCreationAlert(ResponseBuilder builder, String entityName, String param) {
        return createAlert(builder, "app." + entityName + ".created", param );
    }

    public static ResponseBuilder createEntityUpdateAlert(ResponseBuilder builder, String entityName, String param) {
        return createAlert(builder, "app." + entityName + ".updated", param);
    }

    public static ResponseBuilder createEntityDeletionAlert(ResponseBuilder builder, String entityName, String param) {
        return createAlert(builder, "app." + entityName + ".deleted", param);
    }

    public static ResponseBuilder createFailureAlert(ResponseBuilder builder, String entityName, String errorKey, String defaultMessage) {

        builder.header("X-app-error", "error." + errorKey);
        builder.header("X-app-params", entityName);
        return builder;
    }
}
