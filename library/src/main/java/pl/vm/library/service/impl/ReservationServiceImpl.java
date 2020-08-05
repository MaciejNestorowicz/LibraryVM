package pl.vm.library.service.impl;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.vm.library.entity.Book;
import pl.vm.library.entity.Reservation;
import pl.vm.library.exception.ParameterValidationException;
import pl.vm.library.repository.ReservationRepository;
import pl.vm.library.service.BookService;
import pl.vm.library.service.ReservationService;
import pl.vm.library.service.UserService;
import pl.vm.library.to.ReservationTo;
import pl.vm.library.to.UserTo;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	private ModelMapper mapper = new ModelMapper();

	@Override
	public ReservationTo makeReservation(ReservationTo reservationTo) {
		bookService.validateIfBookExist(reservationTo.getBookTo().getId());
		validateIfBookIsReserved(mapper.map(reservationTo.getBookTo(), Book.class));

		UserTo userTo = userService.findById(reservationTo.getUserTo().getId());
		userTo.getReservations().add(reservationTo);

		Reservation reservation = mapper.map(reservationTo, Reservation.class);
		Reservation savedReservation = reservationRepository.save(reservation);

		return mapper.map(savedReservation, ReservationTo.class);
	}

	@Override
	public void extendReservation(Long reservationId) {
		Optional<Reservation> reservation = reservationRepository.findById(reservationId);
		if (reservation.isPresent()) {
			reservation.get().setToDate(setToReservationDate(reservation.get().getToDate()));
			reservationRepository.save(reservation.get());
		}
		else throw new EntityNotFoundException("The Entity with the given ID was not found.");
	}

	public void validateIfBookIsReserved(Book book) {
		Reservation reservation = reservationRepository.findByBook(book);
		if (reservation != null) throw new ParameterValidationException("This book is currently reserved.");
	}

	public Date setToReservationDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 3);
		return calendar.getTime();
	}
}
