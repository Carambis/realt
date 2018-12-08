package by.bsuir.dao.impl;

import by.bsuir.dao.DAORealty;
import by.bsuir.entity.AccessType;
import by.bsuir.entity.User;
import by.bsuir.entity.realty.District;
import by.bsuir.entity.realty.Realty;
import by.bsuir.entity.realty.YearBuild;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAORealtyImpl implements DAORealty {
    Connection connection;
    private final static String SELECT_DISTRICT = "SELECT * FROM table_price";
    private final static String SELECT_YEAR_BUILD = "SELECT * FROM table_cof";
    private final static String INSERT_DISTRICT = "INSERT INTO table_price (district,price,type_build) VALUES (?,?,?)";
    private final static String EDIT_DISTRICT = "UPDATE table_price SET price = ? WHERE district = ? AND type_build = ?";
    private final static String REMOVE_DISTRICT = "DELETE FROM table_price WHERE district = ? AND type_build = ? AND price = ?";
    private final static String INSERT_YEAR_BUILD = "INSERT INTO table_cof (year_build,up_cof) VALUES (?,?)";
    private final static String EDIT_YEAR_BUILD = "UPDATE tavle_cof SET up_cof = ? WHERE year_build = ?";
    private final static String REMOVE_YEAR_BUILD = "DELETE FROM table_cof WHERE year_build = ? AND up_cof = ?";
    private final static String SELECT_REALTY = "SELECT table_price.district, table_price.type_build," +
            " realty.price_furniture,realty.year_build,realty.square,realty.price_repair,realty.user " +
            "FROM realty INNER JOIN table_price on realty.id_tbprice = table_price.id";
    private final static String SELECT_NAME_DISTRICT = "SELECT district FROM table_price";
    private final static String SELECT_NAME_YEAR_BUILD = "SELECT year_build FROM table_cof";
    private final static String SELECT_PRICE = "SELECT price FROM table_price WHERE district = ? AND type_build = ?";
    private final static String SELECT_COF = "SELECT up_cof FROM table_cof WHERE year_build = ?";
    private final static String SELECT_ID_PRICE = "SELECT id FROM table_price WHERE district = ? AND type_build = ?";

    private final static String ADD_REALTY = "INSERT INTO realty (id_tbprice,price_furniture,year_build,square," +
            "price_repair,user) VALUES (?,?,?,?,?,?)";

    public DAORealtyImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<District> selectDistrict() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        District district = null;
        List<District> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_DISTRICT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                district = new District();
                district.setDistrict(resultSet.getString("district"));
                district.setPrice(Double.parseDouble(resultSet.getString("price")));
                district.setTypeBuild(resultSet.getString("type_build"));
                list.add(district);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return list;
    }

    @Override
    public List<YearBuild> selectYearBuild() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        YearBuild yearBuild = null;
        List<YearBuild> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_YEAR_BUILD);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                yearBuild = new YearBuild();
                yearBuild.setYearBuild(Integer.parseInt(resultSet.getString("year_build")));
                yearBuild.setCof(Double.parseDouble(resultSet.getString("up_cof")));
                list.add(yearBuild);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return list;
    }

    @Override
    public boolean addDistrict(District district) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_DISTRICT);
            preparedStatement.setString(1, district.getDistrict());
            preparedStatement.setString(2, String.valueOf(district.getPrice()));
            preparedStatement.setString(3, district.getTypeBuild());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    @Override
    public boolean editDistrict(District district) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(EDIT_DISTRICT);
            preparedStatement.setString(1, String.valueOf(district.getPrice()));
            preparedStatement.setString(2, district.getDistrict());
            preparedStatement.setString(3, district.getTypeBuild());
            int i = preparedStatement.executeUpdate();
            if (i != 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    @Override
    public boolean deleteDistrict(District district) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(REMOVE_DISTRICT);
            preparedStatement.setString(1, district.getDistrict());
            preparedStatement.setString(2, district.getTypeBuild());
            preparedStatement.setString(3, String.valueOf(district.getPrice()));
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    @Override
    public boolean yearBuildAdd(YearBuild yearBuild) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_YEAR_BUILD);
            preparedStatement.setString(1, String.valueOf(yearBuild.getYearBuild()));
            preparedStatement.setString(2, String.valueOf(yearBuild.getCof()));
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    @Override
    public boolean yearBuildEdit(YearBuild yearBuild) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(EDIT_YEAR_BUILD);
            preparedStatement.setString(1, String.valueOf(yearBuild.getCof()));
            preparedStatement.setString(2, String.valueOf(yearBuild.getYearBuild()));
            int i = preparedStatement.executeUpdate();
            if (i != 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    @Override
    public boolean yearBuildDelete(YearBuild yearBuild) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(REMOVE_YEAR_BUILD);
            preparedStatement.setString(1, String.valueOf(yearBuild.getYearBuild()));
            preparedStatement.setString(2, String.valueOf(yearBuild.getCof()));
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    @Override
    public List<Realty> selectRealty() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Realty realty = null;
        List<Realty> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_REALTY);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                realty = new Realty();
                realty.setDistrict(resultSet.getString(1));
                realty.setTypeBuild(resultSet.getString(2));
                realty.setPriceFurniture(Double.parseDouble(resultSet.getString(3)));
                realty.setYearBuild(Integer.parseInt(resultSet.getString(4)));
                realty.setSquare(Double.parseDouble(resultSet.getString(5)));
                realty.setPriceRepair(Double.parseDouble(resultSet.getString(6)));
                realty.setNameUser(resultSet.getString(7));
                list.add(realty);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return list;
    }

    @Override
    public List<String> selectNameDistrict() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_NAME_DISTRICT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return list;
    }

    @Override
    public List<String> selectNameYearBuild() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_NAME_YEAR_BUILD);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return list;
    }

    @Override
    public double selectPrice(Realty realty) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Double price = 0.0;
        try {
            preparedStatement = connection.prepareStatement(SELECT_PRICE);
            preparedStatement.setString(1, realty.getDistrict());
            preparedStatement.setString(2, realty.getTypeBuild());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                price = Double.parseDouble(resultSet.getString("price"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return price;
    }

    @Override
    public double selectCof(Realty realty) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Double cof = 0.0;
        try {
            preparedStatement = connection.prepareStatement(SELECT_COF);
            preparedStatement.setString(1, String.valueOf(realty.getYearBuild()));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cof = Double.parseDouble(resultSet.getString("up_cof"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return cof;
    }

    @Override
    public void addRealty(Realty realty) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ID_PRICE);
            preparedStatement.setString(1, realty.getDistrict());
            preparedStatement.setString(2, realty.getTypeBuild());
            resultSet = preparedStatement.executeQuery();
            preparedStatement = connection.prepareStatement(ADD_REALTY);
            resultSet.next();
            preparedStatement.setString(1, resultSet.getString(1));
            preparedStatement.setString(2, String.valueOf(realty.getPriceFurniture()));
            preparedStatement.setString(3, String.valueOf(realty.getYearBuild()));
            preparedStatement.setString(4, String.valueOf(realty.getSquare()));
            preparedStatement.setString(5, String.valueOf(realty.getPriceRepair()));
            preparedStatement.setString(6, realty.getNameUser());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
