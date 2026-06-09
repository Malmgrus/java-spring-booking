package booking.spring.java.routing;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import booking.spring.java.Exceptions.GuestException;

import java.util.List;

@Service
public class Calculation {

    private RoomRepository roomRepository;
    private BookingRepository bookingRepository;

    public Calculation(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }
    
    public ResponseEntity<String> calculateTotalPrice(Booking booking) {
        double totalPrice = 0.0;

        if (booking.getRoomType().toLowerCase().equals("enkelrum")) {
            if (booking.getNumberOfGuests() > 1) {
                throw new GuestException("Ett Enkelrum kan inte ha mer än 1 gäst");
            }
            totalPrice = booking.getNumberOfGuests() * 800.0;
        } else if (booking.getRoomType().toLowerCase().equals("dubbelrum")) {
            if (booking.getNumberOfGuests() > 2) {
                throw new GuestException("Ett dubbelrum kan inte ha mer än 3 gäster");
            }
            totalPrice = booking.getNumberOfGuests() * 1200.0;
        } else if (booking.getRoomType().toLowerCase().equals("svit")) {
            if (booking.getNumberOfGuests() > 3) {
                throw new GuestException("En svit kan inte ha mer än 3 gäster");
                }
            totalPrice = booking.getNumberOfGuests() * 2000.0;
        } else {
            throw new IllegalArgumentException("Ogiltig rumstyp: " + booking.getRoomType());
        }

        booking.setTotalPrice(totalPrice);
        bookingRepository.save(booking);
        return ResponseEntity.ok("Bokning skapad. Totala priset är: " + totalPrice);
    }

    public ResponseEntity<String> updateRoomOccupancy(Booking booking) {

        List<Room> allRooms = roomRepository.findAll();

        for (Room room : allRooms) {
            if (room.getType().equalsIgnoreCase(booking.getRoomType()) && !room.isOccupied()) {
                room.setOccupied(true);
                roomRepository.save(room);
                booking.setRoom(room);
                return ResponseEntity.ok("Rummet är nu upptaget.");
            }
        }

        throw new IllegalArgumentException("Kan inte uppdatera rummets status.");
    }

    public void updateBooking(long id, Booking booking) {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        if (existingBooking != null) {
            existingBooking.setGuestName(booking.getGuestName());
            if (booking.getRoomType().equals("Enkelrum") && booking.getNumberOfGuests() > 1) {
                throw new GuestException("Ett Enkelrum kan inte ha mer än 1 gäst");
            } else if (booking.getRoomType() == "Dubbelrum" && booking.getNumberOfGuests() > 2) {
                throw new GuestException("Ett dubbelrum kan inte ha mer än 3 gäster");
            } else if (booking.getRoomType() == "Svit" && booking.getNumberOfGuests() > 3) {
                throw new GuestException("En svit kan inte ha mer än 3 gäster");
            }
            existingBooking.setRoomType(booking.getRoomType());
            existingBooking.setNumberOfGuests(booking.getNumberOfGuests());
            calculateTotalPrice(existingBooking);
            bookingRepository.save(existingBooking);
        } else {
            throw new IllegalArgumentException("Bokning hittades inte.");
        }
    }

    public ResponseEntity<String> removeBooking(long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            bookingRepository.deleteById(id);
            return ResponseEntity.ok("Bokningen är nu borttagen.");
        }
        throw new IllegalArgumentException("Bokning hittades inte.");
    }
}