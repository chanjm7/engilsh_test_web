package hello.engilshtest.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EnglishProblem {

    private long id;
    private long categoryId;
    private String english;
    private String mean;
    private String keyword;

    public EnglishProblem() {

    }

    public EnglishProblem(long categoryId, String english, String mean, String keyword) {
        this.categoryId = categoryId;
        this.english = english;
        this.mean = mean;
        this.keyword = keyword;
    }


}
