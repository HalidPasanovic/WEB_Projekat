package Service;

import java.util.List;
import Model.PromoCode;
import Repository.PromoCodeRepository;
import Repository.Interfaces.IPromoCodeRepository;
import Service.Interfaces.IPromoCodeService;

public class PromoCodeService implements IPromoCodeService{

    private IPromoCodeRepository repository;

    public PromoCodeService(String contextPath) {
        repository = PromoCodeRepository.getInstance(contextPath);
    }

    @Override
    public void Create(PromoCode element) throws Exception {
        repository.Create(element);
    }

    @Override
    public PromoCode Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(PromoCode element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public void DeletePhysically(int id) throws Exception {
        repository.DeletePhysically(id);
    }

    @Override
    public List<PromoCode> GetAll() {
        return repository.GetAll();
    }

    @Override
    public List<PromoCode> GetAllWithLogicalyDeleted() {
        return repository.GetAllWithLogicalyDeleted();
    }
    
}
