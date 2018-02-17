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
					HashMap<Integer, Student> studentHashMap = new HashMap<>();
					List<Integer> studentIDList = new ArrayList<>();
					while (sortScan.hasNextLine()) {
						Student student = new Student(sortScan.nextLine());
						studentIDList.add(student.ID);
						studentHashMap.put(student.ID, student);
					}
					Collections.sort(studentIDList);
					writeFile(fileName.replace(Constants.UNSORTED_FILE_PREFIX, Constants.SORTED_FILE_PREFIX), studentIDList, studentHashMap);
					sortedChunkFileList.add(fileName.replace(Constants.UNSORTED_FILE_PREFIX, Constants.SORTED_FILE_PREFIX));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return sortedChunkFileList;
	}

	public void writeFile(String fileName,List<Integer> studentIDList,Map<Integer,Student> sortedStudentMap){
		BufferedWriter bw=null;
        try {
            try {
            	String fullPathToFile = Constants.DATA_DIR + fileName;
                bw = new BufferedWriter(new FileWriter(fullPathToFile,true));
				for (Integer studentID:studentIDList){
					//System.out.println(sortedStudentMap.get(studentID).toString());
                    bw.write(sortedStudentMap.get(studentID).toString());
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
