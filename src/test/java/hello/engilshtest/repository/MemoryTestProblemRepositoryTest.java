package hello.engilshtest.repository;

import hello.engilshtest.domain.Category;
import hello.engilshtest.domain.EnglishProblem;
import hello.engilshtest.repository.sentence.MemorySentenceProblemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class MemoryTestProblemRepositoryTest {

    final MemorySentenceProblemRepository memoryTestProblemRepository = new MemorySentenceProblemRepository();

    @AfterEach
    public void afterEach() {
        memoryTestProblemRepository.clear();
    }

    @Test
    void addCategory() {
        //given
        Category category = new Category("category1");

        //when
        Category saveCategory = memoryTestProblemRepository.saveCategory(category);

        //then
        Category findCategory = memoryTestProblemRepository.findCategoryById(saveCategory.getId());
        assertThat(findCategory.getId()).isEqualTo(saveCategory.getId());
        assertThat(findCategory.getName()).isEqualTo(saveCategory.getName());
        assertThat(findCategory).isEqualTo(saveCategory);
    }

    @Test
    void findCategories() {
        //given
        Category category1 = memoryTestProblemRepository.saveCategory(new Category("category1"));
        Category category2 = memoryTestProblemRepository.saveCategory(new Category("category2"));
        Category category3 = memoryTestProblemRepository.saveCategory(new Category("category3"));
        Category category4 = memoryTestProblemRepository.saveCategory(new Category("category4"));

        //when
        ArrayList<Category> categories = memoryTestProblemRepository.FindCategories();

        //then
        assertThat(categories.size()).isEqualTo(4);
        assertThat(categories).contains(category1, category2, category3, category4);
    }

    @Test
    void findSentences() {
        //given
        EnglishProblem sentence1 = new EnglishProblem(1, "english1", "mean1", "keyword1");
        EnglishProblem sentence2 = new EnglishProblem(2, "english1", "mean1", "keyword1");
        EnglishProblem sentence3 = new EnglishProblem(3, "english1", "mean1", "keyword1");
        EnglishProblem sentence4 = new EnglishProblem(1, "english1", "mean1", "keyword1");
        memoryTestProblemRepository.saveSentence(sentence1);
        memoryTestProblemRepository.saveSentence(sentence2);
        memoryTestProblemRepository.saveSentence(sentence3);
        memoryTestProblemRepository.saveSentence(sentence4);

        //when
        ArrayList<EnglishProblem> sentences = memoryTestProblemRepository.findSentences(1);

        //then
        assertThat(sentences.size()).isEqualTo(2);
        assertThat(sentences).contains(sentence1, sentence4);
    }

    @Test
    void categoryUpdate() {
        //given
        Category oldCategory = new Category("oldCategory");
        Category saveCategory = memoryTestProblemRepository.saveCategory(oldCategory);
        //when
        Category newCategory = new Category("newCategory");
        memoryTestProblemRepository.updateCategory(saveCategory.getId(), newCategory);
        //then
        Category updateCategory = memoryTestProblemRepository.findCategoryById(saveCategory.getId());
        assertThat("newCategory").isEqualTo(updateCategory.getName());
        assertThat(saveCategory.getId()).isEqualTo(updateCategory.getId());
    }

    @Test
    void findSentenceById() {
        //given
        EnglishProblem sentence = new EnglishProblem(1, "hello", "mean", "keyword");
        EnglishProblem saveSentence = memoryTestProblemRepository.saveSentence(sentence);
        //when
        EnglishProblem findSentence = memoryTestProblemRepository.findSentenceById(saveSentence.getId());

        //then
        assertThat(saveSentence).isEqualTo(findSentence);
        assertThat(saveSentence.getId()).isEqualTo(findSentence.getId());
    }

    @Test
    void updateSentence() {
        //given
        EnglishProblem saveSentence =
                memoryTestProblemRepository.saveSentence(new EnglishProblem(1, "a", "a", "a"));

        //when
        memoryTestProblemRepository.updateSentence(saveSentence.getId(), new EnglishProblem(3, "B", "B", "B"));

        //then
        EnglishProblem updateSentence = memoryTestProblemRepository.findSentenceById(saveSentence.getId());
        assertThat(saveSentence).isEqualTo(updateSentence);
        assertThat(saveSentence.getId()).isEqualTo(updateSentence.getId());
        assertThat("B").isEqualTo(updateSentence.getEnglish());
        assertThat("B").isEqualTo(updateSentence.getMean());
        assertThat("B").isEqualTo(updateSentence.getKeyword());

    }
}
