import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    private Calculator calculator;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void evaluatesExpression() {
        int sum = calculator.evaluate("1+2+3");
        assertEquals(6, sum);
        assertThat(sum, is(6));
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException() throws Exception{
        calculator.returnException();
    }

    @Test
    public void shouldReturnExceptionAndMessageIsError() throws Exception{

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("This is a error!");
        calculator.returnException();
    }
}
