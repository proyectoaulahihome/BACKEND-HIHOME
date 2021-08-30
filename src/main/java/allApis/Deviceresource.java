
package allApis;

import DAO.DeviceDAO;
import DAO.UserDAO;
import DataStaticBD.DataBd;
import DataStaticBD.Methods;
import Http.Http;
import Models.Devicemodel;
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
import org.json.JSONObject;

/**
 *
 * @author Faroukmn_97
 */

@Path("device") 
public class Deviceresource {
    
   @Context
   private UriInfo context; 

    public Deviceresource() {
    }
   
   /**
     * Retrieves representation of an instance of ini.CRUD
     * @return an instance of java.lang.String
     */
    DeviceDAO dd = new DeviceDAO();
    String device = dd.selectDevice();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDevice() {
        //TODO return proper representation object
        return  Response.ok(device)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with") 
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/insertdevice")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertdevice(String data) {
       // System.out.println(data);
        String responseJson = "{\"status\":\"poken:" + data + "\"}";
        boolean insertd  = false;
        JsonObject Jso = Methods.stringToJSON(data);
         if (Jso.size() > 0) {
            Devicemodel dm = new Devicemodel();
            dm.setNamedevice(Methods.JsonToString(Jso.getAsJsonObject(), "namedevice", ""));
            dm.setState(Methods.JsonToString(Jso.getAsJsonObject(), "state", ""));
            insertd = dd.insertDevice(dm);
            
            if(insertd)
            {
              responseJson = "{ \"status\":" +  insertd + ",\"information\": \" The Device was inserted.\"}";
            }
            else{
                responseJson = "{ \"status\":" +  insertd + ",\"information\": \" The Device was not inserted.\"}";
            }   
         }else{
            responseJson = "{\"message\":\"Missing data.\",\"nameApplication\":\"" + DataBd.nameApplication + "\",\"flag\":" + insertd + "}"; 
         }
        return  Response.ok(responseJson)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with") 
                .build();
    }
}
