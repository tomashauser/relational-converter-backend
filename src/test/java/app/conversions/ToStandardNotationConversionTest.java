package app.conversions;

import app.endpoint.ConverterService;
import app.endpoint.InputWrapper;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ToStandardNotationConversionTest {
    @Autowired
    private ConverterService converterService;

    @Test
    public void convertSimplifiedToStandard_selection() {
        InputWrapper inputWrapper = new InputWrapper("R(r = 2)", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(HttpStatus.OK, r.getStatusCode());
        assertEquals("\\sigma_{r = 2}(R)", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_emptySelection() {
        InputWrapper inputWrapper = new InputWrapper("R()", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(HttpStatus.OK, r.getStatusCode());
        assertEquals("\\sigma_{}(R)", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_projectionWithMultipleColumns() {
        InputWrapper inputWrapper = new InputWrapper("R[r1,r2]", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("\\pi_{r1,r2}(R)", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_projectionWithOneColumn() {
        InputWrapper inputWrapper = new InputWrapper("R[r1]", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("\\pi_{r1}(R)", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_projectionWithNoColumns_badRequest() {
        InputWrapper inputWrapper = new InputWrapper("R[]", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void convertSimplifiedToStandard_renameWithMultipleRenamings() {
        InputWrapper inputWrapper = new InputWrapper("R \\langle a \\rightarrow b, c \\rightarrow d \\rangle", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("\\rho_{b / a, d / c}(R)", r.getBody().trim());
    }

    @Test
    public void convertSimplifiedToStandard_renameWithSingleRenaming() {
        InputWrapper inputWrapper = new InputWrapper("R \\langle a \\rightarrow b \\rangle", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("\\rho_{b / a}(R)", r.getBody().trim());
    }

    @Test
    public void convertSimplifiedToStandard_renameWithEmptyArgs_badRequest() {
        InputWrapper inputWrapper = new InputWrapper("R \\langle \\rangle", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void convertSimplifiedToStandard_cartesianProduct() {
        InputWrapper inputWrapper = new InputWrapper("R \\times S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\times S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_difference() {
        InputWrapper inputWrapper = new InputWrapper("R \\setminus S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\setminus S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_union() {
        InputWrapper inputWrapper = new InputWrapper("R \\cup S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\cup S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_intersection() {
        InputWrapper inputWrapper = new InputWrapper("R \\cap S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\cap S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_division() {
        InputWrapper inputWrapper = new InputWrapper("R \\div S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\div S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_fullOuterJoin() {
        InputWrapper inputWrapper = new InputWrapper("R *_{F} S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\fullouterjoin S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_fullOuterjoinWithThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("(R *_{F} S)(x = y)", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("\\sigma_{x = y}(R \\fullouterjoin S)", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_fullOuterJoinWithEmptyThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("(R *_{F} S)()", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("\\sigma_{}(R \\fullouterjoin S)", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_leftOuterJoin() {
        InputWrapper inputWrapper = new InputWrapper("R *_{L} S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\leftouterjoin S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_leftOuterJoinWithThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("(R *_{L} S)(x = y)", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("\\sigma_{x = y}(R \\leftouterjoin S)", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_leftOuterJoinWithEmptyThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("(R *_{L} S)()", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("\\sigma_{}(R \\leftouterjoin S)", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_rightOuterJoin() {
        InputWrapper inputWrapper = new InputWrapper("R *_{R} S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\rightouterjoin S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_rightOuterJoinWithThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("(R *_{R} S)(x = y)", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("\\sigma_{x = y}(R \\rightouterjoin S)", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_rightOuterJoinWithEmptyThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("(R *_{R} S)()", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("\\sigma_{}(R \\rightouterjoin S)", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_naturalJoin() {
        InputWrapper inputWrapper = new InputWrapper("R * S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\bowtie S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_naturalJoinWithThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R[x=y]S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\bowtie_{x = y} S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_naturalJoinWithEmptyThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R * S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\bowtie S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_leftAntiJoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\triangleright S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\triangleright S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_leftAntiJoinWithThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("(R \\triangleright S)(x = y)", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("\\sigma_{x = y}(R \\triangleright S)", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_leftAntiJoinWithEmptyThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("(R \\triangleright S)()", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("\\sigma_{}(R \\triangleright S)", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_rightAntiJoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\triangleleft S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\triangleleft S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_rightAntiJoinWithThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("(R \\triangleleft S)(x = y)", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("\\sigma_{x = y}(R \\triangleleft S)", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_rightAntiJoinWithEmptyThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("(R \\triangleleft S)()", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("\\sigma_{}(R \\triangleleft S)", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_leftSemiJoin() {
        InputWrapper inputWrapper = new InputWrapper("R <* S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\ltimes S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_leftSemiJoinWithThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R \\langle x = y] S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\ltimes_{x = y} S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_leftSemiJoinWithEmptyThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R \\langle ] S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\ltimes S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_rightSemiJoin() {
        InputWrapper inputWrapper = new InputWrapper("R *> S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\rtimes S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_rightSemiJoinWithThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R [ x = y \\rangle S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\rtimes_{x = y} S", r.getBody());
    }

    @Test
    public void convertSimplifiedToStandard_rightSemiJoinWithEmptyThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R [ \\rangle S", null, false, false);
        ResponseEntity<String> r = converterService.convertSimplifiedToStandard(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\rtimes S", r.getBody());
    }
}