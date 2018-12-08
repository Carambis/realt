package by.bsuir.entity.realty;

import java.io.Serializable;

public class District implements Serializable {
    private String district;
    private String typeBuild;
    private double price;

    public District(String district, String typeBuild, double price) {
        this.district = district;
        this.typeBuild = typeBuild;
        this.price = price;
    }

    public District() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "District{" +
                "district='" + district + '\'' +
                ", typeBuild='" + typeBuild + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        District district1 = (District) o;

        if (Double.compare(district1.price, price) != 0) return false;
        if (district != null ? !district.equals(district1.district) : district1.district != null) return false;
        return typeBuild != null ? typeBuild.equals(district1.typeBuild) : district1.typeBuild == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = district != null ? district.hashCode() : 0;
        result = 31 * result + (typeBuild != null ? typeBuild.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
