/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allApis;

import Controller.UserController;
import DAO.DeviceDAO;
import DAO.UserDAO;
import DataStaticBD.DataBd;
import DataStaticBD.Methods;
import Models.Devicemodel;
import Models.UserDevicemodel;
import Models.Usermodel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jorgefaruk
 */

@Path("users")

public class Userresource {

    @Context
    private UriInfo context;

    public Userresource() {
    }

    /**
     * Retrieves representation of an instance of ini.CRUD
     *
     * @return an instance of java.lang.String
     */
    UserDAO ud = new UserDAO();
    DeviceDAO dd = new DeviceDAO();
    String nameusuarios = ud.selectUserAll();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        //TODO return proper representation object
        return Response.ok(nameusuarios)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/usersbydevice")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response usersbydevice(String data) {
        System.out.println(data);
        String responseJson = "{\"status\":\"poken:" + data + "\"}";
        
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
             String device_id = Methods.JsonToString(Jso.getAsJsonObject(), "device_id", "");
            String userbydevice = ud.selectUserbydevice(device_id);
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
    @Path("/logIn")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logIn(String data) {
        System.out.println(data);
        String responseJson = "{\"status\":\"poken:" + data + "\"}";
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {
            String email = Methods.JsonToString(Jso.getAsJsonObject(), "email", "");
            String password = Methods.JsonToString(Jso.getAsJsonObject(), "password", "");
            System.out.println(email);
            System.out.println(password);
            UserController userCon = new UserController();
            Object[] responseLogIn;
            responseLogIn = userCon.LogIn(email, password);

            if (responseLogIn[0].equals(true)) {
                responseJson = "{\"message\":\"" + responseLogIn[1] + "\",\"flag\":" + responseLogIn[0] + ",\"data\":" + (new UserDAO().userDataJson((Usermodel) responseLogIn[2])) + "}";
            } else {
                responseJson = "{\"message\":\"" + responseLogIn[1] + "\",\"nameApplication\":\"" + DataBd.nameApplication + "\",\"flag\":" + responseLogIn[0] + "}";
            }
        } else {
            responseJson = "{\"message\":\"Missing data.\",\"nameApplication\":\"" + DataBd.nameApplication + "\",\"flag\":" + false + "}";
        }
//        System.out.println(responseJson);
        return Response.ok(responseJson)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/userregistration")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response userregistration(String data) {
        System.out.println(data);
        String responseJson = "{\"status\":\"poken:" + data + "\"}";
        boolean insertuser = false;
        JsonObject Jso = Methods.stringToJSON(data);
        if (Jso.size() > 0) {

            Devicemodel dm = new Devicemodel();
            dm.setDevice_id(Methods.JsonToString(Jso.getAsJsonObject(), "iddevice", ""));
            boolean comprobeud = dd.comprobeUniqueDevice(dm);
            System.err.println(comprobeud);
            if (!comprobeud) {
                UserController u = new UserController();
                UserDevicemodel usm = new UserDevicemodel();
                usm.setName(Methods.JsonToString(Jso.getAsJsonObject(), "firstnameregister", ""));
                usm.setLast_name(Methods.JsonToString(Jso.getAsJsonObject(), "lastnameregister", ""));
                usm.setEmail(Methods.JsonToString(Jso.getAsJsonObject(), "emailregister", ""));
                usm.setPassword(u.encriptPassword(Methods.JsonToString(Jso.getAsJsonObject(), "passwordregister", "")));
                usm.setAddress(Methods.JsonToString(Jso.getAsJsonObject(), "addressregister", ""));
                usm.setType(Methods.JsonToString(Jso.getAsJsonObject(), "type", ""));
                usm.setImguser(Methods.JsonToString(Jso.getAsJsonObject(), "imguser", ""));
                usm.setDevice_id(Methods.JsonToString(Jso.getAsJsonObject(), "iddevice", ""));
                usm.setNamedevice(Methods.JsonToString(Jso.getAsJsonObject(), "namedevice", ""));
                usm.setMac(Methods.JsonToString(Jso.getAsJsonObject(), "macdevice", ""));
                
                insertuser = ud.insertUser(usm);
                
                if (insertuser) {
                    responseJson = "{ \"status\":" + insertuser + ",\"information\": \" The User was inserted.\"}";
                } else {
                    responseJson = "{ \"status\":" + insertuser + ",\"information\": \" The User was not inserted.\"}";
                }
            }else{
                responseJson = "{ \"status\":" + !comprobeud + ",\"information\": \" The device code is invalid.\"}";
            }

        } else {
            responseJson = "{\"message\":\"Missing data.\",\"nameApplication\":\"" + DataBd.nameApplication + "\",\"flag\":" + insertuser + "}";
        }
        return Response.ok(responseJson)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-with")
                .build();
    }

}
