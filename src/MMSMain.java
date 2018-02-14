public class MMSMain {

    public static void main(String[] args) {
        System.out.println("Hello World");
        InputBlock ip = new InputBlock("0.txt");
        OutputBlock op = new OutputBlock();
        for (int i =0; i< 120; i++) {
            String data = ip.getData();
            //System.out.println(data);
            op.add(data);
        }
    }
}