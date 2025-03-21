package Models;

import java.time.LocalDate;

public class ReservationModel {
    int ID;
    String department;
    LocalDate startDate;
    LocalDate endDate;
    String clientName;
    int peopleNumber;
    String notes;
    String limpiador;
    String entregador;

    public ReservationModel(String department, LocalDate startDate, LocalDate endDate, String clientName, Integer peopleNumber) {
        this.department = department;
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientName = clientName;
        this.peopleNumber = peopleNumber;
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    public String getClientName(){
        return this.clientName;
    }

    public void setPeopleNumber(int peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public int getPeopleNumber(){
        return this.peopleNumber;
    }

    public void setLimpiador(String limpiador) {
        this.limpiador = limpiador;
    }

    public String getLimpiador() {
        return this.limpiador;
    }

    public void setEntregador(String limpiador) {
        this.entregador = entregador;
    }

    public String getEntregador() {
        return this.entregador;
    }


    public boolean conflictsWith(ReservationModel other) {
        return !(this.endDate.isBefore(other.startDate) || this.startDate.isAfter(other.endDate));
    }

    @Override
    public String toString() {
        return "Huesped: " + clientName + " - CheckIn: " + startDate + " - CheckOut: " + endDate + " - Personas: " + peopleNumber;
    }

}
