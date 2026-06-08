package com.example.nodebook_hub.notebook_hub_backend.Entity;


import jakarta.persistence.*;

@Entity
@Table(name="notebook_details")
public class NoteBookDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int note_id;
    private String note_name;
    private String paperType;
    private String size;
    private String ruling;
    private String cover;
    private int quantity;
    private int price;
    private int pages;

    @Lob
    @Column(name="note-image")
    private byte[] noteImage;

    public NoteBookDetails() {
    }

    public NoteBookDetails(int note_id, String paperType, String size, String ruling, String cover, int quantity, byte[] noteImage,int price,int pages,String note_name) {
        this.note_id = note_id;
        this.paperType = paperType;
        this.size = size;
        this.ruling = ruling;
        this.cover = cover;
        this.quantity = quantity;
        this.note_name=note_name;
        this.price=price;
        this.noteImage = noteImage;
        this.pages=pages;
    }

    public String getNote_name() {
        return note_name;
    }

    public void setNote_name(String note_name) {
        this.note_name = note_name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRuling() {
        return ruling;
    }

    public void setRuling(String ruling) {
        this.ruling = ruling;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public byte[] getNoteImage() {
        return noteImage;
    }

    public void setNoteImage(byte[] noteImage) {
        this.noteImage = noteImage;
    }
}
