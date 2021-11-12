package hello.engilshtest;


import hello.engilshtest.domain.Category;
import hello.engilshtest.domain.EnglishProblem;
import hello.engilshtest.repository.sentence.SentenceProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final SentenceProblemRepository sentenceProblemRepository;

    @PostConstruct
    private void init() {
        Category category1 = sentenceProblemRepository.saveCategory(new Category("category1"));
        Category category2 = sentenceProblemRepository.saveCategory(new Category("category2"));
        sentenceProblemRepository.saveSentence(new EnglishProblem(category1.getId(), "sentence1", "mean1", "keyword1"));
        sentenceProblemRepository.saveSentence(new EnglishProblem(category1.getId(), "sentence2", "mean2", "keyword2"));
        sentenceProblemRepository.saveSentence(new EnglishProblem(category1.getId(), "sentence3", "mean3", "keyword3"));

        sentenceProblemRepository.saveSentence(new EnglishProblem(category2.getId(), "sentence1", "mean1", "keyword1"));
        sentenceProblemRepository.saveSentence(new EnglishProblem(category2.getId(), "sentence2", "mean2", "keyword2"));
        sentenceProblemRepository.saveSentence(new EnglishProblem(category2.getId(), "sentence3", "mean3", "keyword3"));
    }

}
