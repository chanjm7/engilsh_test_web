package hello.engilshtest.repository.sentence;

import hello.engilshtest.domain.Category;
import hello.engilshtest.domain.EnglishProblem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemorySentenceProblemRepository implements SentenceProblemRepository {

    private static final Map<Long, Category> sentenceCategories = new ConcurrentHashMap<>();
    private static final Map<Long, EnglishProblem> sentences = new ConcurrentHashMap<>();
    private static long sequenceCategoryId = 0L;
    private static long sequenceSentenceId = 0L;

    @Override
    public Category saveCategory(Category category) {
        category.setId(++sequenceCategoryId);
        sentenceCategories.put(category.getId(), category);
        return category;
    }

    @Override
    public Category findCategoryById(long id) {
        return sentenceCategories.get(id);
    }

    @Override
    public ArrayList<Category> FindCategories() {
        return new ArrayList<>(sentenceCategories.values());
    }

    @Override
    public EnglishProblem saveSentence(EnglishProblem sentence) {
        sentence.setId(++sequenceSentenceId);
        sentences.put(sentence.getId(), sentence);
        return sentence;
    }

    @Override
    public EnglishProblem findSentenceById(long id) {
        return sentences.get(id);
    }

    @Override
    public ArrayList<EnglishProblem> findSentences(long categoryId) {
        ArrayList<EnglishProblem> sentencesInCategory = new ArrayList<>();
        Object[] tmp = sentences.values().toArray();

        for (Object obj : tmp) {
            EnglishProblem sentence = (EnglishProblem) obj;
            if (sentence.getCategoryId() == categoryId) {
                sentencesInCategory.add(sentence);
            }
        }
        return sentencesInCategory;
    }

    @Override
    public void updateCategory(long categoryId, Category newCategory) {
        Category oldCategory = sentenceCategories.get(categoryId);
        oldCategory.setName(newCategory.getName());
    }

    @Override
    public void updateSentence(long sentenceId, EnglishProblem newSentence) {
        EnglishProblem oldSentence = sentences.get(sentenceId);

        oldSentence.setEnglish(newSentence.getEnglish());
        oldSentence.setMean(newSentence.getMean());
        oldSentence.setKeyword(newSentence.getKeyword());
    }

    public void clear() {
        sentenceCategories.clear();
        sentences.clear();
    }
}
