package com.example.questioncatalogservice.service;

import com.example.questioncatalogservice.entity.Question;
import com.example.questioncatalogservice.exception.ResourceNotFoundException;
import com.example.questioncatalogservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cet élément n'existe pas avec l'ID: " + id));
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cet élément n'existe pas avec l'ID: " + id);
        }
        questionRepository.deleteById(id);
    }
    public Question getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new RuntimeException("No questions available");
        }
        int index = new java.util.Random().nextInt(questions.size());
        return questions.get(index);
    }

    public Question updateQuestion(Long id, Question newQuestion) {
        return questionRepository.findById(id)
                .map(question -> {
                    question.setTexte(newQuestion.getTexte());
                    question.setReponseCorrecte(newQuestion.getReponseCorrecte());
                    question.setPropositions(newQuestion.getPropositions());
                    question.setCategorie(newQuestion.getCategorie());
                    return questionRepository.save(question);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Cet élément n'existe pas avec l'ID: " + id));
    }

    public Question patchQuestion(Long id, Question partialUpdate) {
        return questionRepository.findById(id)
                .map(question -> {
                    if (partialUpdate.getTexte() != null) {
                        question.setTexte(partialUpdate.getTexte());
                    }
                    if (partialUpdate.getReponseCorrecte() != null) {
                        question.setReponseCorrecte(partialUpdate.getReponseCorrecte());
                    }
                    if (partialUpdate.getPropositions() != null) {
                        question.setPropositions(partialUpdate.getPropositions());
                    }
                    if (partialUpdate.getCategorie() != null) {
                        question.setCategorie(partialUpdate.getCategorie());
                    }
                    return questionRepository.save(question);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Cet élément n'existe pas avec l'ID: " + id));
    }
}
