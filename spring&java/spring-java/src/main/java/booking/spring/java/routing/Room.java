package booking.spring.java.routing;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private long id;

    @NotBlank(message = "Rumstyp måste anges")
    @Pattern(regexp = "Enkelrum|Dubbelrum|Svit", message = "Rumstyp måste vara Enkelrum, Dubbelrum eller Svit")
    private String type;

    @NotNull(message = "Pris måste anges")
    private double price;
    private boolean occupied;

    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;

    public Room() {}

    public Room( String type, double price, boolean occupied) {
        this.type = type;
        this.price = price;
        this.occupied = occupied;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public long getId() {
        return id;
    }
}

interface RoomRepository extends JpaRepository<Room, Long> {
}