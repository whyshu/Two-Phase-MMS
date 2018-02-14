public class MMSMain {

    public static void main(String[] args) {
        System.out.println("Hello World");
        InputBlock ip = new InputBlock("0.txt");
        OutputBlock op = new OutputBlock();
        for (int i =3; i< 120; i = i +3) {
            //String data = ip.getData();
            System.out.println(i);
            //op.add(data);
        }
    }
}