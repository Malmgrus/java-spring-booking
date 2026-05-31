package booking.spring.java.routing;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;

    private double totalPrice;

    public Booking() {}

    public Booking(String guestName, String roomType, int numberOfGuests) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.numberOfGuests = numberOfGuests;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByRoomId(long roomId);
}