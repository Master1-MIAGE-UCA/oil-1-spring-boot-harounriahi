package com.example.questioncatalogservice.service;

import com.example.questioncatalogservice.entity.Question;
import com.example.questioncatalogservice.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<Question> findAll() {
        return repository.findAll();
    }

    public Question findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question non trouvée"));
    }

    public Question create(Question question) {
        return repository.save(question);
    }

    public Question update(Long id, Question newQuestion) {
        Question q = findById(id);
        q.setTexte(newQuestion.getTexte());
        q.setCategorie(newQuestion.getCategorie());
        q.setReponseCorrecte(newQuestion.getReponseCorrecte());
        q.setPropositions(newQuestion.getPropositions());
        return repository.save(q);
    }

    public Question patch(Long id, String categorie) {
        Question q = findById(id);
        q.setCategorie(categorie);
        return repository.save(q);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
