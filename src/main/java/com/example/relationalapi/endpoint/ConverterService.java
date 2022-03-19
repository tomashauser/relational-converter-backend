package com.example.relationalapi.endpoint;

import com.example.relationalapi.languages.logicalexpression.ast.Formula;
import com.example.relationalapi.languages.ra.antlr4files.rasimplified.RASimplifiedLexer;
import com.example.relationalapi.languages.ra.antlr4files.rasimplified.RASimplifiedParser;
import com.example.relationalapi.languages.ra.antlr4files.rastandard.RAStandardLexer;
import com.example.relationalapi.languages.ra.antlr4files.rastandard.RAStandardParser;
import com.example.relationalapi.languages.ra.antlr4visitors.SimplifiedRootVisitor;
import com.example.relationalapi.languages.ra.antlr4visitors.StandardRootVisitor;
import com.example.relationalapi.languages.ra.ast.RARoot;
import com.example.relationalapi.languages.ra.visitors.*;
import com.example.relationalapi.languages.ra.visitors.interfaces.RAVisitor;
import com.example.relationalapi.languages.trc.converters.TRCToPrenexFormConverter;
import com.example.relationalapi.languages.trc.converters.TRCToStringConverter;
import com.example.relationalapi.utils.Schema;
import com.example.relationalapi.utils.ThrowingErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {
    public static int MAX_RANDOM_QUERY_LENGTH = 10;

    private Pair<RARoot, String> parseRA(boolean semanticCheckingEnabled, Schema schema, Lexer lexer, Parser parser, AbstractParseTreeVisitor<RARoot> rootVisitor) {
        lexer.removeErrorListeners();
        parser.removeErrorListeners();

        ThrowingErrorListener throwingErrorListener = new ThrowingErrorListener();

        lexer.addErrorListener(throwingErrorListener);
        parser.addErrorListener(throwingErrorListener);

        RARoot root = null;

        String trace = "";

        try {
            ParseTree parserRoot = null;

            if (parser instanceof RAStandardParser) {
                parserRoot = ((RAStandardParser) parser).root();
            } else if (parser instanceof RASimplifiedParser) {
                parserRoot = ((RASimplifiedParser) parser).root();
            }

            root = rootVisitor.visit(parserRoot);
        } catch (Exception e) {
            trace = e.getMessage();
        }

        if (semanticCheckingEnabled && root != null) {
            SemanticRAChecker semanticRAChecker = new SemanticRAChecker(schema);
            String errors = semanticRAChecker.check(root.expression);

            if (!errors.isEmpty()) {
                trace += errors;
            }
        }

        return new Pair<>(root, trace);
    }

    private ResponseEntity<String> convertNotationsGeneral(boolean semanticCheckingEnabled, Schema schema, Lexer lexer, Parser parser, RAVisitor<String> notationConverter, AbstractParseTreeVisitor<RARoot> rootVisitor) {
        String res = "";

        Pair<RARoot, String> parseResult = this.parseRA(semanticCheckingEnabled, schema, lexer, parser, rootVisitor);

        RARoot root = parseResult.a;
        String trace = parseResult.b;

        if (!trace.isEmpty() || root == null) {
            return new ResponseEntity<>(trace, HttpStatus.BAD_REQUEST);
        }

        try {
            res = notationConverter.visit(root.expression);
        } catch (Exception e) {
            trace = e.getMessage();
        }

        if (!trace.isEmpty()) {
            return new ResponseEntity<>(trace, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    private ResponseEntity<String> convertToTRCGeneral(boolean prenexFormEnabled, Schema schema, Lexer lexer, Parser parser, AbstractParseTreeVisitor<RARoot> rootVisitor) {
        String res = "";

        Pair<RARoot, String> parseResult = this.parseRA(true, schema, lexer, parser, rootVisitor);

        RARoot root = parseResult.a;
        String trace = parseResult.b;

        if (!trace.isEmpty() || root == null) {
            return new ResponseEntity<>(trace, HttpStatus.BAD_REQUEST);
        }

        ForbiddenTRCOperatorsChecker forbiddenTRCOperatorsChecker = new ForbiddenTRCOperatorsChecker();

        String errors = forbiddenTRCOperatorsChecker.check(root.expression).trim();

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        RAToTRCConverter ra = new RAToTRCConverter(schema);

        Formula trcFormula = ra.visit(root.expression).b;

        TRCToStringConverter trcToStringConvertor = new TRCToStringConverter();

        if (prenexFormEnabled) {
            TRCToPrenexFormConverter trcToPrenexFormConverter = new TRCToPrenexFormConverter();
            trcFormula = trcToPrenexFormConverter.convert(trcFormula);
        }

        try {
            res = trcToStringConvertor.convert(trcFormula);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<String> convertStandardToSimplified(InputWrapper inputWrapper) {
        RAStandardLexer lexer = new RAStandardLexer(new ANTLRInputStream(inputWrapper.getExpression()));
        RAStandardParser parser = new RAStandardParser(new CommonTokenStream(lexer));

        return this.convertNotationsGeneral(inputWrapper.isSemanticChecking(), inputWrapper.getSchema(), lexer, parser, new ToSimplifiedNotationConverter(), new StandardRootVisitor());
    }

    public ResponseEntity<String> convertSimplifiedToStandard(InputWrapper inputWrapper) {
        RASimplifiedLexer lexer = new RASimplifiedLexer(new ANTLRInputStream(inputWrapper.getExpression()));
        RASimplifiedParser parser = new RASimplifiedParser(new CommonTokenStream(lexer));

        return this.convertNotationsGeneral(inputWrapper.isSemanticChecking(), inputWrapper.getSchema(), lexer, parser, new ToStandardNotationConverter(), new SimplifiedRootVisitor());
    }

    public ResponseEntity<String> convertStandardToTRC(InputWrapper inputWrapper) {
        RAStandardLexer lexer = new RAStandardLexer(new ANTLRInputStream(inputWrapper.getExpression()));
        RAStandardParser parser = new RAStandardParser(new CommonTokenStream(lexer));

        AbstractParseTreeVisitor<RARoot> visitor = new StandardRootVisitor();

        return this.convertToTRCGeneral(inputWrapper.isPrenexForm(), inputWrapper.getSchema(), lexer, parser, visitor);
    }

    public ResponseEntity<String> convertSimplifiedToTRC(InputWrapper inputWrapper) {
        RASimplifiedLexer lexer = new RASimplifiedLexer(new ANTLRInputStream(inputWrapper.getExpression()));
        RASimplifiedParser parser = new RASimplifiedParser(new CommonTokenStream(lexer));

        AbstractParseTreeVisitor<RARoot> visitor = new SimplifiedRootVisitor();

        return this.convertToTRCGeneral(inputWrapper.isPrenexForm(), inputWrapper.getSchema(), lexer, parser, visitor);
    }

    public ResponseEntity<String> getRandomQuery(int length) {
        if (length > MAX_RANDOM_QUERY_LENGTH) {
            return new ResponseEntity<>("Max length is " + MAX_RANDOM_QUERY_LENGTH, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(RandomExpressionGenerator.generateStandardStringExpr(3, 10), HttpStatus.OK);
    }
}
