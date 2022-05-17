package app.conversions;

import app.endpoint.ConverterService;
import app.endpoint.InputWrapper;
import app.utils.Header;
import app.utils.Schema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ToTRCConversionTest {
    @Autowired
    private ConverterService converterService;

    private Schema schema;

    @BeforeAll
    public void setUp() {
        Header pHeader = new Header(Arrays.asList("p1", "p2"));
        Header qHeader = new Header(Arrays.asList("p1", "p2"));
        Header rHeader = new Header(Arrays.asList("r1", "r2"));
        Header sHeader = new Header(Arrays.asList("s1", "s2", "s3"));
        Header tHeader = new Header(Arrays.asList("r1", "t1"));
        Header uHeader = new Header(Collections.singletonList("r1"));

        HashMap<String, Header> headerHashMap = new HashMap<>();

        headerHashMap.put("P", pHeader);
        headerHashMap.put("Q", qHeader);
        headerHashMap.put("R", rHeader);
        headerHashMap.put("S", sHeader);
        headerHashMap.put("T", tHeader);
        headerHashMap.put("U", uHeader);

        this.schema = new Schema(headerHashMap);
    }

    @Test
    public void convertStandardToTRC_selection() {
        InputWrapper inputWrapper = new InputWrapper("\\sigma_{r1 = 2}(R)", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; R(t) \\land t.r1 = 2 \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_emptySelection() {
        InputWrapper inputWrapper = new InputWrapper("\\sigma_{}(R)", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; R(t) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_projection() {
        InputWrapper inputWrapper = new InputWrapper("\\pi_{s1, s2}(S)", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; \\exists p \\; (S(p) \\land t.s1 = p.s1 \\land t.s2 = p.s2) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_projectionOnAllColumns_returnsSubexpression() {
        InputWrapper inputWrapper = new InputWrapper("\\pi_{r1, r2}(R)", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; R(t) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_cartesianProduct() {
        InputWrapper inputWrapper = new InputWrapper("R \\times P", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; \\exists p \\; \\exists q \\; (R(p) \\land P(q) \\land t.r1 = p.r1 \\land t.r2 = p.r2 \\land t.p1 = q.p1 \\land t.p2 = q.p2) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_difference() {
        InputWrapper inputWrapper = new InputWrapper("P \\setminus Q", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; P(t) \\land \\lnot Q(t) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_union() {
        InputWrapper inputWrapper = new InputWrapper("P \\cup Q", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; P(t) \\lor Q(t) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_intersection() {
        InputWrapper inputWrapper = new InputWrapper("P \\cap Q", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; P(t) \\land Q(t) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_division() {
        InputWrapper inputWrapper = new InputWrapper("R \\div U", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; \\forall p \\; (\\lnot R(p) \\lor \\exists q \\; (U(q) \\land q.r1 = p.r1 \\land t.r2 = q.r2)) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_fullOuterJoin_returnsError() {
        InputWrapper inputWrapper = new InputWrapper("P \\fullouterjoin Q", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals("Can't convert full outer join into TRC.", r.getBody());
    }

    @Test
    public void convertStandardToTRC_leftOuterJoin_returnsError() {
        InputWrapper inputWrapper = new InputWrapper("P \\leftouterjoin Q", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals("Can't convert left outer join into TRC.", r.getBody());
    }

    @Test
    public void convertStandardToTRC_rightOuterJoin_returnsError() {
        InputWrapper inputWrapper = new InputWrapper("P \\rightouterjoin Q", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals("Can't convert right outer join into TRC.", r.getBody());
    }

    @Test
    public void convertStandardToTRC_naturalJoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\bowtie T", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; \\exists p \\; \\exists q \\; (R(p) \\land T(q) \\land p.r1 = q.r1 \\land t.r1 = p.r1 \\land t.r2 = p.r2 \\land t.t1 = q.t1) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_naturalJoinWithThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R \\bowtie_{r1 > 4} P", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; \\exists p \\; \\exists q \\; (R(p) \\land P(q) \\land t.r2 = p.r2 \\land t.r1 = p.r1 \\land t.p1 = q.p1 \\land t.p2 = q.p2 \\land t.r1 > 4) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_naturalJoinWithEmptyThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R \\bowtie_{} P", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; \\exists p \\; \\exists q \\; (R(p) \\land P(q) \\land t.r2 = p.r2 \\land t.r1 = p.r1 \\land t.p1 = q.p1 \\land t.p2 = q.p2) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_leftAntijoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\triangleright T", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; T(t) \\land \\lnot \\exists p \\; \\exists q \\; (R(p) \\land T(q) \\land p.r1 = q.r1 \\land t.r1 = p.r1 \\land t.r2 = p.r2) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_rightAntijoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\triangleleft T", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; R(t) \\land \\lnot \\exists p \\; \\exists q \\; (R(p) \\land T(q) \\land p.r1 = q.r1 \\land t.r1 = q.r1 \\land t.t1 = q.t1) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_leftSemijoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\ltimes T", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; \\exists p \\; \\exists q \\; (R(p) \\land T(q) \\land p.r1 = q.r1 \\land t.r1 = p.r1 \\land t.r2 = p.r2) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_leftSemijoin_withThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R \\ltimes_{p1 = 4} P", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; \\exists p \\; \\exists q \\; (R(p) \\land P(q) \\land t.r1 = p.r1 \\land t.r2 = p.r2 \\land t.p1 = 4) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_leftSemijoin_withEmptyThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R \\ltimes_{} P", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; \\exists p \\; \\exists q \\; (R(p) \\land P(q) \\land t.r1 = p.r1 \\land t.r2 = p.r2) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_rightSemijoin() {
        InputWrapper inputWrapper = new InputWrapper("R \\rtimes T", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; \\exists p \\; \\exists q \\; (R(p) \\land T(q) \\land p.r1 = q.r1 \\land t.r1 = q.r1 \\land t.t1 = q.t1) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_rightSemijoin_withThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R \\rtimes_{p1 = 4} P", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; \\exists p \\; \\exists q \\; (R(p) \\land P(q) \\land t.p1 = q.p1 \\land t.p2 = q.p2 \\land t.p1 = 4) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_rightSemijoin_withEmptyThetaCondition() {
        InputWrapper inputWrapper = new InputWrapper("R \\rtimes_{} P", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals("\\{ \\; t \\; | \\; \\exists p \\; \\exists q \\; (R(p) \\land P(q) \\land t.p1 = q.p1 \\land t.p2 = q.p2) \\; \\}", r.getBody());
    }

    @Test
    public void convertStandardToTRC_rename_returnsError() {
        InputWrapper inputWrapper = new InputWrapper("\\rho_{r1 / r1}(R)", this.schema, false, false);
        ResponseEntity<String> r = this.converterService.convertStandardToTRC(inputWrapper);

        assertEquals(r.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals("Can't convert renaming into TRC.", r.getBody());
    }
}
