package by.bsuir.entity.realty;

import java.io.Serializable;

public class Realty implements Serializable{
    private String district;
    private String typeBuild;
    private double priceFurniture;
    private int yearBuild;
    private double priceRepair;
    private double square;
    private String nameUser;

    public Realty() {
    }

    public Realty(String district, String typeBuild, double priceFurniture, int yearBuild, double priceRepair,
                  double square, String nameUser) {
        this.district = district;
        this.typeBuild = typeBuild;
        this.priceFurniture = priceFurniture;
        this.yearBuild = yearBuild;
        this.priceRepair = priceRepair;
        this.square = square;
        this.nameUser = nameUser;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTypeBuild() {
        return typeBuild;
    }

    public void setTypeBuild(String typeBuild) {
        this.typeBuild = typeBuild;
    }

    public double getPriceFurniture() {
        return priceFurniture;
    }

    public void setPriceFurniture(double priceFurniture) {
        this.priceFurniture = priceFurniture;
    }

    public int getYearBuild() {
        return yearBuild;
    }

    public void setYearBuild(int yearBuild) {
        this.yearBuild = yearBuild;
    }

    public double getPriceRepair() {
        return priceRepair;
    }

    public void setPriceRepair(double priceRepair) {
        this.priceRepair = priceRepair;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Realty realty = (Realty) o;

        if (Double.compare(realty.priceFurniture, priceFurniture) != 0) return false;
        if (yearBuild != realty.yearBuild) return false;
        if (Double.compare(realty.priceRepair, priceRepair) != 0) return false;
        if (Double.compare(realty.square, square) != 0) return false;
        if (district != null ? !district.equals(realty.district) : realty.district != null) return false;
        if (typeBuild != null ? !typeBuild.equals(realty.typeBuild) : realty.typeBuild != null) return false;
        return nameUser != null ? nameUser.equals(realty.nameUser) : realty.nameUser == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = district != null ? district.hashCode() : 0;
        result = 31 * result + (typeBuild != null ? typeBuild.hashCode() : 0);
        temp = Double.doubleToLongBits(priceFurniture);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + yearBuild;
        temp = Double.doubleToLongBits(priceRepair);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(square);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (nameUser != null ? nameUser.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Realty{" +
                "district='" + district + '\'' +
                ", typeBuild='" + typeBuild + '\'' +
                ", priceFurniture=" + priceFurniture +
                ", yearBuild=" + yearBuild +
                ", priceRepair=" + priceRepair +
                ", square=" + square +
                ", nameUser='" + nameUser + '\'' +
                '}';
    }
}