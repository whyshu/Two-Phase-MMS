import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlockManager {

    private ArrayList<InputBlock> _inputBlocks = new ArrayList<>();

    private OutputBlock _outputBlock = new OutputBlock();

    private int _fileCount;

    public BlockManager(int fileCount) {
        _fileCount = fileCount;
        for (int i = 1; i<=fileCount; i++) {
            InputBlock ip = new InputBlock(String.valueOf(i) + ".txt");
            _inputBlocks.add(ip);
        }
    }

    public  void execute() {
        List<InputBlock> blocks = _inputBlocks.stream().filter(x -> x.isDataAvailable()).collect(Collectors.toList());
        InputBlock minInputBlock = blocks.get(0);
        for (InputBlock inputBlock : blocks) {
            if (Integer.parseInt(inputBlock.getCurrentData()) < Integer.parseInt(minInputBlock.getCurrentData())) {
                minInputBlock = inputBlock;
            }
        }
        _outputBlock.add(minInputBlock.getCurrentData());
        minInputBlock.getData();
    }
}
