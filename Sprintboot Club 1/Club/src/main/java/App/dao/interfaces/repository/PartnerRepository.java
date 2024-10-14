package App.dao.interfaces.repository;

import App.dto.UserDto;
import App.model.Partner;
import App.model.Person;
import App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    // Agregar cualquier método adicional si es necesario
    Partner findById(long id);

    void delete(Partner partner);

    public Partner findByUserId(User user);
}
