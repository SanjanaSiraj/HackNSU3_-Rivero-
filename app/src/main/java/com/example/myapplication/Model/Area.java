package com.example.myapplication.Model;

public class Area {
    private String area_name,deadline,erosionRate,loss,population,userId,documentId;

    public Area() {
    }

    public Area(String area_name, String deadline, String erosionRate, String loss, String population) {
        this.area_name = area_name;
        this.deadline = deadline;
        this.erosionRate = erosionRate;
        this.loss = loss;
        this.population = population;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getErosionRate() {
        return erosionRate;
    }

    public void setErosionRate(String erosionRate) {
        this.erosionRate = erosionRate;
    }

    public String getLoss() {
        return loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
