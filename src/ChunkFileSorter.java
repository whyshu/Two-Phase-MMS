import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ChunkFileSorter {
	private ArrayList<String> _chunkFileList;
	public ChunkFileSorter(ArrayList<String> chunkFileList){
		_chunkFileList=chunkFileList;
	}
	public void sort(){
		try {
			for(String fileName:_chunkFileList){
				//Open the file
				String fullPathtoFile = Constants.DATA_DIR + fileName;
				File file=new File(fullPathtoFile);
				Scanner sortScan=new Scanner(file);
			    List<Integer> L = new ArrayList<Integer>();
				while(sortScan.hasNextLine()){
					L.add(Integer.parseInt(sortScan.nextLine()));
				}
				file.delete();
				Collections.sort(L);
				writeFile(fileName.replace(Constants.UNSORTED_FILE_PREFIX, Constants.SORTED_FILE_PREFIX),L);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeFile(String fileName,List sortedLines){
		BufferedWriter bw=null;
        try {
            try {
            	String fullPathToFile = Constants.DATA_DIR + fileName;
                bw = new BufferedWriter(new FileWriter(fullPathToFile,true));
                for (Object value : sortedLines) {
                	System.out.println("Sorted::"+value.toString()+"File name :: "+fileName);
                    bw.write(value.toString());
                    bw.newLine();
                }
            }finally {
                bw.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
	}
}
