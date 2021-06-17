package nl.hu.bep.tom.helloku.resources;

import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class HelloResourceTest {

    @Test
    void hello() {
        Response r = new HelloResource().hello();
        assertEquals("Hello", r.getEntity());
    }
}
