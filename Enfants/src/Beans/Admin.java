package Beans;

/**
 * @author Marine Veyssiere
 */
public class Admin {

    private Integer id;
    private String pass;

    public Admin(Integer id, String pass) {
        this.id = id;
        this.pass = pass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
