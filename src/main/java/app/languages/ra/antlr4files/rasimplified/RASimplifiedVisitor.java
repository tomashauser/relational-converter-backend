// Generated from C:/Users/Tom/Documents/FelSight/RelationalAPI/src/main/antlr4\RASimplified.g4 by ANTLR 4.9.2
package app.languages.ra.antlr4files.rasimplified;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RASimplifiedParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RASimplifiedVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RASimplifiedParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(RASimplifiedParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Relation}
	 * labeled alternative in {@link RASimplifiedParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(RASimplifiedParser.RelationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JoinOperation}
	 * labeled alternative in {@link RASimplifiedParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinOperation(RASimplifiedParser.JoinOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryOperation}
	 * labeled alternative in {@link RASimplifiedParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperation(RASimplifiedParser.BinaryOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Selection}
	 * labeled alternative in {@link RASimplifiedParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelection(RASimplifiedParser.SelectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Projection}
	 * labeled alternative in {@link RASimplifiedParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProjection(RASimplifiedParser.ProjectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parentheses}
	 * labeled alternative in {@link RASimplifiedParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentheses(RASimplifiedParser.ParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Rename}
	 * labeled alternative in {@link RASimplifiedParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRename(RASimplifiedParser.RenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RASimplifiedParser#thetaCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThetaCondition(RASimplifiedParser.ThetaConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RASimplifiedParser#columnList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnList(RASimplifiedParser.ColumnListContext ctx);
	/**
	 * Visit a parse tree produced by {@link RASimplifiedParser#renameList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameList(RASimplifiedParser.RenameListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FormulaParentheses}
	 * labeled alternative in {@link RASimplifiedParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormulaParentheses(RASimplifiedParser.FormulaParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryLogicalOperation}
	 * labeled alternative in {@link RASimplifiedParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryLogicalOperation(RASimplifiedParser.BinaryLogicalOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotOperation}
	 * labeled alternative in {@link RASimplifiedParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotOperation(RASimplifiedParser.NotOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Predicate}
	 * labeled alternative in {@link RASimplifiedParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(RASimplifiedParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleColumnSpecification}
	 * labeled alternative in {@link RASimplifiedParser#columnSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleColumnSpecification(RASimplifiedParser.SimpleColumnSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringTerm}
	 * labeled alternative in {@link RASimplifiedParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringTerm(RASimplifiedParser.StringTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberTerm}
	 * labeled alternative in {@link RASimplifiedParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberTerm(RASimplifiedParser.NumberTermContext ctx);
}