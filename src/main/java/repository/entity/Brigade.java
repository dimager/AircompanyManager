package repository.entity;

import java.util.ArrayList;

public class Brigade implements Entity {
    private long id;
    private String brigadeName;
    private ArrayList<User> brigadeUsers =  new ArrayList<>();

}
