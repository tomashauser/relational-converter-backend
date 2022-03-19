// Generated from C:/Users/Tom/Documents/FelSight/RelationalAPI/src/main/antlr4\RASimplified.g4 by ANTLR 4.9.2
package com.example.relationalapi.languages.ra.antlr4files.rasimplified;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RASimplifiedParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, IDENTIFIER=9, 
		WS=10, BINARY_OPERATION_SYMBOL=11, JOIN_OPERATION_SYMBOL=12, NUMBER=13, 
		STRING_VALUE=14, COMPARISON_OP=15, BINARY_CONNECTIVE=16, UNARY_CONNECTIVE=17, 
		COLUMN_SPEC_SEPARATOR=18;
	public static final int
		RULE_root = 0, RULE_expr = 1, RULE_thetaCondition = 2, RULE_columnList = 3, 
		RULE_renameList = 4, RULE_formula = 5, RULE_columnSpec = 6, RULE_term = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"root", "expr", "thetaCondition", "columnList", "renameList", "formula", 
			"columnSpec", "term"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "'('", "')'", "'\\langle'", "'\\rangle'", "','", 
			"'\\rightarrow'", null, null, null, null, null, null, null, null, "'\\lnot'", 
			"'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "IDENTIFIER", "WS", 
			"BINARY_OPERATION_SYMBOL", "JOIN_OPERATION_SYMBOL", "NUMBER", "STRING_VALUE", 
			"COMPARISON_OP", "BINARY_CONNECTIVE", "UNARY_CONNECTIVE", "COLUMN_SPEC_SEPARATOR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "RASimplified.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RASimplifiedParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class RootContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(RASimplifiedParser.EOF, 0); }
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			expr(0);
			setState(17);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RelationContext extends ExprContext {
		public TerminalNode IDENTIFIER() { return getToken(RASimplifiedParser.IDENTIFIER, 0); }
		public RelationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitRelation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JoinOperationContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode JOIN_OPERATION_SYMBOL() { return getToken(RASimplifiedParser.JOIN_OPERATION_SYMBOL, 0); }
		public ThetaConditionContext thetaCondition() {
			return getRuleContext(ThetaConditionContext.class,0);
		}
		public JoinOperationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitJoinOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryOperationContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode BINARY_OPERATION_SYMBOL() { return getToken(RASimplifiedParser.BINARY_OPERATION_SYMBOL, 0); }
		public BinaryOperationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitBinaryOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SelectionContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ThetaConditionContext thetaCondition() {
			return getRuleContext(ThetaConditionContext.class,0);
		}
		public SelectionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitSelection(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ProjectionContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ColumnListContext columnList() {
			return getRuleContext(ColumnListContext.class,0);
		}
		public ProjectionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitProjection(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesesContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParenthesesContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitParentheses(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RenameContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public RenameListContext renameList() {
			return getRuleContext(RenameListContext.class,0);
		}
		public RenameContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitRename(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				_localctx = new ParenthesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(20);
				match(T__2);
				setState(21);
				expr(0);
				setState(22);
				match(T__3);
				}
				break;
			case IDENTIFIER:
				{
				_localctx = new RelationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(24);
				match(IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(68);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(66);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryOperationContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(27);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(28);
						match(BINARY_OPERATION_SYMBOL);
						setState(29);
						expr(8);
						}
						break;
					case 2:
						{
						_localctx = new JoinOperationContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(30);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(31);
						match(JOIN_OPERATION_SYMBOL);
						setState(32);
						expr(7);
						}
						break;
					case 3:
						{
						_localctx = new JoinOperationContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(33);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(34);
						match(T__0);
						setState(35);
						thetaCondition();
						setState(36);
						match(T__1);
						setState(37);
						expr(6);
						}
						break;
					case 4:
						{
						_localctx = new JoinOperationContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(39);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(40);
						match(T__4);
						setState(41);
						thetaCondition();
						setState(42);
						match(T__1);
						setState(43);
						expr(5);
						}
						break;
					case 5:
						{
						_localctx = new JoinOperationContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(45);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(46);
						match(T__0);
						setState(47);
						thetaCondition();
						setState(48);
						match(T__5);
						setState(49);
						expr(4);
						}
						break;
					case 6:
						{
						_localctx = new ProjectionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(51);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(52);
						match(T__0);
						setState(53);
						columnList();
						setState(54);
						match(T__1);
						}
						break;
					case 7:
						{
						_localctx = new SelectionContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(56);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(57);
						match(T__2);
						setState(58);
						thetaCondition();
						setState(59);
						match(T__3);
						}
						break;
					case 8:
						{
						_localctx = new RenameContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(61);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(62);
						match(T__4);
						setState(63);
						renameList();
						setState(64);
						match(T__5);
						}
						break;
					}
					} 
				}
				setState(70);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ThetaConditionContext extends ParserRuleContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public ThetaConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thetaCondition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitThetaCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ThetaConditionContext thetaCondition() throws RecognitionException {
		ThetaConditionContext _localctx = new ThetaConditionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_thetaCondition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << IDENTIFIER) | (1L << NUMBER) | (1L << STRING_VALUE) | (1L << UNARY_CONNECTIVE))) != 0)) {
				{
				setState(71);
				formula(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnListContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(RASimplifiedParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(RASimplifiedParser.IDENTIFIER, i);
		}
		public ColumnListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitColumnList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnListContext columnList() throws RecognitionException {
		ColumnListContext _localctx = new ColumnListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_columnList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(IDENTIFIER);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(75);
				match(T__6);
				setState(76);
				match(IDENTIFIER);
				}
				}
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RenameListContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(RASimplifiedParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(RASimplifiedParser.IDENTIFIER, i);
		}
		public RenameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_renameList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitRenameList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RenameListContext renameList() throws RecognitionException {
		RenameListContext _localctx = new RenameListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_renameList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(IDENTIFIER);
			setState(83);
			match(T__7);
			setState(84);
			match(IDENTIFIER);
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(85);
				match(T__6);
				setState(86);
				match(IDENTIFIER);
				setState(87);
				match(T__7);
				setState(88);
				match(IDENTIFIER);
				}
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormulaContext extends ParserRuleContext {
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
	 
		public FormulaContext() { }
		public void copyFrom(FormulaContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FormulaParenthesesContext extends FormulaContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public FormulaParenthesesContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitFormulaParentheses(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryLogicalOperationContext extends FormulaContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public TerminalNode BINARY_CONNECTIVE() { return getToken(RASimplifiedParser.BINARY_CONNECTIVE, 0); }
		public BinaryLogicalOperationContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitBinaryLogicalOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotOperationContext extends FormulaContext {
		public TerminalNode UNARY_CONNECTIVE() { return getToken(RASimplifiedParser.UNARY_CONNECTIVE, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public NotOperationContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitNotOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PredicateContext extends FormulaContext {
		public List<ColumnSpecContext> columnSpec() {
			return getRuleContexts(ColumnSpecContext.class);
		}
		public ColumnSpecContext columnSpec(int i) {
			return getRuleContext(ColumnSpecContext.class,i);
		}
		public TerminalNode COMPARISON_OP() { return getToken(RASimplifiedParser.COMPARISON_OP, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public PredicateContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		return formula(0);
	}

	private FormulaContext formula(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FormulaContext _localctx = new FormulaContext(_ctx, _parentState);
		FormulaContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_formula, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				_localctx = new PredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(95);
				columnSpec();
				setState(96);
				match(COMPARISON_OP);
				setState(97);
				columnSpec();
				}
				break;
			case 2:
				{
				_localctx = new PredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(99);
				columnSpec();
				setState(100);
				match(COMPARISON_OP);
				setState(101);
				term();
				}
				break;
			case 3:
				{
				_localctx = new PredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(103);
				term();
				setState(104);
				match(COMPARISON_OP);
				setState(105);
				columnSpec();
				}
				break;
			case 4:
				{
				_localctx = new FormulaParenthesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(107);
				match(T__2);
				setState(108);
				formula(0);
				setState(109);
				match(T__3);
				}
				break;
			case 5:
				{
				_localctx = new NotOperationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(111);
				match(UNARY_CONNECTIVE);
				setState(112);
				formula(3);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(123);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(121);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryLogicalOperationContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(115);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(116);
						match(BINARY_CONNECTIVE);
						setState(117);
						formula(3);
						}
						break;
					case 2:
						{
						_localctx = new BinaryLogicalOperationContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(118);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(119);
						match(BINARY_CONNECTIVE);
						setState(120);
						formula(2);
						}
						break;
					}
					} 
				}
				setState(125);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ColumnSpecContext extends ParserRuleContext {
		public ColumnSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnSpec; }
	 
		public ColumnSpecContext() { }
		public void copyFrom(ColumnSpecContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SimpleColumnSpecificationContext extends ColumnSpecContext {
		public TerminalNode IDENTIFIER() { return getToken(RASimplifiedParser.IDENTIFIER, 0); }
		public SimpleColumnSpecificationContext(ColumnSpecContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitSimpleColumnSpecification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnSpecContext columnSpec() throws RecognitionException {
		ColumnSpecContext _localctx = new ColumnSpecContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_columnSpec);
		try {
			_localctx = new SimpleColumnSpecificationContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	 
		public TermContext() { }
		public void copyFrom(TermContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StringTermContext extends TermContext {
		public TerminalNode STRING_VALUE() { return getToken(RASimplifiedParser.STRING_VALUE, 0); }
		public StringTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitStringTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberTermContext extends TermContext {
		public TerminalNode NUMBER() { return getToken(RASimplifiedParser.NUMBER, 0); }
		public NumberTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RASimplifiedVisitor ) return ((RASimplifiedVisitor<? extends T>)visitor).visitNumberTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_term);
		try {
			setState(130);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_VALUE:
				_localctx = new StringTermContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				match(STRING_VALUE);
				}
				break;
			case NUMBER:
				_localctx = new NumberTermContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return expr_sempred((ExprContext)_localctx, predIndex);
		case 5:
			return formula_sempred((FormulaContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 10);
		case 6:
			return precpred(_ctx, 9);
		case 7:
			return precpred(_ctx, 8);
		}
		return true;
	}
	private boolean formula_sempred(FormulaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return precpred(_ctx, 2);
		case 9:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\24\u0087\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\5\3\34\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3E\n\3\f\3\16\3H"+
		"\13\3\3\4\5\4K\n\4\3\5\3\5\3\5\7\5P\n\5\f\5\16\5S\13\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\7\6\\\n\6\f\6\16\6_\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7t\n\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\7\7|\n\7\f\7\16\7\177\13\7\3\b\3\b\3\t\3\t\5\t\u0085\n\t\3\t"+
		"\2\4\4\f\n\2\4\6\b\n\f\16\20\2\2\2\u0091\2\22\3\2\2\2\4\33\3\2\2\2\6J"+
		"\3\2\2\2\bL\3\2\2\2\nT\3\2\2\2\fs\3\2\2\2\16\u0080\3\2\2\2\20\u0084\3"+
		"\2\2\2\22\23\5\4\3\2\23\24\7\2\2\3\24\3\3\2\2\2\25\26\b\3\1\2\26\27\7"+
		"\5\2\2\27\30\5\4\3\2\30\31\7\6\2\2\31\34\3\2\2\2\32\34\7\13\2\2\33\25"+
		"\3\2\2\2\33\32\3\2\2\2\34F\3\2\2\2\35\36\f\t\2\2\36\37\7\r\2\2\37E\5\4"+
		"\3\n !\f\b\2\2!\"\7\16\2\2\"E\5\4\3\t#$\f\7\2\2$%\7\3\2\2%&\5\6\4\2&\'"+
		"\7\4\2\2\'(\5\4\3\b(E\3\2\2\2)*\f\6\2\2*+\7\7\2\2+,\5\6\4\2,-\7\4\2\2"+
		"-.\5\4\3\7.E\3\2\2\2/\60\f\5\2\2\60\61\7\3\2\2\61\62\5\6\4\2\62\63\7\b"+
		"\2\2\63\64\5\4\3\6\64E\3\2\2\2\65\66\f\f\2\2\66\67\7\3\2\2\678\5\b\5\2"+
		"89\7\4\2\29E\3\2\2\2:;\f\13\2\2;<\7\5\2\2<=\5\6\4\2=>\7\6\2\2>E\3\2\2"+
		"\2?@\f\n\2\2@A\7\7\2\2AB\5\n\6\2BC\7\b\2\2CE\3\2\2\2D\35\3\2\2\2D \3\2"+
		"\2\2D#\3\2\2\2D)\3\2\2\2D/\3\2\2\2D\65\3\2\2\2D:\3\2\2\2D?\3\2\2\2EH\3"+
		"\2\2\2FD\3\2\2\2FG\3\2\2\2G\5\3\2\2\2HF\3\2\2\2IK\5\f\7\2JI\3\2\2\2JK"+
		"\3\2\2\2K\7\3\2\2\2LQ\7\13\2\2MN\7\t\2\2NP\7\13\2\2OM\3\2\2\2PS\3\2\2"+
		"\2QO\3\2\2\2QR\3\2\2\2R\t\3\2\2\2SQ\3\2\2\2TU\7\13\2\2UV\7\n\2\2V]\7\13"+
		"\2\2WX\7\t\2\2XY\7\13\2\2YZ\7\n\2\2Z\\\7\13\2\2[W\3\2\2\2\\_\3\2\2\2]"+
		"[\3\2\2\2]^\3\2\2\2^\13\3\2\2\2_]\3\2\2\2`a\b\7\1\2ab\5\16\b\2bc\7\21"+
		"\2\2cd\5\16\b\2dt\3\2\2\2ef\5\16\b\2fg\7\21\2\2gh\5\20\t\2ht\3\2\2\2i"+
		"j\5\20\t\2jk\7\21\2\2kl\5\16\b\2lt\3\2\2\2mn\7\5\2\2no\5\f\7\2op\7\6\2"+
		"\2pt\3\2\2\2qr\7\23\2\2rt\5\f\7\5s`\3\2\2\2se\3\2\2\2si\3\2\2\2sm\3\2"+
		"\2\2sq\3\2\2\2t}\3\2\2\2uv\f\4\2\2vw\7\22\2\2w|\5\f\7\5xy\f\3\2\2yz\7"+
		"\22\2\2z|\5\f\7\4{u\3\2\2\2{x\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2"+
		"~\r\3\2\2\2\177}\3\2\2\2\u0080\u0081\7\13\2\2\u0081\17\3\2\2\2\u0082\u0085"+
		"\7\20\2\2\u0083\u0085\7\17\2\2\u0084\u0082\3\2\2\2\u0084\u0083\3\2\2\2"+
		"\u0085\21\3\2\2\2\f\33DFJQ]s{}\u0084";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}