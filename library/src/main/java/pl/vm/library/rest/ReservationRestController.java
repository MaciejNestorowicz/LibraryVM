package pl.vm.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import pl.vm.library.service.ReservationService;
import pl.vm.library.to.ReservationTo;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservations")
public class ReservationRestController {

	@Autowired
	private ReservationService reservationService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ReservationTo makeReservation(@Valid @RequestBody ReservationTo reservationTo) {
		return reservationService.makeReservation(reservationTo);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{reservationId}")
	public void extendReservation(@PathVariable Long reservationId) {
		reservationService.extendReservation(reservationId);
	}
}
