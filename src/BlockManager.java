import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlockManager {

    private ArrayList<InputBlock> _inputBlocks = new ArrayList<>();

    private OutputBlock _outputBlock = new OutputBlock();

    private int _fileCount;

    public BlockManager(int fileCount) {
        _fileCount = fileCount;
        for (int i = 0; i < fileCount; i++) {
            InputBlock ip = new InputBlock(Constants.SORTED_FILE_PREFIX + String.valueOf(i) + ".txt");
            _inputBlocks.add(ip);
        }
        //System.out.println(_inputBlocks.size());
    }
    
    public void finish() {
    	_outputBlock.finish();
    	for (InputBlock inputblock : _inputBlocks) {
    		inputblock.finish();
    	}
    }

    public void execute() {
        List<InputBlock> blocks = _inputBlocks.stream().filter(x -> x.isDataAvailable()).collect(Collectors.toList());
        //System.out.println(blocks.size());
        InputBlock minInputBlock = blocks.get(0);
        for (InputBlock inputBlock : blocks) {
            if (inputBlock.getCurrentData().ID < minInputBlock.getCurrentData().ID) {
                minInputBlock = inputBlock;
            }
        }
        _outputBlock.add(minInputBlock.getCurrentData());
        minInputBlock.getData();
    }
}