package carsharing.daos;

import carsharing.models.Company;

import java.util.List;

public interface CompanyDAO {
    List<Company> findAll();
    void add(Company company);
}
