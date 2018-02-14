import java.util.ArrayList;

public class BlockManager {

    private ArrayList<InputBlock> _inputBlocks = new ArrayList<>();

    private OutputBlock _outputBlock = new OutputBlock();

    private int _fileCount;

    public BlockManager(int fileCount) {
        _fileCount = fileCount;
        for (int i =1; i<=fileCount; i++) {
            InputBlock ip = new InputBlock(String.valueOf(i) + ".txt");
            _inputBlocks.add(ip);
        }
    }

    public  void execute() {
        InputBlock minInputBlock = _inputBlocks.get(0);
        for (InputBlock inputBlock : _inputBlocks) {
            if(inputBlock.getCurrentData().compareTo(minInputBlock.getCurrentData()) < 0)
            {
                minInputBlock = inputBlock;
            }
        }
        _outputBlock.add(minInputBlock.getCurrentData());
        minInputBlock.getData();
    }
}
