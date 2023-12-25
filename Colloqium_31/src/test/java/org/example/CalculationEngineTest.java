package org.example;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

public class CalculationEngineTest {
    @Mock
    private DBConnection dbConnection;
    @Mock
    private DBGateForCalculations dbGate;

    private CalculationEngine calculationEngine;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        calculationEngine = new CalculationEngine(dbGate);
    }

    @Test
    public void testCalc() {
        when(dbConnection.connect()).thenReturn(true);
        when(dbGate.processData()).thenReturn(10);

        int result = calculationEngine.calc();

        assertEquals(20, result);
    }
}
