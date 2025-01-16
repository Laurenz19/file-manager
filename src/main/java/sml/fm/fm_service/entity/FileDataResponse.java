package sml.fm.fm_service.entity;

public class FileDataResponse {

    private byte[] data;
    private String contentType;

    // Constructor
    public FileDataResponse(byte[] data, String contentType) {
        this.data = data;
        this.contentType = contentType;
    }

    // Getter for data
    public byte[] getData() {
        return data;
    }

    // Setter for data
    public void setData(byte[] data) {
        this.data = data;
    }

    // Getter for contentType
    public String getContentType() {
        return contentType;
    }

    // Setter for contentType
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}

