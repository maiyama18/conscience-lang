import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends ConscienceBaseVisitor<Integer> {
    private Map<String, Integer> variables = new HashMap<>();

    @Override
    public Integer visitFile(ConscienceParser.FileContext ctx) {
        return visit(ctx.block());
    }

    @Override
    public Integer visitBlock(ConscienceParser.BlockContext ctx) {
        ctx.stat().forEach(stat -> visit(stat));

        return 0;
    }

    @Override
    public Integer visitAssignStat(ConscienceParser.AssignStatContext ctx) {
        String var = ctx.ID().getText();
        Integer value = visit(ctx.expr());

        variables.put(var, value);

        return value;
    }

    @Override
    public Integer visitPrintStat(ConscienceParser.PrintStatContext ctx) {
        Integer value = visit(ctx.expr());

        System.out.println(value);

        return value;
    }

    @Override
    public Integer visitIfStat(ConscienceParser.IfStatContext ctx) {
        if (!visit(ctx.expr()).equals(0)) {
            visit(ctx.block());
        }

        return 0;
    }

    @Override
    public Integer visitWhileStat(ConscienceParser.WhileStatContext ctx) {
        while (!visit(ctx.expr()).equals(0)) {
            visit(ctx.block());
        }

        return 0;
    }

    @Override
    public Integer visitMulDivModExpr(ConscienceParser.MulDivModExprContext ctx) {
        switch (ctx.op.getText()) {
            case "*":
                return visit(ctx.expr(0)) * visit(ctx.expr(1));
            case "/":
                return visit(ctx.expr(0)) / visit(ctx.expr(1));
            case "%":
                return visit(ctx.expr(0)) % visit(ctx.expr(1));
        }

        System.out.println("undefined operator: " + ctx.op.getText());
        return null;
    }

    @Override
    public Integer visitAddSubExpr(ConscienceParser.AddSubExprContext ctx) {
        switch (ctx.op.getText()) {
            case "+":
                return visit(ctx.expr(0)) + visit(ctx.expr(1));
            case "-":
                return visit(ctx.expr(0)) - visit(ctx.expr(1));
        }

        System.out.println("undefined operator: " + ctx.op.getText());
        return null;
    }

    @Override
    public Integer visitIdExpr(ConscienceParser.IdExprContext ctx) {
        return variables.get(ctx.ID().getText());
    }

    @Override
    public Integer visitIntExpr(ConscienceParser.IntExprContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitParenExpr(ConscienceParser.ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    public static void main(String[] args) throws IOException {
        String fileName = args[0];

        CharStream input = CharStreams.fromFileName(fileName);
        ConscienceLexer lexer = new ConscienceLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ConscienceParser parser = new ConscienceParser(tokens);

        ParseTree tree = parser.file();
        EvalVisitor visitor = new EvalVisitor();
        visitor.visit(tree);
    }
}
