import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This Class is responsible for Merging all the Sorted chunk files
 */
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

    /**
     * Fetches the top most item in each input block (Each sorted chunk)
     * 
     * Sort the items
     * 
     * Adds the first element in the sorted list in the output block
     * 
     * Fetches the next item only from the block from which the item is added to output
     * 
     */
    public void execute() {
        List<InputBlock> blocks = _inputBlocks.stream().filter(x -> x.isDataAvailable()).collect(Collectors.toList());
        //System.out.println(blocks.size());
        InputBlock minInputBlock = blocks.get(0);
        for (InputBlock inputBlock : blocks) {
            if (inputBlock.getCurrentData().ID < minInputBlock.getCurrentData().ID) {
                minInputBlock = inputBlock;
            }
        }
        _outputBlock.add(minInputBlock.getCurrentData().toString());
        minInputBlock.getData();
    }
}