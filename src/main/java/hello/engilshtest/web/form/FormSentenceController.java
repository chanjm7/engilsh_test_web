package hello.engilshtest.web.form;

import hello.engilshtest.domain.Category;
import hello.engilshtest.domain.EnglishProblem;
import hello.engilshtest.repository.sentence.SentenceProblemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@Component
@AllArgsConstructor
@RequestMapping("/sentence/categories")
public class FormSentenceController {

    private final SentenceProblemRepository englishProblemRepository;

    @GetMapping()
    public String categories(Model model) {
        ArrayList<Category> categories = englishProblemRepository.FindCategories();
        model.addAttribute("categories", categories);
        return "basic/sentence/categories";
    }

    @GetMapping("/add")
    public String addCategory() {
        return "basic/sentence/addCategoryForm";
    }

    @PostMapping("/add")
    public String saveCategory(@ModelAttribute("categories") Category category) {
        englishProblemRepository.saveCategory(category);
        return "redirect:/sentence/categories";
    }

    @GetMapping("{categoryId}")
    public String sentences(@PathVariable long categoryId, Model model) {
        ArrayList<EnglishProblem> sentences = englishProblemRepository.findSentences(categoryId);
        Category category = englishProblemRepository.findCategoryById(categoryId);

        model.addAttribute("sentences", sentences);
        model.addAttribute("category", category);
        return "/basic/sentence/sentences";
    }

    @GetMapping("{categoryId}/add")
    public String addSentence(@PathVariable long categoryId, Model model) {
        Category category = englishProblemRepository.findCategoryById(categoryId);

        model.addAttribute("category", category);
        return "basic/sentence/addSentenceForm";
    }

    @PostMapping("{categoryId}/add")
    public String saveSentence(@PathVariable long categoryId, @ModelAttribute EnglishProblem sentence) {
        sentence.setCategoryId(categoryId);
        englishProblemRepository.saveSentence(sentence);
        return "redirect:/sentence/categories/{categoryId}";
    }

    @GetMapping("/edit")
    public String editCategory(Model model) {
        ArrayList<Category> categories = englishProblemRepository.FindCategories();
        model.addAttribute("categories", categories);
        return "basic/sentence/editCategory";
    }

    @GetMapping("{categoryId}/edit")
    public String editCategoryForm(@PathVariable long categoryId, Model model) {
        Category category = englishProblemRepository.findCategoryById(categoryId);
        model.addAttribute("category", category);
        return "basic/sentence/editCategoryForm";
    }

    @PostMapping("{categoryId}/edit")
    public String editCategoryForm(@PathVariable long categoryId,
                                   @ModelAttribute Category newCategory) {
        englishProblemRepository.updateCategory(categoryId, newCategory);
        return "redirect:/sentence/categories/edit";
    }

    @GetMapping("{categoryId}/{sentenceId}/edit")
    public String editSentenceForm(@PathVariable long sentenceId, Model model) {
        EnglishProblem sentence = englishProblemRepository.findSentenceById(sentenceId);

        model.addAttribute("sentence", sentence);
        return "basic/sentence/editSentenceForm";
    }

    @PostMapping("{categoryId}/{sentenceId}/edit")
    public String editSentence(@PathVariable long sentenceId,
                               @ModelAttribute EnglishProblem newSentence) {

        englishProblemRepository.updateSentence(sentenceId, newSentence);
        return "redirect:/sentence/categories/{categoryId}";
    }
}
