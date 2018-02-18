import sun.reflect.generics.tree.Tree;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class ChunkFileSorter {
	private ArrayList<String> _chunkFileList;
	public ChunkFileSorter(ArrayList<String> chunkFileList){
		_chunkFileList=chunkFileList;
	}
	public ArrayList<String> sort(){
		ArrayList<String> sortedChunkFileList=new ArrayList<>();
		try {
			for(String fileName:_chunkFileList){
					//Open the file
					String fullPathtoFile = Constants.DATA_DIR + fileName;
					File file = new File(fullPathtoFile);
					Scanner sortScan = new Scanner(file);
					List<String> studentList = new ArrayList<>();
					while (sortScan.hasNextLine()) {
						Student s=new Student(sortScan.nextLine());
						studentList.add(s.toString());
					}
					studentList.sort(Comparator.comparing(s -> Integer.parseInt(s.substring(0, 8))));
					writeFile(fileName.replace(Constants.UNSORTED_FILE_PREFIX, Constants.SORTED_FILE_PREFIX), studentList);
					sortedChunkFileList.add(fileName.replace(Constants.UNSORTED_FILE_PREFIX, Constants.SORTED_FILE_PREFIX));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return sortedChunkFileList;
	}

	public void writeFile(String fileName,List<String> studentList){
		BufferedWriter bw=null;
        try {
            try {
            	String fullPathToFile = Constants.DATA_DIR + fileName;
                bw = new BufferedWriter(new FileWriter(fullPathToFile,true));
				for (String student:studentList){
                    bw.write(student);
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

