
public class InputBlock extends Block{

    private int counter = -1;

    public InputBlock(String fileName) {
        load();
    }

    public String getData() {
        int nextDataIndex = computeNextDataIndex();
        return getRecord(nextDataIndex);
    }

    public String getCurrentData() {
        if(counter == -1) {
            getData();
        }
        return getRecord(counter);
    }

    private void load() {
        loadNextBlockIntoMemory();
        counter = -1;
    }

    private void loadNextBlockIntoMemory() {

    }

    private int computeNextDataIndex(){
        if(counter < BLOCK_MAX_INDEX) {
            counter = counter + 1;
        }
        else {
            load();
            counter = counter + 1;
        }
        return counter;
    }

    private String getRecord(int index) {
        return String.valueOf(index);
    }
}
