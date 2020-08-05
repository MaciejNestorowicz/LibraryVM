package pl.vm.library.service;

import pl.vm.library.to.ReservationTo;

/**
 * The Service which contains business logic for Reservation.
 */
public interface ReservationService {

    public ReservationTo makeReservation(ReservationTo reservationTo);

    public void extendReservation(Long reservationId);
}
