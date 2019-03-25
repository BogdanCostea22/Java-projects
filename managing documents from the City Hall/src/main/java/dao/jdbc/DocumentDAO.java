package dao.jdbc;

import dao.DAO;
import model.Document;

import java.util.List;

public class DocumentDAO extends AbstractDAO<Document> implements DAO<Document, String> {

    //Override update function
    public void update(Document document){
        super.update(document, Integer.parseInt(document.getDocumentId()));
    }

    public Document findById(String s) {
        List<Document> list = super.findAll();

        for(Document document : list)
            if(document.getDocumentId().equals(s))
                return document;

        return null;
    }

    //Override delete function
    public  void delete(Document document){
        super.deleteById(Integer.parseInt(document.getDocumentId()));
    }

    public void deleteAll() {

    }
}
