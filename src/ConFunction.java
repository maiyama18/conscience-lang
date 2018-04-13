import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class ConFunction {
    private List<TerminalNode> params;
    private ConscienceParser.BlockContext block;

    public ConFunction(List<TerminalNode> params, ConscienceParser.BlockContext block) {
        this.params = params;
        this.block = block;
    }

    public List<TerminalNode> getParams() {
        return params;
    }

    public ConscienceParser.BlockContext getBlock() {
        return block;
    }
}
