package com.example.relationalapi.semanticcheking;

import com.example.relationalapi.languages.logicalexpression.ast.predicate.ComparisonPredicate;
import com.example.relationalapi.languages.logicalexpression.ast.terms.NumberTerm;
import com.example.relationalapi.languages.logicalexpression.ast.terms.ColumnSpecification;
import com.example.relationalapi.languages.ra.ast.binaryoperation.*;
import com.example.relationalapi.languages.ra.ast.expression.*;
import com.example.relationalapi.languages.ra.ast.joinoperation.*;
import com.example.relationalapi.languages.ra.visitors.SemanticRAChecker;
import com.example.relationalapi.utils.Header;
import com.example.relationalapi.utils.Schema;
import org.antlr.v4.runtime.misc.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SemanticRACheckerTest {
    private SemanticRAChecker semanticRAChecker;
    private ThetaCondition sampleConditionForR;
    private ThetaCondition sampleConditionForP;
    private ThetaCondition emptyCondition;

    @Before
    public void setUp() {
        Header pHeader = new Header(Arrays.asList("p1", "p2"));
        Header qHeader = new Header(Arrays.asList("p1", "p2"));
        Header rHeader = new Header(Arrays.asList("r1", "r2"));
        Header sHeader = new Header(Collections.singletonList("s1"));

        HashMap<String, Header> headerHashMap = new HashMap<>();

        headerHashMap.put("P", pHeader);
        headerHashMap.put("Q", qHeader);
        headerHashMap.put("R", rHeader);
        headerHashMap.put("S", sHeader);

        Schema schema = new Schema(headerHashMap);

        this.semanticRAChecker = new SemanticRAChecker(schema);

        this.sampleConditionForR = new ThetaCondition(new ComparisonPredicate(new ColumnSpecification("r1"), new NumberTerm(7), "="));
        this.sampleConditionForP = new ThetaCondition(new ComparisonPredicate(new ColumnSpecification("p1"), new NumberTerm(7), "="));
        this.emptyCondition = new ThetaCondition(null);
    }

    @Test
    public void selectionWithNonExistingRelation_error() {
        Selection selection = new Selection(this.sampleConditionForR, new Relation("W"));
        String res = this.semanticRAChecker.check(selection);
        assertEquals("Relation W isn't in the schema.Column r1 in selection is not in {}.", res);
    }

    @Test
    public void selectionWithEmptyCondition_ok() {
        Selection selection = new Selection(this.emptyCondition, new Relation("R"));
        String res = this.semanticRAChecker.check(selection);
        assertTrue(res.isEmpty());
    }

    @Test
    public void projectionWithNonExistingColumn_error() {
        Projection projection = new Projection(new ColumnList(Arrays.asList("a")), new Relation("R"));
        String res = this.semanticRAChecker.check(projection);
        assertEquals("You can only project on attributes that are in the inner expression.", res);
    }

    @Test
    public void projectionWithNonExistingRelation_error() {
        Projection projection = new Projection(new ColumnList(Arrays.asList("r1")), new Relation("W"));
        String res = this.semanticRAChecker.check(projection);
        assertEquals("Relation W isn't in the schema.You can only project on attributes that are in the inner expression.", res);
    }

    @Test
    public void renameWithNonExistingColumn_error() {
        RenameList renameList = new RenameList(Collections.singletonList(new Pair<>("w", "p")));
        Rename rename = new Rename(renameList, new Relation("R"));

        String res = this.semanticRAChecker.check(rename);
        assertEquals("Column w is not in the inner expression in renaming.", res);
    }

    @Test
    public void renameWithNonExistingRelation_error() {
        RenameList renameList = new RenameList(Collections.singletonList(new Pair<>("r1", "w")));
        Rename rename = new Rename(renameList, new Relation("W"));

        String res = this.semanticRAChecker.check(rename);
        assertEquals("Relation W isn't in the schema.Column r1 is not in the inner expression in renaming.", res);
    }

    @Test
    public void renameWithDuplicateColumnsResult_error() {
        RenameList renameList = new RenameList(Arrays.asList(new Pair<>("r1", "w"), new Pair<>("r2", "w")));
        Rename rename = new Rename(renameList, new Relation("R"));

        String res = this.semanticRAChecker.check(rename);
        assertEquals("Renaming creates duplicit columns.", res);
    }

    @Test
    public void cartesianProductWithNonDisjointAttributes_error() {
        CartesianProduct cartesianProduct = new CartesianProduct(new Relation("P"), new Relation("Q"));

        String res = this.semanticRAChecker.check(cartesianProduct);
        assertEquals("Cartesian product requires disjoint attributes.", res);
    }

    @Test
    public void differenceWithNonUnionCompatibleAttributes_error() {
        Difference difference = new Difference(new Relation("R"), new Relation("S"));

        String res = this.semanticRAChecker.check(difference);
        assertEquals("Set difference requires union-compatible relations.", res);
    }

    @Test
    public void unionWithNonUnionCompatibleAttributes_error() {
        Union union = new Union(new Relation("R"), new Relation("S"));

        String res = this.semanticRAChecker.check(union);
        assertEquals("Union requires union-compatible relations.", res);
    }

    @Test
    public void intersectionWithNonUnionCompatibleAttributes_error() {
        Intersection intersection = new Intersection(new Relation("R"), new Relation("S"));

        String res = this.semanticRAChecker.check(intersection);
        assertEquals("Intersection requires union-compatible relations.", res);
    }

    @Test
    public void divisionWithWrongAttributes_error() {
        Division division = new Division(new Relation("R"), new Relation("S"));

        String res = this.semanticRAChecker.check(division);
        assertEquals("Division requires that a right relation is a proper subset of the left one.", res);
    }

    @Test
    public void thetaNaturalJoinWithNonDisjointAttributes_error() {
        NaturalJoin naturalJoin = new NaturalJoin(new Relation("P"), new Relation("Q"), this.sampleConditionForP);

        String res = this.semanticRAChecker.check(naturalJoin);
        assertEquals("Theta join requires disjoint attributes.", res);
    }

    @Test
    public void thetaNaturalJoinWithInvalidThetaCondition_error() {
        NaturalJoin naturalJoin = new NaturalJoin(new Relation("P"), new Relation("S"), this.sampleConditionForR);

        String res = this.semanticRAChecker.check(naturalJoin);
        assertEquals("Column r1 is neither in {p1, p2} nor in [s1].", res);
    }

    @Test
    public void thetaLeftSemijoinJoinWithNonDisjointAttributes_error() {
        LeftSemijoin leftSemijoin = new LeftSemijoin(new Relation("P"), new Relation("Q"), this.sampleConditionForP);

        String res = this.semanticRAChecker.check(leftSemijoin);
        assertEquals("Theta join requires disjoint attributes.", res);
    }

    @Test
    public void thetaLeftSemijoinWithInvalidThetaCondition_error() {
        LeftSemijoin leftSemijoin = new LeftSemijoin(new Relation("P"), new Relation("S"), this.sampleConditionForR);

        String res = this.semanticRAChecker.check(leftSemijoin);
        assertEquals("Column r1 is neither in {p1, p2} nor in [s1].", res);
    }

    @Test
    public void thetaRightSemijoinJoinWithNonDisjointAttributes_error() {
        RightSemijoin rightSemijoin = new RightSemijoin(new Relation("P"), new Relation("Q"), this.sampleConditionForP);

        String res = this.semanticRAChecker.check(rightSemijoin);
        assertEquals("Theta join requires disjoint attributes.", res);
    }

    @Test
    public void thetaRightSemijoinWithInvalidThetaCondition_error() {
        RightSemijoin rightSemijoin = new RightSemijoin(new Relation("P"), new Relation("S"), this.sampleConditionForR);

        String res = this.semanticRAChecker.check(rightSemijoin);
        assertEquals("Column r1 is neither in {p1, p2} nor in [s1].", res);
    }

    @Test
    public void leftAntijoin_ok() {
        LeftAntijoin leftAntijoin = new LeftAntijoin(new Relation("R"), new Relation("S"), this.emptyCondition);

        String res = this.semanticRAChecker.check(leftAntijoin);
        assertTrue(res.isEmpty());
    }

    @Test
    public void rightAntijoin_ok() {
        RightAntijoin rightAntijoin = new RightAntijoin(new Relation("R"), new Relation("S"), this.emptyCondition);

        String res = this.semanticRAChecker.check(rightAntijoin);
        assertTrue(res.isEmpty());
    }

    @Test
    public void fullOuterJoin_ok() {
        FullOuterJoin fullOuterJoin = new FullOuterJoin(new Relation("R"), new Relation("S"), this.emptyCondition);

        String res = this.semanticRAChecker.check(fullOuterJoin);
        assertTrue(res.isEmpty());
    }

    @Test
    public void leftOuterJoin_ok() {
        LeftOuterJoin leftOuterJoin = new LeftOuterJoin(new Relation("R"), new Relation("S"), this.emptyCondition);

        String res = this.semanticRAChecker.check(leftOuterJoin);
        assertTrue(res.isEmpty());
    }

    @Test
    public void rightOuterJoin_ok() {
        RightOuterJoin rightOuterJoin = new RightOuterJoin(new Relation("R"), new Relation("S"), this.emptyCondition);

        String res = this.semanticRAChecker.check(rightOuterJoin);
        assertTrue(res.isEmpty());
    }

    @Test
    public void naturalJoin_ok() {
        NaturalJoin naturalJoin = new NaturalJoin(new Relation("R"), new Relation("S"), this.emptyCondition);

        String res = this.semanticRAChecker.check(naturalJoin);
        assertTrue(res.isEmpty());
    }

    @Test
    public void projection_ok() {
        Projection projection = new Projection(new ColumnList(Arrays.asList("r1")), new Relation("R"));

        String res = this.semanticRAChecker.check(projection);
        assertTrue(res.isEmpty());
    }

    @Test
    public void selection_ok() {
        Selection selection = new Selection(this.sampleConditionForR, new Relation("R"));

        String res = this.semanticRAChecker.check(selection);
        assertTrue(res.isEmpty());
    }

    @Test
    public void renaming_ok() {
        RenameList renameList = new RenameList(Collections.singletonList(new Pair<>("r1", "w")));
        Rename rename = new Rename(renameList, new Relation("R"));

        String res = this.semanticRAChecker.check(rename);
        assertTrue(res.isEmpty());
    }
}
