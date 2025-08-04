package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    ProductoTest.class,
    PedidoTest.class,
    GestorPedidosTest.class,
    GestorPedidosMockTest.class
})

public class AllTestsSuite {



}
