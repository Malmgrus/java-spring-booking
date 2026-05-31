package booking.spring.java.routing;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
public class Routing {
    private RoomRepository roomRepository;
    private BookingRepository bookingRepository;
    private Calculation calculation;

    public Routing(RoomRepository roomRepository, BookingRepository bookingRepository, Calculation calculation) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.calculation = calculation;
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @PostMapping("/createBooking")
    public ResponseEntity<String> createBooking(@Valid @RequestBody Booking booking) {

        calculation.updateRoomOccupancy(booking);

        calculation.calculateTotalPrice(booking);

        return ResponseEntity.ok("Bokning skapad. Totala priset är: " + booking.getTotalPrice());
    }

    @GetMapping("/bookings")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Booking> getBookings() {  // Endast admin
        return bookingRepository.findAll();
    }

    @PutMapping("/updateBooking/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable long id, @RequestBody Booking booking) {
        calculation.updateBooking(id, booking);
        return ResponseEntity.ok("Bokning uppdaterad.");
    }

    @DeleteMapping("/deleteBooking/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable long id) {
        calculation.removeBooking(id);
        return ResponseEntity.ok("Bokning borttagen.");
    }
}
