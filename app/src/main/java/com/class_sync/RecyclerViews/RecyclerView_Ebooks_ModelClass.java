package com.class_sync.RecyclerViews;

public class RecyclerView_Ebooks_ModelClass {
    String name;
    String author;
    String rating;
    String Img;
    String PdfUrl;
    String description;
    String category;

    public RecyclerView_Ebooks_ModelClass() {
    }
    public RecyclerView_Ebooks_ModelClass(String name, String author, String img, String pdfUrl, String description, String category) {
        this.name = name;
        this.author = author;
        Img = img;
        PdfUrl = pdfUrl;
        this.description = description;
        this.category = category;
    }

    public RecyclerView_Ebooks_ModelClass(String name,String rating, String author, String img, String pdfUrl, String description, String category) {
        this.name = name;
        this.author = author;
        this.rating = rating;
        Img = img;
        PdfUrl = pdfUrl;
        this.description = description;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getPdfUrl() {
        return PdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        PdfUrl = pdfUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
