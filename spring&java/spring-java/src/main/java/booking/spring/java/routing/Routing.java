package booking.spring.java.routing;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
public class Routing {
    private RoomRepository roomRepository;
    private BookingRepository bookingRepository;

    public Routing(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @PostMapping("/createBooking")
    public Booking createBooking(@Valid @RequestBody Booking booking) {
        System.out.println("booking: " + booking);
        return bookingRepository.save(booking);
    }

    @GetMapping("/bookings")
    @PreAuthorize("hasRole('admin')")
    public String getBookings() {  // Endast admin
        return "List of bookings";
    }

    @PutMapping("/updateBooking/{id}")
    public void updateBooking() {

    }

    @DeleteMapping("/deleteBooking/{id}")
    public void deleteBooking() {
        
    }
}
