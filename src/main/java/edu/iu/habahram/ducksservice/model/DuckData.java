package edu.iu.habahram.ducksservice.model;


import jakarta.persistence.*;

@Entity
@Table(name = "ducks")
public final class DuckData {
    @Id
    private int id;
    private String name;
    private String type;

    @Column(columnDefinition = "bytea")
    private byte[] image;

    public DuckData() {
    }

    public DuckData(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }
}

