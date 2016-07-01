package ch.belsoft.hrassistant.company.dao;

import java.io.Serializable;
import java.util.List;

import ch.belsoft.hrassistant.dao.BaseDAO;
import ch.belsoft.hrassistant.dao.ICrudDAO;
import ch.belsoft.hrassistant.job.model.Company;

public class CompanyDAO extends BaseDAO implements ICrudDAO<Company, String>,
Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final String DESIGN_DOC = "company";
    private static final String VIEW_NAME = "companies";
    private static final int VIEW_LIMIT = 1000;
    
    public void create(Company t) {
        connectToService();
        super.handleResponse(cloudantService.saveDocument(t), t);
    }
    
    public void delete(Company t) {
        connectToService();
        cloudantService.removeDocument(t);
        
    }
    
    public Company read(String id) {
        connectToService();
        return (Company) cloudantService.findDocumentByID(Company.class, id);
    }
    
    @SuppressWarnings("unchecked")
    public List<Company> read() {
        connectToService();
        return (List<Company>) cloudantService.findAllDocumentFromView(Company.class,
                DESIGN_DOC, VIEW_NAME, "STRING", VIEW_LIMIT);
    }
    
    public void update(Company t) {
        connectToService();
        super.handleResponse(cloudantService.updateDocument(t), t);
    }
    
    
}