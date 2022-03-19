// Generated from C:/Users/Tom/Documents/FelSight/RelationalAPI/src/main/antlr4\RAStandard.g4 by ANTLR 4.9.2
package com.example.relationalapi.languages.ra.antlr4files.rastandard;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RAStandardParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RAStandardVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RAStandardParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(RAStandardParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Relation}
	 * labeled alternative in {@link RAStandardParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(RAStandardParser.RelationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JoinOperation}
	 * labeled alternative in {@link RAStandardParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinOperation(RAStandardParser.JoinOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Selection}
	 * labeled alternative in {@link RAStandardParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelection(RAStandardParser.SelectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryOperation}
	 * labeled alternative in {@link RAStandardParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperation(RAStandardParser.BinaryOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Projection}
	 * labeled alternative in {@link RAStandardParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProjection(RAStandardParser.ProjectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Rename}
	 * labeled alternative in {@link RAStandardParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRename(RAStandardParser.RenameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parentheses}
	 * labeled alternative in {@link RAStandardParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentheses(RAStandardParser.ParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RAStandardParser#thetaCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThetaCondition(RAStandardParser.ThetaConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RAStandardParser#columnList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnList(RAStandardParser.ColumnListContext ctx);
	/**
	 * Visit a parse tree produced by {@link RAStandardParser#renameList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameList(RAStandardParser.RenameListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FormulaParentheses}
	 * labeled alternative in {@link RAStandardParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormulaParentheses(RAStandardParser.FormulaParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryLogicalOperation}
	 * labeled alternative in {@link RAStandardParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryLogicalOperation(RAStandardParser.BinaryLogicalOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotOperation}
	 * labeled alternative in {@link RAStandardParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotOperation(RAStandardParser.NotOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Predicate}
	 * labeled alternative in {@link RAStandardParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(RAStandardParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleColumnSpecification}
	 * labeled alternative in {@link RAStandardParser#columnSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleColumnSpecification(RAStandardParser.SimpleColumnSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringTerm}
	 * labeled alternative in {@link RAStandardParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringTerm(RAStandardParser.StringTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberTerm}
	 * labeled alternative in {@link RAStandardParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberTerm(RAStandardParser.NumberTermContext ctx);
}