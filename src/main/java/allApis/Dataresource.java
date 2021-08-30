package allApis;

import DAO.DataDAO;
import DataStaticBD.DataBd;
import DataStaticBD.Methods;
import Http.Http;
import Models.Datamodel;
import com.google.gson.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.json.JSONObject;

/**
 *
 * @author Faroukmn_97
 */
@Path("data")
public class Dataresource {

    @Context
    private UriInfo context;

    public Dataresource() {

    }

    /**
     * Retrieves representation of an instance of ini.CRUD
     *
     * @return an instance of java.lang.String
     */
    DataDAO dataDAO = new DataDAO();
    String datad = dataDAO.selectData();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDevice() {
        //TODO return proper representation object
        return Response.ok(datad)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }

    @Path("/shearchforuser")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response shearchforuser() {
        String dataforuser = dataDAO.selectDataAll();
        return Response.ok(dataforuser)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/shearchbyruserdeviceAll")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response shearchforuserdeviceAll(String data) {
        System.out.println(data);
        String responseJson = "{\"status\":\"poken:" + data + "\"}";
        
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
             String device_id = Methods.JsonToString(Jso.getAsJsonObject(), "device_id", "");
            String userbydevice = dataDAO.selectDatabydeviceAll(device_id);
            responseJson = "{\"message\":\" Data returned successfully.\",\"flag\":true,\"data\":" + userbydevice + "}";
        }else {
            responseJson = "{\"message\":\"Missing data.\",\"nameApplication\":\"" + DataBd.nameApplication + "\",\"flag\":" + false + "}";
        }
        return Response.ok(responseJson)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/shearchbyruserdevice")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response shearchforuserdevice(String data) {
        System.out.println(data);
        String responseJson = "{\"status\":\"poken:" + data + "\"}";
        
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
             String device_id = Methods.JsonToString(Jso.getAsJsonObject(), "device_id", "");
            String userbydevice = dataDAO.selectDatabydevice(device_id);
            responseJson = "{\"message\":\" Data returned successfully.\",\"flag\":true,\"data\":" + userbydevice + "}";
        }else {
            responseJson = "{\"message\":\"Missing data.\",\"nameApplication\":\"" + DataBd.nameApplication + "\",\"flag\":" + false + "}";
        }
        return Response.ok(responseJson)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Path("/insertdata")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertdata(String data) {
        //System.out.println(data);
        String responseJson = "{\"status\":\"poken:" + data + "\"}";
        boolean insertd = false;
//        JsonObject Jso = Methods.stringToJSON(data);
        JSONObject Jso = new JSONObject(data);
        //System.out.println(Jso.get("MLX"));
        Http peticion = new Http();
        String user = "Desconocido";
        if (Jso.get("band").toString() == "true") {
            user = peticion.GET("http://be3d-186-3-255-100.ngrok.io");
            JSONObject Jso2 = new JSONObject(user);
            System.out.println("USUARIO RECONOCIDO: " + Jso2.get("Mensaje"));
            user = Jso2.get("Mensaje").toString();;
        }
        //System.out.println(user);
        Datamodel dm = new Datamodel();
        dm.setDevice_id(Jso.get("ID").toString());
        dm.setMqgas(Jso.get("MQGAS").toString());
        dm.setMlx(Jso.get("MLX").toString());
        dm.setMqhumo(Jso.get("MQHUMO").toString());
        dm.setU_detected(user.replaceAll("^[\"']+|[\"']+$", ""));
        insertd = dataDAO.insertData(dm);

        responseJson = "{\"status\":" + insertd + ",\"data\":" + data + ",\"recognized_user\":" + user + "}";
        System.out.println(data);
        return Response.ok(responseJson)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
   
}
