package com.example.finalproject.Service;

import com.example.finalproject.ApiException.ApiException;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.DocumentRepository;
import com.example.finalproject.Repository.SessionRepository;
import com.example.finalproject.Repository.StudentRepository;
import com.example.finalproject.Repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final TutorRepository tutorRepository;
    private final StudentRepository studentRepository;
    private final SessionRepository sessionRepository;

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    //Reema
    // Assign Document to Session >> tutor
    public void addDocument(Document document,Integer session_id,Integer tutor_id) {
        Tutor tutor = tutorRepository.findTutorById(tutor_id);
        if (tutor == null) {
            throw  new ApiException("Tutor not found");
        }
        Session session = sessionRepository.findSessionById(session_id);
        if(session == null) {
            throw new ApiException("Session not found");
        }
        else if(session.getTutor().getId() != tutor_id) {
            throw  new ApiException("Tutor doesn't belong to this session");
        }
        document.setTutor(tutor);
        document.setSession(session);
        documentRepository.save(document);
    }

    public Document updateDocument(Integer userId, Integer id, Document document) {
        Document d = findDocumentById(id);
        if(d == null) {
            throw new ApiException("Document not found");
        }
        if (!d.getTutor().getUser().getId().equals(userId)) {
            throw new ApiException("You do not have permission to perform this action on this document");
        }
        d.setTitle(document.getTitle());
        d.setPrice(document.getPrice());
        return documentRepository.save(d);
    }

    public void deleteDocument(Integer userId, Integer documentId) {
        Document document = documentRepository.findDocumentById(documentId);
        if (document == null) {
            throw new ApiException("Document not found");
        }
        if (!document.getTutor().getUser().getId().equals(userId)) {
            throw new ApiException("You do not have permission to perform this action on this document");
        }
        documentRepository.deleteById(documentId);
    }
    // added by Omar
    public Document findDocumentById(Integer id) {
        Document document = documentRepository.findDocumentById(id);
        if (document == null) {
            throw new ApiException("Document not found");
        }
        return document;
    }

    //Reema
    //Assign Doc to student >> student
    public void assignDocToStudent (Integer doc_id, Integer student_id) {
        Document doc = documentRepository.findDocumentById(doc_id);
        if(doc == null) {
            throw new ApiException("Document not found");
        }

        Student student = studentRepository.findStudentById(student_id);
        if (student == null) {
            throw  new ApiException("Student not found");
        }
        if (!student.isEnrolled()){
            throw new ApiException("Student is not enrolled, you can't assign this Document!");
        }

        doc.setStudent(student);
        documentRepository.save(doc);
        studentRepository.save(student);
    }

    // Omar
    public Document getDocumentByTitle(String title) {
        Document document = documentRepository.findDocumentByTitle(title);
        if (document == null) {
            throw new ApiException("Document not found");
        }
        return document;
    }

}

