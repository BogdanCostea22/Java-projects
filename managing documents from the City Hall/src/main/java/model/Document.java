package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Document")
public class Document {
    @Id
    @Column(name = "document_id")
    private String documentId;

    @Column(name = "document_type")
    private String documentType;

    public Document() {
    }

    public Document(String documentId, String documentType) {
        this.documentId = documentId;
        this.documentType = documentType;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentId='" + documentId + '\'' +
                ", documentType='" + documentType + '\'' +
                '}';
    }
}
