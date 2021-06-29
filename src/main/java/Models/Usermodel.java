
package Models;
/**
 *
 * @author Faroukmn_97
 */
public class Usermodel {
    
    private String user_id = "-2";
    private String name = "-2";
    private String last_name = "-2";
    private String email = "-2";
    private String password = "-2";
    private String address = "-2";
    private String type = "-2";
    private String imguser = "-2";

    public Usermodel() {
    }

    public Usermodel(String User_id,String Name, String Last_name, String Email, String Password, String Address,String Type, String Imguser) {
        this.user_id=User_id;
        this.name = Name;
        this.last_name = Last_name;
        this.email = Email;
        this.password = Password;
        this.address = Address;
        this.type =  Type;
        this.imguser =  Imguser;
  }
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImguser() {
        return imguser;
    }

    public void setImguser(String imguser) {
        this.imguser = imguser;
    }
    
    
    
    
    
}
