package by.bsuir.dao;

import by.bsuir.entity.realty.District;
import by.bsuir.entity.realty.Realty;
import by.bsuir.entity.realty.YearBuild;

import java.util.List;

public interface DAORealty {
    List<District> selectDistrict();
    List<YearBuild> selectYearBuild();
    boolean addDistrict(District district);
    boolean editDistrict(District district);
    boolean deleteDistrict(District district);
    boolean yearBuildAdd(YearBuild yearBuild);
    boolean yearBuildEdit(YearBuild yearBuild);
    boolean yearBuildDelete(YearBuild yearBuild);
    List<Realty> selectRealty();
    List<String> selectNameDistrict();
    List<String> selectNameYearBuild();
    double selectPrice(Realty realty);
    double selectCof(Realty realty);
    void addRealty(Realty realty);

}
