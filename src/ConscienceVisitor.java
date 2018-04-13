// Generated from Conscience.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ConscienceParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ConscienceVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ConscienceParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(ConscienceParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConscienceParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ConscienceParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConscienceParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(ConscienceParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConscienceParser#assignStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStat(ConscienceParser.AssignStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConscienceParser#mutateStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMutateStat(ConscienceParser.MutateStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConscienceParser#printStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStat(ConscienceParser.PrintStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConscienceParser#ifStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(ConscienceParser.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConscienceParser#whileStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(ConscienceParser.WhileStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConscienceParser#fnDefStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnDefStat(ConscienceParser.FnDefStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConscienceParser#exprStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStat(ConscienceParser.ExprStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConscienceParser#ids}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIds(ConscienceParser.IdsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConscienceParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(ConscienceParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link ConscienceParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpr(ConscienceParser.StringExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntegerExpr}
	 * labeled alternative in {@link ConscienceParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerExpr(ConscienceParser.IntegerExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link ConscienceParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(ConscienceParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDivModExpr}
	 * labeled alternative in {@link ConscienceParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivModExpr(ConscienceParser.MulDivModExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CompareExpr}
	 * labeled alternative in {@link ConscienceParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareExpr(ConscienceParser.CompareExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link ConscienceParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(ConscienceParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FnCallExpr}
	 * labeled alternative in {@link ConscienceParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnCallExpr(ConscienceParser.FnCallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link ConscienceParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(ConscienceParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanExpr}
	 * labeled alternative in {@link ConscienceParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpr(ConscienceParser.BooleanExprContext ctx);
}