import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class InputBlock extends Block{

    private int counter = -1;
    private String _fileName;
    LineNumberReader _reader;
    ArrayList<String> _currrentBlock = new ArrayList<>();

    public InputBlock(String fileName) {
        try {
            _fileName = fileName;
            String fullPathToFile = Constants.DATA_DIR + fileName;
            _reader = new LineNumberReader(new InputStreamReader(new FileInputStream(fullPathToFile), "UTF-8"));
            load();
        }
        catch (Exception e){
            System.out.println("Exception Occured" + e.getMessage());
        }
    }

    public String getData() {
        int nextDataIndex = computeNextDataIndex();
        return getRecord(nextDataIndex);
    }

    public boolean isDataAvailable() {
        return _currrentBlock.size() > 0;
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
        _currrentBlock = getBlock();
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
        if(isDataAvailable()) {
            return _currrentBlock.get(index);
        }
        else {
            return "";
        }
    }

    public ArrayList<String> getBlock()  {
        ArrayList<String> lines = new ArrayList<>();
        int lineCounter = 0;
        try{
            String line;
            while ( lineCounter < 40  && ((line = _reader.readLine()) != null)) {
                lines.add(line);
                lineCounter = lineCounter + 1;
            }
        }
        catch (Exception e)
        {
            e.fillInStackTrace();
        }
        System.out.println("LC " + lineCounter);
        return lines;
    }
}