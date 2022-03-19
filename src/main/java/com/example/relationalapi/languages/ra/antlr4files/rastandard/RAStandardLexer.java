// Generated from C:/Users/Tom/Documents/FelSight/RelationalAPI/src/main/antlr4\RAStandard.g4 by ANTLR 4.9.2
package com.example.relationalapi.languages.ra.antlr4files.rastandard;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RAStandardLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		IDENTIFIER=10, WS=11, BINARY_OPERATION=12, REGULAR_JOIN=13, THETA_JOIN=14, 
		NUMBER=15, STRING_VALUE=16, COMPARISON_OP=17, BINARY_CONNECTIVE=18, UNARY_CONNECTIVE=19, 
		COLUMN_SPEC_SEPARATOR=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"IDENTIFIER", "WS", "BINARY_OPERATION", "REGULAR_JOIN", "THETA_JOIN", 
			"NUMBER", "STRING_VALUE", "COMPARISON_OP", "BINARY_CONNECTIVE", "UNARY_CONNECTIVE", 
			"COLUMN_SPEC_SEPARATOR"
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


	public RAStandardLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RAStandard.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u010a\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\7\13K\n\13\f\13\16\13N\13\13\3\f\6\fQ\n\f\r\f\16"+
		"\fR\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\rr\n\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00ba"+
		"\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00d1\n\17\3\20\6\20\u00d4"+
		"\n\20\r\20\16\20\u00d5\3\20\3\20\6\20\u00da\n\20\r\20\16\20\u00db\5\20"+
		"\u00de\n\20\3\21\3\21\6\21\u00e2\n\21\r\21\16\21\u00e3\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22"+
		"\u00f6\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0101\n"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\2\2\26\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26\3\2\5\4\2C\\c|\7\2//\62;C\\aac|\5\2\13\f\17\17\"\"\2\u011d\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5/\3\2\2\2\7\62\3\2\2\2"+
		"\t\64\3\2\2\2\13\66\3\2\2\2\r8\3\2\2\2\17?\3\2\2\2\21D\3\2\2\2\23F\3\2"+
		"\2\2\25H\3\2\2\2\27P\3\2\2\2\31q\3\2\2\2\33\u00b9\3\2\2\2\35\u00d0\3\2"+
		"\2\2\37\u00d3\3\2\2\2!\u00df\3\2\2\2#\u00f5\3\2\2\2%\u0100\3\2\2\2\'\u0102"+
		"\3\2\2\2)\u0108\3\2\2\2+,\7^\2\2,-\7r\2\2-.\7k\2\2.\4\3\2\2\2/\60\7a\2"+
		"\2\60\61\7}\2\2\61\6\3\2\2\2\62\63\7\177\2\2\63\b\3\2\2\2\64\65\7*\2\2"+
		"\65\n\3\2\2\2\66\67\7+\2\2\67\f\3\2\2\289\7^\2\29:\7u\2\2:;\7k\2\2;<\7"+
		"i\2\2<=\7o\2\2=>\7c\2\2>\16\3\2\2\2?@\7^\2\2@A\7t\2\2AB\7j\2\2BC\7q\2"+
		"\2C\20\3\2\2\2DE\7.\2\2E\22\3\2\2\2FG\7\61\2\2G\24\3\2\2\2HL\t\2\2\2I"+
		"K\t\3\2\2JI\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2M\26\3\2\2\2NL\3\2\2"+
		"\2OQ\t\4\2\2PO\3\2\2\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2ST\3\2\2\2TU\b\f\2"+
		"\2U\30\3\2\2\2VW\7^\2\2WX\7e\2\2XY\7c\2\2Yr\7r\2\2Z[\7^\2\2[\\\7e\2\2"+
		"\\]\7w\2\2]r\7r\2\2^_\7^\2\2_`\7v\2\2`a\7k\2\2ab\7o\2\2bc\7g\2\2cr\7u"+
		"\2\2de\7^\2\2ef\7u\2\2fg\7g\2\2gh\7v\2\2hi\7o\2\2ij\7k\2\2jk\7p\2\2kl"+
		"\7w\2\2lr\7u\2\2mn\7^\2\2no\7f\2\2op\7k\2\2pr\7x\2\2qV\3\2\2\2qZ\3\2\2"+
		"\2q^\3\2\2\2qd\3\2\2\2qm\3\2\2\2r\32\3\2\2\2st\7^\2\2tu\7v\2\2uv\7t\2"+
		"\2vw\7k\2\2wx\7c\2\2xy\7p\2\2yz\7i\2\2z{\7n\2\2{|\7g\2\2|}\7n\2\2}~\7"+
		"g\2\2~\177\7h\2\2\177\u00ba\7v\2\2\u0080\u0081\7^\2\2\u0081\u0082\7v\2"+
		"\2\u0082\u0083\7t\2\2\u0083\u0084\7k\2\2\u0084\u0085\7c\2\2\u0085\u0086"+
		"\7p\2\2\u0086\u0087\7i\2\2\u0087\u0088\7n\2\2\u0088\u0089\7g\2\2\u0089"+
		"\u008a\7t\2\2\u008a\u008b\7k\2\2\u008b\u008c\7i\2\2\u008c\u008d\7j\2\2"+
		"\u008d\u00ba\7v\2\2\u008e\u008f\7^\2\2\u008f\u0090\7n\2\2\u0090\u0091"+
		"\7g\2\2\u0091\u0092\7h\2\2\u0092\u0093\7v\2\2\u0093\u0094\7q\2\2\u0094"+
		"\u0095\7w\2\2\u0095\u0096\7v\2\2\u0096\u0097\7g\2\2\u0097\u0098\7t\2\2"+
		"\u0098\u0099\7l\2\2\u0099\u009a\7q\2\2\u009a\u009b\7k\2\2\u009b\u00ba"+
		"\7p\2\2\u009c\u009d\7^\2\2\u009d\u009e\7t\2\2\u009e\u009f\7k\2\2\u009f"+
		"\u00a0\7i\2\2\u00a0\u00a1\7j\2\2\u00a1\u00a2\7v\2\2\u00a2\u00a3\7q\2\2"+
		"\u00a3\u00a4\7w\2\2\u00a4\u00a5\7v\2\2\u00a5\u00a6\7g\2\2\u00a6\u00a7"+
		"\7t\2\2\u00a7\u00a8\7l\2\2\u00a8\u00a9\7q\2\2\u00a9\u00aa\7k\2\2\u00aa"+
		"\u00ba\7p\2\2\u00ab\u00ac\7^\2\2\u00ac\u00ad\7h\2\2\u00ad\u00ae\7w\2\2"+
		"\u00ae\u00af\7n\2\2\u00af\u00b0\7n\2\2\u00b0\u00b1\7q\2\2\u00b1\u00b2"+
		"\7w\2\2\u00b2\u00b3\7v\2\2\u00b3\u00b4\7g\2\2\u00b4\u00b5\7t\2\2\u00b5"+
		"\u00b6\7l\2\2\u00b6\u00b7\7q\2\2\u00b7\u00b8\7k\2\2\u00b8\u00ba\7p\2\2"+
		"\u00b9s\3\2\2\2\u00b9\u0080\3\2\2\2\u00b9\u008e\3\2\2\2\u00b9\u009c\3"+
		"\2\2\2\u00b9\u00ab\3\2\2\2\u00ba\34\3\2\2\2\u00bb\u00bc\7^\2\2\u00bc\u00bd"+
		"\7d\2\2\u00bd\u00be\7q\2\2\u00be\u00bf\7y\2\2\u00bf\u00c0\7v\2\2\u00c0"+
		"\u00c1\7k\2\2\u00c1\u00d1\7g\2\2\u00c2\u00c3\7^\2\2\u00c3\u00c4\7n\2\2"+
		"\u00c4\u00c5\7v\2\2\u00c5\u00c6\7k\2\2\u00c6\u00c7\7o\2\2\u00c7\u00c8"+
		"\7g\2\2\u00c8\u00d1\7u\2\2\u00c9\u00ca\7^\2\2\u00ca\u00cb\7t\2\2\u00cb"+
		"\u00cc\7v\2\2\u00cc\u00cd\7k\2\2\u00cd\u00ce\7o\2\2\u00ce\u00cf\7g\2\2"+
		"\u00cf\u00d1\7u\2\2\u00d0\u00bb\3\2\2\2\u00d0\u00c2\3\2\2\2\u00d0\u00c9"+
		"\3\2\2\2\u00d1\36\3\2\2\2\u00d2\u00d4\4\62;\2\u00d3\u00d2\3\2\2\2\u00d4"+
		"\u00d5\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00dd\3\2"+
		"\2\2\u00d7\u00d9\7\60\2\2\u00d8\u00da\4\62;\2\u00d9\u00d8\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00de\3\2"+
		"\2\2\u00dd\u00d7\3\2\2\2\u00dd\u00de\3\2\2\2\u00de \3\2\2\2\u00df\u00e1"+
		"\7)\2\2\u00e0\u00e2\t\3\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3"+
		"\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\7)"+
		"\2\2\u00e6\"\3\2\2\2\u00e7\u00f6\4>@\2\u00e8\u00e9\7^\2\2\u00e9\u00ea"+
		"\7n\2\2\u00ea\u00eb\7p\2\2\u00eb\u00ec\7g\2\2\u00ec\u00f6\7s\2\2\u00ed"+
		"\u00ee\7^\2\2\u00ee\u00ef\7n\2\2\u00ef\u00f0\7g\2\2\u00f0\u00f6\7s\2\2"+
		"\u00f1\u00f2\7^\2\2\u00f2\u00f3\7i\2\2\u00f3\u00f4\7g\2\2\u00f4\u00f6"+
		"\7s\2\2\u00f5\u00e7\3\2\2\2\u00f5\u00e8\3\2\2\2\u00f5\u00ed\3\2\2\2\u00f5"+
		"\u00f1\3\2\2\2\u00f6$\3\2\2\2\u00f7\u00f8\7^\2\2\u00f8\u00f9\7n\2\2\u00f9"+
		"\u00fa\7c\2\2\u00fa\u00fb\7p\2\2\u00fb\u0101\7f\2\2\u00fc\u00fd\7^\2\2"+
		"\u00fd\u00fe\7n\2\2\u00fe\u00ff\7q\2\2\u00ff\u0101\7t\2\2\u0100\u00f7"+
		"\3\2\2\2\u0100\u00fc\3\2\2\2\u0101&\3\2\2\2\u0102\u0103\7^\2\2\u0103\u0104"+
		"\7n\2\2\u0104\u0105\7p\2\2\u0105\u0106\7q\2\2\u0106\u0107\7v\2\2\u0107"+
		"(\3\2\2\2\u0108\u0109\7\60\2\2\u0109*\3\2\2\2\16\2LRq\u00b9\u00d0\u00d5"+
		"\u00db\u00dd\u00e3\u00f5\u0100\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}