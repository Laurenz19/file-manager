package sml.fm.fm_service.entity;

import jakarta.persistence.*;

@Entity
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Lob
    @Column(name = "data", length = 1000)
    private byte[] data; //store the fileData

    // Default constructor
    public FileData() {
    }

    // Constructor with parameters
    public FileData(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
