package app.conversions;

import app.endpoint.ConverterService;
import app.endpoint.InputWrapper;
import app.languages.ra.visitors.RandomExpressionGenerator;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NotationConversionEquivalenceTest {
    @Autowired
    private ConverterService converterService;

    private final int NUM_OF_TEST_CASES = 1000;

    @Test
    public void testConversionEquivalenceStandardToSimplifiedAndBack() {
        for (int i = 0; i < NUM_OF_TEST_CASES; i++) {
            int randomNumber = RandomExpressionGenerator.getRandomNumber(3, 10);
            String standardOriginal = RandomExpressionGenerator.generateStandardStringExpr(randomNumber);
            String simplified = this.converterService.convertStandardToSimplified(new InputWrapper(standardOriginal, null, false, false)).getBody();
            String standardNew = this.converterService.convertSimplifiedToStandard(new InputWrapper(simplified, null, false, false)).getBody();
            assertEquals(standardOriginal, standardNew);
        }
    }

    @Test
    public void testConversionEquivalenceSimplifiedToStandardAndBack() {
        for (int i = 0; i < NUM_OF_TEST_CASES; i++) {
            int randomNumber = RandomExpressionGenerator.getRandomNumber(3, 10);
            String simplifiedOriginal = RandomExpressionGenerator.generateSimplifiedStringExpr(randomNumber);
            String standard = this.converterService.convertSimplifiedToStandard(new InputWrapper(simplifiedOriginal, null, false, false)).getBody();
            String simplifiedNew = this.converterService.convertStandardToSimplified(new InputWrapper(standard, null, false, false)).getBody();
            assertEquals(simplifiedOriginal, simplifiedNew);
        }
    }
}
