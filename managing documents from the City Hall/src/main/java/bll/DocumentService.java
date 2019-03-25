package bll;

import dao.DAO;
import dao.hibernate.DocumentDao;
import factory.AbstractFactory;
import model.Document;

import java.util.List;

public class DocumentService {
    private static DAO documentDao;

    public DocumentService(AbstractFactory a) {
        documentDao = a.getDocumentDao();
    }

    public void persist(Document entity) {
            documentDao.persist(entity);

    }

    public void update(Document entity) {
        documentDao.update(entity);
    }

    public Document findById(String id) {
        Document document = (Document)documentDao.findById(id);
        return document;
    }

    public void delete(String id) {
        Document document = (Document)documentDao.findById(id);
        documentDao.delete(document);
    }

    public List<Document> findAll() {
        List<Document> documents = documentDao.findAll();
        return documents;

    }

    public void deleteAll() {
        documentDao.deleteAll();
    }

    public  DAO documentDao() {
        return documentDao;
    }
    
    //Return last document id
    public String getLastId()
    {
        List<Document> documents = documentDao.findAll();

        int max = 0;
        for(Document document: documents)
        {
            int max1 = Integer.parseInt(document.getDocumentId());
            if(max1 > max)
                max = max1;
        }
        System.out.print(max);
        return Integer.toString(max+1);
    }
}