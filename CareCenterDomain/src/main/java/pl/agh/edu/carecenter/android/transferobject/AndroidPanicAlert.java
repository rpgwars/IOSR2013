package pl.agh.edu.carecenter.android.transferobject;

/**
 * User: piotrpaul
 * Date: 16.05.2013
 * Time: 00:44
 */
public class AndroidPanicAlert {

    private Integer id;

    public Integer getId() {
        return id;
    }

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
