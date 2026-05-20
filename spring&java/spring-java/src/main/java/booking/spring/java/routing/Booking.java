package booking.spring.java.routing;

import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Namn måste anges")
    private String guestName;

    @NotBlank(message = "Rumstyp måste anges")
    private String roomType;

    @Min(value = 1, message = "Antal gäster måste vara minst 1")
    @Max(value = 3, message = "Antal gäster kan inte vara mer än 3")
    private int numberOfGuests;
    private double totalPrice;

    public Booking() {}

    public Booking(String guestName, String roomType, int numberOfGuests, double totalPrice) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.numberOfGuests = numberOfGuests;
        this.totalPrice = totalPrice;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

interface BookingRepository extends JpaRepository<Booking, Long> {
}