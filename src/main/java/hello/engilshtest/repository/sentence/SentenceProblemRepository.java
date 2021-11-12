package hello.engilshtest.repository.sentence;

import hello.engilshtest.domain.Category;
import hello.engilshtest.domain.EnglishProblem;

import java.util.ArrayList;

public interface SentenceProblemRepository {

    Category saveCategory(Category category);

    Category findCategoryById(long id);

    ArrayList<Category> FindCategories();

    EnglishProblem saveSentence(EnglishProblem sentence);

    EnglishProblem findSentenceById(long id);

    ArrayList<EnglishProblem> findSentences(long categoryId);

    void updateCategory(long categoryId, Category newCategory);

    void updateSentence(long sentenceId, EnglishProblem newSentence);
}
