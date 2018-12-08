package by.bsuir.entity.realty;

import java.io.Serializable;

public class YearBuild implements Serializable {
    private int yearBuild;
    private double cof;

    public YearBuild(int yearBuild, double cof) {
        this.yearBuild = yearBuild;
        this.cof = cof;
    }

    public YearBuild() {
    }

    public int getYearBuild() {
        return yearBuild;
    }

    public void setYearBuild(int yearBuild) {
        this.yearBuild = yearBuild;
    }

    public double getCof() {
        return cof;
    }

    public void setCof(double cof) {
        this.cof = cof;
    }

    @Override
    public String toString() {
        return "YearBuild{" +
                "yearBuild=" + yearBuild +
                ", cof=" + cof +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        YearBuild yearBuild1 = (YearBuild) o;

        if (yearBuild != yearBuild1.yearBuild) return false;
        return Double.compare(yearBuild1.cof, cof) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = yearBuild;
        temp = Double.doubleToLongBits(cof);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
