package br.com.group9.desafio_quality.util;

import br.com.group9.desafio_quality.dto.RoomDTO;

import java.util.Arrays;
import java.util.List;

public class RoomCreator {
    static RoomDTO smallestRoom = new RoomDTO("Smallest Test Room", 2.0, 4.0, 8.0);
    static RoomDTO biggestRoom = new RoomDTO("Biggest Test Room", 3.0, 4.0, 12.0);

    public static List<RoomDTO> createRoomListToBeSaved() {
        RoomDTO room1 = new RoomDTO(smallestRoom.getRoomName(), smallestRoom.getRoomWidth(), smallestRoom.getRoomLength(), null);
        RoomDTO room2 = new RoomDTO(biggestRoom.getRoomName(), biggestRoom.getRoomWidth(), biggestRoom.getRoomLength(), null);
        return Arrays.asList(room1, room2);
    }

    public static List<RoomDTO> createValidRoomList() {
        RoomDTO room1 = new RoomDTO(smallestRoom.getRoomName(), smallestRoom.getRoomWidth(), smallestRoom.getRoomLength(), smallestRoom.getRoomM2());
        RoomDTO room2 = new RoomDTO(biggestRoom.getRoomName(), biggestRoom.getRoomWidth(), biggestRoom.getRoomLength(), biggestRoom.getRoomM2());
        return Arrays.asList(room1, room2);
    }
}
