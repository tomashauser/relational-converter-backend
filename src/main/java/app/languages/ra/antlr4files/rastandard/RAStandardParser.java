// Generated from C:/Users/Tom/Documents/FelSight/RelationalAPI/src/main/antlr4\RAStandard.g4 by ANTLR 4.9.2
package app.languages.ra.antlr4files.rastandard;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RAStandardParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		IDENTIFIER=10, WS=11, BINARY_OPERATION=12, REGULAR_JOIN=13, THETA_JOIN=14, 
		NUMBER=15, STRING_VALUE=16, COMPARISON_OP=17, BINARY_CONNECTIVE=18, UNARY_CONNECTIVE=19, 
		COLUMN_SPEC_SEPARATOR=20;
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
			null, "'\\pi'", "'_{'", "'}'", "'('", "')'", "'\\sigma'", "'\\rho'", 
			"','", "'/'", null, null, null, null, null, null, null, null, null, "'\\lnot'", 
			"'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "IDENTIFIER", 
			"WS", "BINARY_OPERATION", "REGULAR_JOIN", "THETA_JOIN", "NUMBER", "STRING_VALUE", 
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
	public String getGrammarFileName() { return "RAStandard.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RAStandardParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class RootContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(RAStandardParser.EOF, 0); }
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitRoot(this);
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
		public TerminalNode IDENTIFIER() { return getToken(RAStandardParser.IDENTIFIER, 0); }
		public RelationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitRelation(this);
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
		public TerminalNode REGULAR_JOIN() { return getToken(RAStandardParser.REGULAR_JOIN, 0); }
		public TerminalNode THETA_JOIN() { return getToken(RAStandardParser.THETA_JOIN, 0); }
		public ThetaConditionContext thetaCondition() {
			return getRuleContext(ThetaConditionContext.class,0);
		}
		public JoinOperationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitJoinOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SelectionContext extends ExprContext {
		public ThetaConditionContext thetaCondition() {
			return getRuleContext(ThetaConditionContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SelectionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitSelection(this);
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
		public TerminalNode BINARY_OPERATION() { return getToken(RAStandardParser.BINARY_OPERATION, 0); }
		public BinaryOperationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitBinaryOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ProjectionContext extends ExprContext {
		public ColumnListContext columnList() {
			return getRuleContext(ColumnListContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ProjectionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitProjection(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RenameContext extends ExprContext {
		public RenameListContext renameList() {
			return getRuleContext(RenameListContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public RenameContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitRename(this);
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
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitParentheses(this);
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
			setState(47);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				_localctx = new ProjectionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(20);
				match(T__0);
				setState(21);
				match(T__1);
				setState(22);
				columnList();
				setState(23);
				match(T__2);
				setState(24);
				match(T__3);
				setState(25);
				expr(0);
				setState(26);
				match(T__4);
				}
				break;
			case T__5:
				{
				_localctx = new SelectionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(28);
				match(T__5);
				setState(29);
				thetaCondition();
				setState(30);
				match(T__3);
				setState(31);
				expr(0);
				setState(32);
				match(T__4);
				}
				break;
			case T__6:
				{
				_localctx = new RenameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(34);
				match(T__6);
				setState(35);
				match(T__1);
				setState(36);
				renameList();
				setState(37);
				match(T__2);
				setState(38);
				match(T__3);
				setState(39);
				expr(0);
				setState(40);
				match(T__4);
				}
				break;
			case T__3:
				{
				_localctx = new ParenthesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(42);
				match(T__3);
				setState(43);
				expr(0);
				setState(44);
				match(T__4);
				}
				break;
			case IDENTIFIER:
				{
				_localctx = new RelationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(46);
				match(IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(62);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(60);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryOperationContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(49);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(50);
						match(BINARY_OPERATION);
						setState(51);
						expr(6);
						}
						break;
					case 2:
						{
						_localctx = new JoinOperationContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(52);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(53);
						match(REGULAR_JOIN);
						setState(54);
						expr(5);
						}
						break;
					case 3:
						{
						_localctx = new JoinOperationContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(55);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(56);
						match(THETA_JOIN);
						setState(57);
						thetaCondition();
						setState(58);
						expr(4);
						}
						break;
					}
					} 
				}
				setState(64);
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
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitThetaCondition(this);
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
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(65);
				match(T__1);
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << IDENTIFIER) | (1L << NUMBER) | (1L << STRING_VALUE) | (1L << UNARY_CONNECTIVE))) != 0)) {
					{
					setState(66);
					formula(0);
					}
				}

				setState(69);
				match(T__2);
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
		public List<TerminalNode> IDENTIFIER() { return getTokens(RAStandardParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(RAStandardParser.IDENTIFIER, i);
		}
		public ColumnListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitColumnList(this);
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
			setState(72);
			match(IDENTIFIER);
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(73);
				match(T__7);
				setState(74);
				match(IDENTIFIER);
				}
				}
				setState(79);
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
		public List<TerminalNode> IDENTIFIER() { return getTokens(RAStandardParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(RAStandardParser.IDENTIFIER, i);
		}
		public RenameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_renameList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitRenameList(this);
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
			setState(80);
			match(IDENTIFIER);
			setState(81);
			match(T__8);
			setState(82);
			match(IDENTIFIER);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(83);
				match(T__7);
				setState(84);
				match(IDENTIFIER);
				setState(85);
				match(T__8);
				setState(86);
				match(IDENTIFIER);
				}
				}
				setState(91);
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
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitFormulaParentheses(this);
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
		public TerminalNode BINARY_CONNECTIVE() { return getToken(RAStandardParser.BINARY_CONNECTIVE, 0); }
		public BinaryLogicalOperationContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitBinaryLogicalOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotOperationContext extends FormulaContext {
		public TerminalNode UNARY_CONNECTIVE() { return getToken(RAStandardParser.UNARY_CONNECTIVE, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public NotOperationContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitNotOperation(this);
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
		public TerminalNode COMPARISON_OP() { return getToken(RAStandardParser.COMPARISON_OP, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public PredicateContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitPredicate(this);
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
			setState(111);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				_localctx = new PredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(93);
				columnSpec();
				setState(94);
				match(COMPARISON_OP);
				setState(95);
				columnSpec();
				}
				break;
			case 2:
				{
				_localctx = new PredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(97);
				columnSpec();
				setState(98);
				match(COMPARISON_OP);
				setState(99);
				term();
				}
				break;
			case 3:
				{
				_localctx = new PredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(101);
				term();
				setState(102);
				match(COMPARISON_OP);
				setState(103);
				columnSpec();
				}
				break;
			case 4:
				{
				_localctx = new FormulaParenthesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(105);
				match(T__3);
				setState(106);
				formula(0);
				setState(107);
				match(T__4);
				}
				break;
			case 5:
				{
				_localctx = new NotOperationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(109);
				match(UNARY_CONNECTIVE);
				setState(110);
				formula(3);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(121);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(119);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryLogicalOperationContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(113);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(114);
						match(BINARY_CONNECTIVE);
						setState(115);
						formula(3);
						}
						break;
					case 2:
						{
						_localctx = new BinaryLogicalOperationContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(116);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(117);
						match(BINARY_CONNECTIVE);
						setState(118);
						formula(2);
						}
						break;
					}
					} 
				}
				setState(123);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
		public TerminalNode IDENTIFIER() { return getToken(RAStandardParser.IDENTIFIER, 0); }
		public SimpleColumnSpecificationContext(ColumnSpecContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitSimpleColumnSpecification(this);
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
			setState(124);
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
		public TerminalNode STRING_VALUE() { return getToken(RAStandardParser.STRING_VALUE, 0); }
		public StringTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitStringTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberTermContext extends TermContext {
		public TerminalNode NUMBER() { return getToken(RAStandardParser.NUMBER, 0); }
		public NumberTermContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAStandardVisitor ) return ((RAStandardVisitor<? extends T>)visitor).visitNumberTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_term);
		try {
			setState(128);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_VALUE:
				_localctx = new StringTermContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				match(STRING_VALUE);
				}
				break;
			case NUMBER:
				_localctx = new NumberTermContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
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
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean formula_sempred(FormulaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 2);
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26\u0085\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\62\n\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3?\n\3\f\3\16\3B\13\3\3\4\3\4\5\4F\n\4\3"+
		"\4\5\4I\n\4\3\5\3\5\3\5\7\5N\n\5\f\5\16\5Q\13\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\7\6Z\n\6\f\6\16\6]\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7r\n\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\7\7z\n\7\f\7\16\7}\13\7\3\b\3\b\3\t\3\t\5\t\u0083\n\t\3\t\2\4\4\f\n\2"+
		"\4\6\b\n\f\16\20\2\2\2\u008e\2\22\3\2\2\2\4\61\3\2\2\2\6H\3\2\2\2\bJ\3"+
		"\2\2\2\nR\3\2\2\2\fq\3\2\2\2\16~\3\2\2\2\20\u0082\3\2\2\2\22\23\5\4\3"+
		"\2\23\24\7\2\2\3\24\3\3\2\2\2\25\26\b\3\1\2\26\27\7\3\2\2\27\30\7\4\2"+
		"\2\30\31\5\b\5\2\31\32\7\5\2\2\32\33\7\6\2\2\33\34\5\4\3\2\34\35\7\7\2"+
		"\2\35\62\3\2\2\2\36\37\7\b\2\2\37 \5\6\4\2 !\7\6\2\2!\"\5\4\3\2\"#\7\7"+
		"\2\2#\62\3\2\2\2$%\7\t\2\2%&\7\4\2\2&\'\5\n\6\2\'(\7\5\2\2()\7\6\2\2)"+
		"*\5\4\3\2*+\7\7\2\2+\62\3\2\2\2,-\7\6\2\2-.\5\4\3\2./\7\7\2\2/\62\3\2"+
		"\2\2\60\62\7\f\2\2\61\25\3\2\2\2\61\36\3\2\2\2\61$\3\2\2\2\61,\3\2\2\2"+
		"\61\60\3\2\2\2\62@\3\2\2\2\63\64\f\7\2\2\64\65\7\16\2\2\65?\5\4\3\b\66"+
		"\67\f\6\2\2\678\7\17\2\28?\5\4\3\79:\f\5\2\2:;\7\20\2\2;<\5\6\4\2<=\5"+
		"\4\3\6=?\3\2\2\2>\63\3\2\2\2>\66\3\2\2\2>9\3\2\2\2?B\3\2\2\2@>\3\2\2\2"+
		"@A\3\2\2\2A\5\3\2\2\2B@\3\2\2\2CE\7\4\2\2DF\5\f\7\2ED\3\2\2\2EF\3\2\2"+
		"\2FG\3\2\2\2GI\7\5\2\2HC\3\2\2\2HI\3\2\2\2I\7\3\2\2\2JO\7\f\2\2KL\7\n"+
		"\2\2LN\7\f\2\2MK\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2P\t\3\2\2\2QO\3"+
		"\2\2\2RS\7\f\2\2ST\7\13\2\2T[\7\f\2\2UV\7\n\2\2VW\7\f\2\2WX\7\13\2\2X"+
		"Z\7\f\2\2YU\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\\13\3\2\2\2][\3\2"+
		"\2\2^_\b\7\1\2_`\5\16\b\2`a\7\23\2\2ab\5\16\b\2br\3\2\2\2cd\5\16\b\2d"+
		"e\7\23\2\2ef\5\20\t\2fr\3\2\2\2gh\5\20\t\2hi\7\23\2\2ij\5\16\b\2jr\3\2"+
		"\2\2kl\7\6\2\2lm\5\f\7\2mn\7\7\2\2nr\3\2\2\2op\7\25\2\2pr\5\f\7\5q^\3"+
		"\2\2\2qc\3\2\2\2qg\3\2\2\2qk\3\2\2\2qo\3\2\2\2r{\3\2\2\2st\f\4\2\2tu\7"+
		"\24\2\2uz\5\f\7\5vw\f\3\2\2wx\7\24\2\2xz\5\f\7\4ys\3\2\2\2yv\3\2\2\2z"+
		"}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|\r\3\2\2\2}{\3\2\2\2~\177\7\f\2\2\177\17"+
		"\3\2\2\2\u0080\u0083\7\22\2\2\u0081\u0083\7\21\2\2\u0082\u0080\3\2\2\2"+
		"\u0082\u0081\3\2\2\2\u0083\21\3\2\2\2\r\61>@EHO[qy{\u0082";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}