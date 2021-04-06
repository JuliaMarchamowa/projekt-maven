package group.MainApp.tracking;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TrackingServiceImplTest {

    @Test
    public void testJsonSerialize() {
        Event e = new Event("0.0.0.0", "new 1", "/coaches/1", LocalDateTime.of(1, 2, 3, 4, 5, 6, 7));
        assertEquals("{\"userIp\":\"0.0.0.0\",\"actionType\":\"new 1\",\"link\":\"/coaches/1\",\"timestamp\":{\"date\":{\"year\":1,\"month\":2,\"day\":3},\"time\":{\"hour\":4,\"minute\":5,\"second\":6,\"nano\":7}}}", TrackingServiceImpl.jsonSerialize(e));
    }

    @Test
    public void testJsonDeserialize() {
        String json = "[{\"userIp\":\"0.0.0.0\",\"actionType\":\"new 1\",\"link\":\"/coaches/1\",\"timestamp\":{\"date\":{\"year\":1,\"month\":2,\"day\":3},\"time\":{\"hour\":4,\"minute\":5,\"second\":6,\"nano\":7}}}]";
        Event expected = new Event("0.0.0.0", "new 1", "/coaches/1", LocalDateTime.of(1, 2, 3, 4, 5, 6, 7));
        assertEquals(Collections.singletonList(expected), TrackingServiceImpl.jsonDeserialize(json));
    }

}