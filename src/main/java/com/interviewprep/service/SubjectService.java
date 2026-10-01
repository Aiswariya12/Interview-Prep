package com.interviewprep.service;

import com.interviewprep.entity.Subject;
import com.interviewprep.entity.Topic;
import com.interviewprep.exception.BadRequestException;
import com.interviewprep.exception.ResourceNotFoundException;
import com.interviewprep.repository.SubjectRepository;
import com.interviewprep.repository.TopicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;

    public SubjectService(SubjectRepository subjectRepository, TopicRepository topicRepository) {
        this.subjectRepository = subjectRepository;
        this.topicRepository = topicRepository;
    }

    public List<Subject> getAllActiveSubjects() {
        return subjectRepository.findByActiveTrue();
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
    }

    public List<Topic> getTopicsBySubjectId(Long subjectId) {
        return topicRepository.findBySubjectId(subjectId);
    }

    @Transactional
    public Subject createSubject(Subject subject) {
        if (subjectRepository.existsByNameIgnoreCase(subject.getName())) {
            throw new BadRequestException("Subject already exists: " + subject.getName());
        }
        return subjectRepository.save(subject);
    }

    @Transactional
    public Subject updateSubject(Long id, Subject subjectDetails) {
        Subject subject = getSubjectById(id);
        subject.setName(subjectDetails.getName());
        subject.setDescription(subjectDetails.getDescription());
        subject.setIcon(subjectDetails.getIcon());
        subject.setColor(subjectDetails.getColor());
        if (subjectDetails.getActive() != null) {
            subject.setActive(subjectDetails.getActive());
        }
        return subjectRepository.save(subject);
    }

    @Transactional
    public void deleteSubject(Long id) {
        Subject subject = getSubjectById(id);
        subjectRepository.delete(subject);
    }

    @Transactional
    public Topic createTopic(Long subjectId, Topic topic) {
        Subject subject = getSubjectById(subjectId);
        topic.setSubject(subject);
        return topicRepository.save(topic);
    }

    @Transactional
    public void deleteTopic(Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found: " + topicId));
        topicRepository.delete(topic);
    }
}
