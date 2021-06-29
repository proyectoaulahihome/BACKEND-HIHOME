/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DataStaticBD.Conection;
import Models.Usermodel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorgefaruk
 */
public class UserDAO {
    Conection con;
    String sentence;
    
    public UserDAO() {
        con = new Conection();
    }
    
    public String selectUserDAO(){
        sentence = "select * from tbluser";
        Conection con = new Conection();
        String json = con.getRecordsInJson(sentence);
    return json;
    } 
    
    public Usermodel setUser(DefaultTableModel table, int index)
    {
   
        Usermodel usr = new Usermodel();
        usr.setUser_id(table.getValueAt(index, 0).toString());
        usr.setName(table.getValueAt(index, 1).toString());
        usr.setLast_name(table.getValueAt(index, 2).toString());
        usr.setEmail(table.getValueAt(index, 3).toString());
        usr.setPassword(table.getValueAt(index, 4).toString());
        usr.setAddress(table.getValueAt(index, 5).toString());
        usr.setType(table.getValueAt(index, 6).toString());
        usr.setImguser(table.getValueAt(index, 7).toString());
        return usr;
    }
            
    public String userDataJson(Usermodel usr) {
        String key = "digiclave";
        long tiempo = System.currentTimeMillis();
//        System.out.println(new Date(tiempo) +"-" + new Date(tiempo+900000));
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, key)
                .setSubject(usr.getUser_id())
                .setIssuedAt(new Date(tiempo))
                .setExpiration(new Date(tiempo+900000))
                .compact();
        JsonObjectBuilder jsoB = Json.createObjectBuilder();
        jsoB.add("name", usr.getName());
        jsoB.add("last_name", usr.getLast_name());
        jsoB.add("email", usr.getEmail());
        jsoB.add("password", usr.getPassword());
        jsoB.add("address", usr.getAddress());
        jsoB.add("type", usr.getType());
        jsoB.add("imguser", usr.getImguser());
        jsoB.add("user_token", jwt);
        javax.json.JsonObject jsonObj = jsoB.build();
        return jsonObj.toString();
    }
    
   public boolean comprobeUniqueEmail(Usermodel usr)
    {
        String sentency = String.format("select * from tbluser where email='%s';",usr.getEmail());
        return (((con.returnRecord(sentency)).getRowCount() <= 0));
    }
    
}
