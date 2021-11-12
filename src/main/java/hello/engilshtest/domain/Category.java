package hello.engilshtest.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Category {
    private long id;
    private String name;

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }
}
