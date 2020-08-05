package pl.vm.library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import pl.vm.library.entity.Book;
import pl.vm.library.entity.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.book = :searchedBook")
    public Reservation findByBook(@Param("searchedBook") Book book);

}
