package App.dao.interfaces.repository;

import App.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    // Agregar cualquier método adicional si es necesario
    Partner findById(long id);

    void delete(Partner partner);
}
