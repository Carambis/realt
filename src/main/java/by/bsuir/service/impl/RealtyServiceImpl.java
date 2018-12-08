package by.bsuir.service.impl;

import by.bsuir.dao.DAOFactory;
import by.bsuir.dao.DAORealty;
import by.bsuir.entity.realty.District;
import by.bsuir.entity.realty.Realty;
import by.bsuir.entity.realty.YearBuild;
import by.bsuir.service.RealtyService;

import java.util.List;

public class RealtyServiceImpl implements RealtyService {
    DAORealty daoRealty = DAOFactory.getInstance().getDaoRealty();

    @Override
    public List<District> selectDistrict() {
        return daoRealty.selectDistrict();
    }

    @Override
    public List<YearBuild> selectYearBuild() {
        return daoRealty.selectYearBuild();
    }

    @Override
    public boolean addDistrict(District district) {
        return daoRealty.addDistrict(district);
    }

    @Override
    public boolean editDistrict(District district) {
        return daoRealty.editDistrict(district);
    }

    @Override
    public boolean deleteDistrict(District district) {
        return daoRealty.deleteDistrict(district);
    }

    @Override
    public boolean yearBuildAdd(YearBuild yearBuild) {
        return daoRealty.yearBuildAdd(yearBuild);
    }

    @Override
    public boolean yearBuildEdit(YearBuild yearBuild) {
        return daoRealty.yearBuildEdit(yearBuild);
    }

    @Override
    public boolean yearBuildDelete(YearBuild yearBuild) {
        return daoRealty.yearBuildDelete(yearBuild);
    }

    @Override
    public List<Realty> selectRealty() {
        return daoRealty.selectRealty();
    }

    @Override
    public List<String> selectNameDistrict() {
        return daoRealty.selectNameDistrict();
    }

    @Override
    public List<String> selectNameYearBuild() {
        return daoRealty.selectNameYearBuild();
    }

    @Override
    public double selectPrice(Realty realty) {
        return daoRealty.selectPrice(realty);
    }

    @Override
    public double selectCof(Realty realty) {
        return daoRealty.selectCof(realty);
    }

    @Override
    public void addRealty(Realty realty) {
        daoRealty.addRealty(realty);
    }
}
