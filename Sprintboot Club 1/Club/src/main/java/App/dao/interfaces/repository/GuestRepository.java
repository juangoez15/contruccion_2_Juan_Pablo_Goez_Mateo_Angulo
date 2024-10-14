package App.dao.interfaces.repository;

import App.model.Guest;
import App.model.Person;
import App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    public Person findByUserId(User user);
    // Métodos personalizados, si son necesarios
}
