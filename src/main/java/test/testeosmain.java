/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

//import apisclient.NewJerseyClient;
import Controller.UserController;
import DataStaticBD.Conection;
import apisclient.NewJerseyClient;
import DAO.UserDAO;
import DataStaticBD.DataBd;
import DataStaticBD.Methods;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class testeosmain {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        peu();
        new Conection().testConection();
       // String sentence = "SELECT * FROM \"public\".\"TBLUSER\"; 
        Conection con = new Conection();
//        String json = con.getRecordsInJson(sentence);
//        System.out.println(json);
//         JsonObject Jso = Methods.stringToJSON(json);
//         System.out.println(Jso);
   
//        String user_id = Methods.JsonToString(Jso, "user_id", "");
//        String name = Methods.JsonToString(Jso, "name", "");
//        String last_name = Methods.JsonToString(Jso, "last_name", "");
//        String email = Methods.JsonToString(Jso, "email", "");
//        String password = Methods.JsonToString(Jso, "password", "");
//        String address = Methods.JsonToString(Jso, "address", "");
//        String type = Methods.JsonToString(Jso, "type", "");
//        String imguser = Methods.JsonToString(Jso, "imguser", "");
//        System.err.println("ID usuario:" + user_id);
//        System.err.println("NOMBRE usuario:" + name);
//        System.err.println("Email usuario:" + email);
//        String data="{\n" +
//"   \"iduser\":1,\n" +
//"   \"nameproyect\":\"Proyectcompeticion\",\n" +
//"   \"upload_date\":\"2021-01-22\",\n" +
//"   \"date_downloaded\":\"2021-01-22\",\n" +
//"   \"short_description\":\"description\",\n" +
//"   \"description\":\"description\",\n" +
//"   \"project_csv_path\":\" [{csv_path:D:micasa\\\\\\\\carpetaB},{csv_path:D:micasa\\\\\\\\carpetaB}] \",\n" +
//"   \"project_rules\":\"project_rules\",\n" +
//"   \"project_model\":\"project_model\",\n" +
//"   \"prize\":\"24,000\",\n" +
//"   \"evaluation\":\"como se va a evaluar\",\n" +
//"   \"timeline\":\"{fechainicio:2021-02-22,fechafin:2021-03-22,cantidadpush:3}\",\n" +
//"   \"requerimients\":\"project_model\",\n" +
//"    \"imgproyect\":\"G:\\\\\\\\Mi unidad\\\\\\\\2020\",\n" +
//"   \"user_token\":\"qweqwe212232rfewgwrq\"\n" +
//"}";
//        System.out.println(data);
//        JsonObject Jso = Methods.stringToJSON(data);
//        System.out.println(Jso);
//        String iduser = Methods.JsonToString(Jso, "iduser", "");
//        String nameproyect = Methods.JsonToString(Jso, "nameproyect", "");
//        String upload_date = Methods.JsonToString(Jso, "upload_date", "");
//        String date_downloaded = Methods.JsonToString(Jso, "date_downloaded", "");
//        String short_description = Methods.JsonToString(Jso, "short_description", "");
//        String description = Methods.JsonToString(Jso, "description", "");
//        String project_csv_path = Methods.JsonToString(Jso, "project_csv_path", "");
//        String project_rules = Methods.JsonToString(Jso, "project_rules", "");
//        String project_model = Methods.JsonToString(Jso, "project_model", "");
//        String prize = Methods.JsonToString(Jso, "prize", "");
//        String evaluation = Methods.JsonToString(Jso, "evaluation", "");
//        String timeline = Methods.JsonToString(Jso, "timeline", "");
//        String requerimients = Methods.JsonToString(Jso, "requerimients", "");
//        String imgproyect = Methods.JsonToString(Jso, "imgproyect", "");
//        String userID = Methods.JsonToString(Jso, "user_token", "");
////        userID = Methods.getDataToJwt(userID);
//        boolean cat = false;
//        if (!userID.equals("")) {
//            Proyect proyect = new Proyect();
//            ProyectDAO proyectDao = new ProyectDAO();
//            proyect.setIduser(iduser);
//            proyect.setNameproyect(nameproyect);
//            proyect.setUpload_date(upload_date);
//            proyect.setDate_downloaded(date_downloaded);
//            proyect.setShort_description(short_description);
//            proyect.setDescription(description);
//            proyect.setProyect_csv_path(project_csv_path);
//            proyect.setProject_rules(project_rules);
//            proyect.setProject_model(project_model);
//            proyect.setPrize(prize);
//            proyect.setEvaluation(evaluation);
//            proyect.setTimeline(timeline);
//            proyect.setRequerimients(requerimients);
//            proyect.setImgproyect(imgproyect);
//            cat = proyectDao.insertProyectDAO(proyect);
//        }
        
        
//        String userID = Methods.JsonToString(Jso, "user_token", "");
//
//        userID = Methods.getDataToJwt(userID);
//        boolean cat = false;
//        if (!userID.equals("")) {
//            Category category = new Category();
//            CategoryDAO categoryDao = new CategoryDAO();
//            category.setDescripcion_category(categoryName);
//            cat = categoryDao.insertCategoryDAO(category);
//        }
        
        UserController u = new UserController();
        System.out.println(u.encriptPassword("321"));
        
    }
    public static void prueba1(){
        String datas = "{\"name\":\"Puchamon\",\"lastname\":\"Apell\",\"email\":\"pucha@gmail.com\",\"pass\":\"123\"}";
        NewJerseyClient nc = new NewJerseyClient();
        Response r = nc.save(datas);
        System.out.println(r.getStatus());
        System.out.println(r.readEntity(String.class));
    }
    public static void peu(){
        WebTarget webTarget;
        Client client;
        String BASE_URI = "http://localhost:8080/restAPI/webresources";
        String datas = "{\"name\":\"Puchamon\",\"lastname\":\"Apell\",\"email\":\"pucha@gmail.com\",\"pass\":\"123\"}";
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("misapis");
        Response r = webTarget.path("add").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(datas, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);

        System.out.println(r.getStatus());
        System.out.println(r.readEntity(String.class));
    }
}
