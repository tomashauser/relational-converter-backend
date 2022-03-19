// Generated from C:/Users/Tom/Documents/FelSight/RelationalAPI/src/main/antlr4\RASimplified.g4 by ANTLR 4.9.2
package com.example.relationalapi.languages.ra.antlr4files.rasimplified;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RASimplifiedLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, IDENTIFIER=9, 
		WS=10, BINARY_OPERATION_SYMBOL=11, JOIN_OPERATION_SYMBOL=12, NUMBER=13, 
		STRING_VALUE=14, COMPARISON_OP=15, BINARY_CONNECTIVE=16, UNARY_CONNECTIVE=17, 
		COLUMN_SPEC_SEPARATOR=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "IDENTIFIER", 
			"WS", "BINARY_OPERATION_SYMBOL", "JOIN_OPERATION_SYMBOL", "NUMBER", "STRING_VALUE", 
			"COMPARISON_OP", "BINARY_CONNECTIVE", "UNARY_CONNECTIVE", "COLUMN_SPEC_SEPARATOR"
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


	public RASimplifiedLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RASimplified.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u00f0\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\7\nP\n\n\f\n\16\nS\13\n\3\13\6\13"+
		"V\n\13\r\13\16\13W\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f"+
		"w\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00b7\n\r\3\16\6\16\u00ba"+
		"\n\16\r\16\16\16\u00bb\3\16\3\16\6\16\u00c0\n\16\r\16\16\16\u00c1\5\16"+
		"\u00c4\n\16\3\17\3\17\6\17\u00c8\n\17\r\17\16\17\u00c9\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20"+
		"\u00dc\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00e7\n"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\2\2\24\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\3"+
		"\2\5\4\2C\\c|\7\2//\62;C\\aac|\5\2\13\f\17\17\"\"\2\u010a\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\3\'\3\2\2\2\5)\3\2\2\2\7+\3\2\2\2\t-\3\2\2\2\13/\3\2\2\2\r\67\3\2\2"+
		"\2\17?\3\2\2\2\21A\3\2\2\2\23M\3\2\2\2\25U\3\2\2\2\27v\3\2\2\2\31\u00b6"+
		"\3\2\2\2\33\u00b9\3\2\2\2\35\u00c5\3\2\2\2\37\u00db\3\2\2\2!\u00e6\3\2"+
		"\2\2#\u00e8\3\2\2\2%\u00ee\3\2\2\2\'(\7]\2\2(\4\3\2\2\2)*\7_\2\2*\6\3"+
		"\2\2\2+,\7*\2\2,\b\3\2\2\2-.\7+\2\2.\n\3\2\2\2/\60\7^\2\2\60\61\7n\2\2"+
		"\61\62\7c\2\2\62\63\7p\2\2\63\64\7i\2\2\64\65\7n\2\2\65\66\7g\2\2\66\f"+
		"\3\2\2\2\678\7^\2\289\7t\2\29:\7c\2\2:;\7p\2\2;<\7i\2\2<=\7n\2\2=>\7g"+
		"\2\2>\16\3\2\2\2?@\7.\2\2@\20\3\2\2\2AB\7^\2\2BC\7t\2\2CD\7k\2\2DE\7i"+
		"\2\2EF\7j\2\2FG\7v\2\2GH\7c\2\2HI\7t\2\2IJ\7t\2\2JK\7q\2\2KL\7y\2\2L\22"+
		"\3\2\2\2MQ\t\2\2\2NP\t\3\2\2ON\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2R"+
		"\24\3\2\2\2SQ\3\2\2\2TV\t\4\2\2UT\3\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2\2"+
		"\2XY\3\2\2\2YZ\b\13\2\2Z\26\3\2\2\2[\\\7^\2\2\\]\7e\2\2]^\7c\2\2^w\7r"+
		"\2\2_`\7^\2\2`a\7e\2\2ab\7w\2\2bw\7r\2\2cd\7^\2\2de\7v\2\2ef\7k\2\2fg"+
		"\7o\2\2gh\7g\2\2hw\7u\2\2ij\7^\2\2jk\7u\2\2kl\7g\2\2lm\7v\2\2mn\7o\2\2"+
		"no\7k\2\2op\7p\2\2pq\7w\2\2qw\7u\2\2rs\7^\2\2st\7f\2\2tu\7k\2\2uw\7x\2"+
		"\2v[\3\2\2\2v_\3\2\2\2vc\3\2\2\2vi\3\2\2\2vr\3\2\2\2w\30\3\2\2\2x\u00b7"+
		"\7,\2\2yz\7>\2\2z\u00b7\7,\2\2{|\7,\2\2|\u00b7\7@\2\2}~\7,\2\2~\u00b7"+
		"\7N\2\2\177\u0080\7,\2\2\u0080\u0081\7a\2\2\u0081\u00b7\7N\2\2\u0082\u0083"+
		"\7,\2\2\u0083\u0084\7a\2\2\u0084\u0085\7}\2\2\u0085\u0086\7N\2\2\u0086"+
		"\u00b7\7\177\2\2\u0087\u0088\7,\2\2\u0088\u00b7\7T\2\2\u0089\u008a\7,"+
		"\2\2\u008a\u008b\7a\2\2\u008b\u00b7\7T\2\2\u008c\u008d\7,\2\2\u008d\u008e"+
		"\7a\2\2\u008e\u008f\7}\2\2\u008f\u0090\7T\2\2\u0090\u00b7\7\177\2\2\u0091"+
		"\u0092\7,\2\2\u0092\u00b7\7H\2\2\u0093\u0094\7,\2\2\u0094\u0095\7a\2\2"+
		"\u0095\u00b7\7H\2\2\u0096\u0097\7,\2\2\u0097\u0098\7a\2\2\u0098\u0099"+
		"\7}\2\2\u0099\u009a\7H\2\2\u009a\u00b7\7\177\2\2\u009b\u009c\7^\2\2\u009c"+
		"\u009d\7v\2\2\u009d\u009e\7t\2\2\u009e\u009f\7k\2\2\u009f\u00a0\7c\2\2"+
		"\u00a0\u00a1\7p\2\2\u00a1\u00a2\7i\2\2\u00a2\u00a3\7n\2\2\u00a3\u00a4"+
		"\7g\2\2\u00a4\u00a5\7n\2\2\u00a5\u00a6\7g\2\2\u00a6\u00a7\7h\2\2\u00a7"+
		"\u00b7\7v\2\2\u00a8\u00a9\7^\2\2\u00a9\u00aa\7v\2\2\u00aa\u00ab\7t\2\2"+
		"\u00ab\u00ac\7k\2\2\u00ac\u00ad\7c\2\2\u00ad\u00ae\7p\2\2\u00ae\u00af"+
		"\7i\2\2\u00af\u00b0\7n\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7t\2\2\u00b2"+
		"\u00b3\7k\2\2\u00b3\u00b4\7i\2\2\u00b4\u00b5\7j\2\2\u00b5\u00b7\7v\2\2"+
		"\u00b6x\3\2\2\2\u00b6y\3\2\2\2\u00b6{\3\2\2\2\u00b6}\3\2\2\2\u00b6\177"+
		"\3\2\2\2\u00b6\u0082\3\2\2\2\u00b6\u0087\3\2\2\2\u00b6\u0089\3\2\2\2\u00b6"+
		"\u008c\3\2\2\2\u00b6\u0091\3\2\2\2\u00b6\u0093\3\2\2\2\u00b6\u0096\3\2"+
		"\2\2\u00b6\u009b\3\2\2\2\u00b6\u00a8\3\2\2\2\u00b7\32\3\2\2\2\u00b8\u00ba"+
		"\4\62;\2\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\u00c3\3\2\2\2\u00bd\u00bf\7\60\2\2\u00be\u00c0\4"+
		"\62;\2\u00bf\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1"+
		"\u00c2\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00bd\3\2\2\2\u00c3\u00c4\3\2"+
		"\2\2\u00c4\34\3\2\2\2\u00c5\u00c7\7)\2\2\u00c6\u00c8\t\3\2\2\u00c7\u00c6"+
		"\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"\u00cb\3\2\2\2\u00cb\u00cc\7)\2\2\u00cc\36\3\2\2\2\u00cd\u00dc\4>@\2\u00ce"+
		"\u00cf\7^\2\2\u00cf\u00d0\7n\2\2\u00d0\u00d1\7p\2\2\u00d1\u00d2\7g\2\2"+
		"\u00d2\u00dc\7s\2\2\u00d3\u00d4\7^\2\2\u00d4\u00d5\7n\2\2\u00d5\u00d6"+
		"\7g\2\2\u00d6\u00dc\7s\2\2\u00d7\u00d8\7^\2\2\u00d8\u00d9\7i\2\2\u00d9"+
		"\u00da\7g\2\2\u00da\u00dc\7s\2\2\u00db\u00cd\3\2\2\2\u00db\u00ce\3\2\2"+
		"\2\u00db\u00d3\3\2\2\2\u00db\u00d7\3\2\2\2\u00dc \3\2\2\2\u00dd\u00de"+
		"\7^\2\2\u00de\u00df\7n\2\2\u00df\u00e0\7c\2\2\u00e0\u00e1\7p\2\2\u00e1"+
		"\u00e7\7f\2\2\u00e2\u00e3\7^\2\2\u00e3\u00e4\7n\2\2\u00e4\u00e5\7q\2\2"+
		"\u00e5\u00e7\7t\2\2\u00e6\u00dd\3\2\2\2\u00e6\u00e2\3\2\2\2\u00e7\"\3"+
		"\2\2\2\u00e8\u00e9\7^\2\2\u00e9\u00ea\7n\2\2\u00ea\u00eb\7p\2\2\u00eb"+
		"\u00ec\7q\2\2\u00ec\u00ed\7v\2\2\u00ed$\3\2\2\2\u00ee\u00ef\7\60\2\2\u00ef"+
		"&\3\2\2\2\r\2QWv\u00b6\u00bb\u00c1\u00c3\u00c9\u00db\u00e6\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}