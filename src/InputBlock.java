import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class InputBlock extends Block{

    private int counter = -1;
    private String _fileName;
    LineNumberReader _reader;
    ArrayList<Student> _currentBlock = new ArrayList<>();

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

    public Student getData() {
        int nextDataIndex = computeNextDataIndex();
        return getRecord(nextDataIndex);
    }

    public boolean isDataAvailable() {
        boolean isDataAvailable =  counter < _currentBlock.size();
//        if(!isDataAvailable) {
//            System.out.println("Data not available" + _fileName);
//        }
        return isDataAvailable;
    }

    public Student getCurrentData() {
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
        _currentBlock = getBlock();
    }
    
    public void finish() {
    	if(_reader != null) {
    		try {
				_reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
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

    private Student getRecord(int index) {
    	try{
	        if(isDataAvailable()) {
	            return _currentBlock.get(index);
	        }
	        else {
	    		System.out.println("Requested block :: "+index+"File name :: "+_fileName+"Total number of lines :: "+_currentBlock.size());
	            return null;
	        }
    	}catch(Exception e){
    		System.out.println(e.getMessage() + "Requested block :: "+index+"File name :: "+_fileName+"Total number of lines :: "+_currentBlock.size());
    	}
    	return null;
    }

    public ArrayList<Student> getBlock()  {
        ArrayList<Student> lines = new ArrayList<>();
        int lineCounter = 0;
        try{
            String line;
            while ( lineCounter < 40  && ((line = _reader.readLine()) != null)) {
                //System.out.println(line);
                Student s=new Student(line);
                //System.out.println("Object Created");
                lines.add(s);
                lineCounter = lineCounter + 1;
            }
        }
        catch (Exception e)
        {
           System.out.println(e.getMessage());
        }
        return lines;
    }
}