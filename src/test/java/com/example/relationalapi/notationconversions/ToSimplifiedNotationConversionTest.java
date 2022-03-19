package com.example.relationalapi.notationconversions;

import com.example.relationalapi.endpoint.ConverterService;
import com.example.relationalapi.endpoint.InputWrapper;
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
public class ToSimplifiedNotationConversionTest {
    @Autowired
    private ConverterService converterService;

    @Test
    public void convertStandardToSimplified_selection() {
        InputWrapper inputWrapper = new InputWrapper("\\sigma_{r = 2}(R)", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(HttpStatus.OK, r.getStatusCode());
        assertEquals("R(r = 2)", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_emptySelection() {
        InputWrapper inputWrapper = new InputWrapper("\\sigma_{}(R)", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(HttpStatus.OK, r.getStatusCode());
        assertEquals("R()", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_projectionWithMultipleColumns() {
        InputWrapper inputWrapper = new InputWrapper("\\pi_{r1, r2}(R)", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R[r1,r2]", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_projectionWithOneColumn() {
        InputWrapper inputWrapper = new InputWrapper("\\pi_{r1}(R)", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R[r1]", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_projectionWithNoColumns_badRequest() {
        InputWrapper inputWrapper = new InputWrapper("\\pi_{}(R)", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void convertStandardToSimplified_renameWithMultipleRenamings() {
        InputWrapper inputWrapper = new InputWrapper("\\rho_{b / a, d / c}(R)", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\langle a \\rightarrow b, c \\rightarrow d \\rangle", r.getBody().trim());
    }

    @Test
    public void convertStandardToSimplified_renameWithSingleRenaming() {
        InputWrapper inputWrapper = new InputWrapper("\\rho_{b / a}(R)", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\langle a \\rightarrow b \\rangle", r.getBody().trim());
    }

    @Test
    public void convertStandardToSimplified_renameWithEmptyArgs_badRequest() {
        InputWrapper inputWrapper = new InputWrapper("\\rho_{}(R)", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void convertStandardToSimplified_cartesianProduct() {
        InputWrapper inputWrapper = new InputWrapper("R \\times S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\times S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_difference() {
        InputWrapper inputWrapper = new InputWrapper("R \\setminus S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\setminus S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_union() {
        InputWrapper inputWrapper = new InputWrapper("R \\cup S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\cup S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_intersection() {
        InputWrapper inputWrapper = new InputWrapper("R \\cap S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\cap S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_division() {
        InputWrapper inputWrapper = new InputWrapper("R \\div S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\div S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_fullOuterJoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\fullouterjoin S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R *_{F} S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_leftOuterJoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\leftouterjoin S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R *_{L} S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_rightOuterJoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\rightouterjoin S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R *_{R} S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_naturalJoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\bowtie S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R * S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_naturalJoinWithThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R \\bowtie_{x = y} S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R[x=y]S", r.getBody().replace(" ", ""));
    }

    @Test
    public void convertStandardToSimplified_naturalJoinWithEmptyThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R \\bowtie_{} S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R * S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_leftAntiJoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\triangleright S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\triangleright S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_rightAntiJoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\triangleleft S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\triangleleft S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_leftSemiJoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\ltimes S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R <* S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_leftSemiJoinWithThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R \\ltimes_{x = y} S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R \\langle x = y ] S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_leftSemiJoinWithEmptyThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R \\ltimes_{} S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R <* S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_rightSemiJoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\rtimes S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R *> S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_rightSemiJoinWithThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R \\rtimes_{x = y} S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R [ x = y \\rangle S", r.getBody());
    }

    @Test
    public void convertStandardToSimplified_rightSemiJoinWithEmptyThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R \\rtimes_{} S", null, false, false);
        ResponseEntity<String> r = converterService.convertStandardToSimplified(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        assertEquals("R *> S", r.getBody());
    }
}
