
package allApis;

import DAO.NotificationDAO;
import DataStaticBD.DataBd;
import DataStaticBD.Methods;
import Models.Notificationsmodel;
import com.google.gson.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Faroukmn_97
 */
@Path("notification")
public class Notificationresource {
    
   @Context
   private UriInfo context; 
   
   public Notificationresource(){
       
   }
   
    NotificationDAO notificationDAO = new NotificationDAO();
    String notificationd = notificationDAO.selectNotification();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDevice() {
        //TODO return proper representation object
        return Response.ok(notificationd)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/insertnotification")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertnotification(String data){
        System.out.println(data);
        String responseJson = "{\"status\":\"poken:" + data + "\"}";
        boolean insertnoti = false;
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            
            Notificationsmodel nm = new Notificationsmodel();
            nm.setData_id(Methods.JsonToString(Jso.getAsJsonObject(), "data_id", ""));
            nm.setTitle(Methods.JsonToString(Jso.getAsJsonObject(), "title", ""));
            nm.setMessage(Methods.JsonToString(Jso.getAsJsonObject(), "message", ""));
            
            insertnoti = notificationDAO.insertNotification(nm);
             responseJson = "{ \"status\":" +  insertnoti + ",\"information\": \" The Notification was inserted.\"}";
         }else{
            responseJson = "{\"message\":\"Missing data.\",\"nameApplication\":\"" + DataBd.nameApplication + "\",\"flag\":" + insertnoti + "}"; 
         }
                
        return Response.ok(responseJson)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                    .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                    .build();
        }
}
