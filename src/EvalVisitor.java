import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends ConscienceBaseVisitor<ConValue> {
    private Scope scope;

    public EvalVisitor(Scope scope) {
        this.scope = scope;
    }

    @Override
    public ConValue visitFile(ConscienceParser.FileContext ctx) {
        visit(ctx.block());

        return ConValue.VOID;
    }

    @Override
    public ConValue visitBlock(ConscienceParser.BlockContext ctx) {
        scope = new Scope(scope);
        ctx.stat().forEach(stat -> visit(stat));
        scope = scope.getParent();

        return ConValue.VOID;
    }

    @Override
    public ConValue visitAssignStat(ConscienceParser.AssignStatContext ctx) {
        String var = ctx.ID().getText();
        ConValue value = visit(ctx.expr());
        scope.assign(var, value);

        return ConValue.VOID;
    }

    @Override
    public ConValue visitMutateStat(ConscienceParser.MutateStatContext ctx) {
        String var = ctx.ID().getText();
        ConValue value = visit(ctx.expr());
        scope.mutate(var, value);

        return ConValue.VOID;
    }

    @Override
    public ConValue visitPrintStat(ConscienceParser.PrintStatContext ctx) {
        ConValue value = visit(ctx.expr());
        System.out.println(value);

        return ConValue.VOID;
    }

    @Override
    public ConValue visitIfStat(ConscienceParser.IfStatContext ctx) {
        if (visit(ctx.expr()).asBoolean()) {
            visit(ctx.block());
        }

        return ConValue.VOID;
    }

    @Override
    public ConValue visitWhileStat(ConscienceParser.WhileStatContext ctx) {
        while (visit(ctx.expr()).asBoolean()) {
            visit(ctx.block());
        }

        return ConValue.VOID;
    }

    @Override
    public ConValue visitMulDivModExpr(ConscienceParser.MulDivModExprContext ctx) {
        Integer left = visit(ctx.expr(0)).asInteger();
        Integer right = visit(ctx.expr(1)).asInteger();

        switch (ctx.op.getText()) {
            case "*":
                return new ConValue(left * right, Type.INTEGER);
            case "/":
                return new ConValue(left / right, Type.INTEGER);
            case "%":
                return new ConValue(left % right, Type.INTEGER);
        }

        throw new RuntimeException("undefined operator: " + ctx.op.getText());
    }

    @Override
    public ConValue visitAddSubExpr(ConscienceParser.AddSubExprContext ctx) {
        switch (visit(ctx.expr(0)).getType()) {
            case INTEGER:
                Integer left = visit(ctx.expr(0)).asInteger();
                Integer right = visit(ctx.expr(1)).asInteger();

                switch (ctx.op.getText()) {
                    case "+":
                        return new ConValue(left + right, Type.INTEGER);
                    case "-":
                        return new ConValue(left - right, Type.INTEGER);
                }
            case STRING:
                String leftStr = visit(ctx.expr(0)).asString();
                String rightStr = visit(ctx.expr(1)).asString();

                switch (ctx.op.getText()) {
                    case "+":
                        return new ConValue(leftStr + rightStr, Type.STRING);
                }
        }

        throw new RuntimeException("undefined operator: " + ctx.op.getText());
    }

    @Override
    public ConValue visitCompareExpr(ConscienceParser.CompareExprContext ctx) {
        ConValue left = visit(ctx.expr(0));
        ConValue right = visit(ctx.expr(1));

        switch (ctx.op.getText()) {
            case ">":
                return new ConValue(left.compareTo(right) > 0, Type.BOOLEAN);
            case "<":
                return new ConValue(left.compareTo(right) < 0, Type.BOOLEAN);
            case ">=":
                return new ConValue(left.compareTo(right) >= 0, Type.BOOLEAN);
            case "<=":
                return new ConValue(left.compareTo(right) <= 0, Type.BOOLEAN);
            case "==":
                return new ConValue(left.equals(right), Type.BOOLEAN);
        }

        throw new RuntimeException("not comparable variables: " + left + " and " + right);
    }

    @Override
    public ConValue visitIdExpr(ConscienceParser.IdExprContext ctx) {
        return scope.resolve(ctx.ID().getText());
    }

    @Override
    public ConValue visitIntegerExpr(ConscienceParser.IntegerExprContext ctx) {
        return new ConValue(Integer.valueOf(ctx.INTEGER().getText()), Type.INTEGER);
    }

    @Override
    public ConValue visitStringExpr(ConscienceParser.StringExprContext ctx) {
        String s = ctx.STRING().getText();

        return new ConValue(s.substring(1, s.length() - 1), Type.STRING);
    }

    @Override
    public ConValue visitBooleanExpr(ConscienceParser.BooleanExprContext ctx) {
        return new ConValue(Boolean.valueOf(ctx.BOOLEAN().getText()), Type.BOOLEAN);
    }

    @Override
    public ConValue visitParenExpr(ConscienceParser.ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    public static void main(String[] args) throws IOException {
        String fileName = args[0];

        CharStream input = CharStreams.fromFileName(fileName);
        ConscienceLexer lexer = new ConscienceLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ConscienceParser parser = new ConscienceParser(tokens);

        ParseTree tree = parser.file();
        EvalVisitor visitor = new EvalVisitor(new Scope());
        visitor.visit(tree);
    }
}
