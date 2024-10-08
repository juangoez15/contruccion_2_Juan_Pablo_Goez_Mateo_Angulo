
package App.dao.interfaces.repository;

import App.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner,Long> {

    public Partner findById(long id);

    public boolean existsById(long id);

    
}
