package example.entity;

import com.owlike.genson.annotation.JsonProperty;

/**
 * Created by Roman Kuziv on 9/22/2016.
 */

public class Me {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{")
                .append("id: ").append(id).append(", ")
                .append("name: ").append(name)
                .append("}");

        return sb.toString();
    }
}
