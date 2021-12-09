import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoLocation_Test {

    // Preparation
    @Test
    void distance() {
        GeoLocation_ g1=new GeoLocation_(10,10,0);
        GeoLocation_ g2=new GeoLocation_(10,20,0);
        assertEquals(10,g1.distance(g2));
    }
}
