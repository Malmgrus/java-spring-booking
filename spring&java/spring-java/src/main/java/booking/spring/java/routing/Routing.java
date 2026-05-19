package booking.spring.java.routing;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Routing {
    
    @GetMapping("/rooms")
    public String getRooms() {
        return "List of rooms";
    }

    @PostMapping("/createBooking")
    public void createBooking() {

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
