package booking.spring.java.routing;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadRooms {

    @Bean
    CommandLineRunner loadData(RoomRepository roomRepository) {
        return args -> {

            roomRepository.save(new Room("Enkelrum", 800));
            roomRepository.save(new Room("Enkelrum", 800));
            roomRepository.save(new Room("Enkelrum", 800));
            roomRepository.save(new Room("Dubbelrum", 1200));
            roomRepository.save(new Room("Dubbelrum", 1200));
            roomRepository.save(new Room("Svit", 2000));

        };
    }
}