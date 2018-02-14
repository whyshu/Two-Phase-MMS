

public class OutputBlock extends Block{

    private int counter = -1;

    public OutputBlock() {

    }

    public void add(String data) {
        counter = counter + 1;
        addToBuffer(counter, data);
        if(counter == BLOCK_MAX_INDEX) {
            writeToFile();
            counter = -1;
        }
    }

    private void addToBuffer(int counter, String data) {
        System.out.println("Added to output buffer " + counter + data);
    }

    private void writeToFile() {
        System.out.println("Block has been written to File");
        freeBuffer();
    }
    private void freeBuffer(){
        System.out.println("Output Buffer has been freed");
    }
}
