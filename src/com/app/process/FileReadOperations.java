package com.app.process;
import java.io.*;
import java.util.*;
public class FileReadOperations {
	
	
	public FileReadOperations(){
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Create and write a sample file
		try {                              // For writing into file
			FileReadOperations obj=new FileReadOperations();
			obj.fileprocess();
			
			File file=new File("C:\\Users\\KIIT\\Desktop\\Coding\\CHUBB\\Day7_30_10_25\\userdetails.txt");
		FileWriter writer=new FileWriter(file,true);  // passing appendable as true. It will append not override
			writer.append("Hello Java\n This is a Scanner example");
		
			writer.flush();
			
			
			File f=new File("C:\\Users\\KIIT\\Desktop\\Coding\\CHUBB\\Day7_30_10_25\\userdetails.txt");
//		File f=new File(""); // file path
//				FileWriter wobj=new FileWriter(file);
//				
//		wobj.write("this is test");		
//		
//		wobj.flush();  // flushing so that no data will be rremaining
//		
//		wobj.close();
		
			
			System.out.println(f.getAbsolutePath());
			System.out.println("Exists: "+f.exists());
			
			
		Scanner sc=new Scanner(f);
			while(sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void fileprocess() throws IOException{
		// Create a file and write content
		try(FileWriter writer=new FileWriter("fileReader.txt")) {
			writer.write("Learning FileReader and FileWriter in Java");
			
		}   // Write into a file and then read it
		// Read using FileReader
//		try(FileReader reader=new FileReader("fileReader.txt")){
		
		try(FileReader reader=new FileReader("C:\\Users\\KIIT\\Desktop\\Coding\\CHUBB\\Day7_30_10_25\\userdetails.txt")){
			int ch;
			while((ch=reader.read())!=-1) {
				System.out.println((char)ch);
			}
		}
	}
	
	public void readFile(String filepath) throws FileNotFoundException, IOException{
		try(FileReader reader=new FileReader(filepath)){
			int ch;
			while((ch=reader.read()) !=-1) {
				System.out.println((char)ch);
			}
		}
	}
	
	public void writeFilewithAppend(String filepath) {
		try {
//			FileReadOperations obj=new FileReadOperations();
			// obj.fileprocess()
			
			File file=new File(filepath);
			FileWriter writer=new FileWriter(file,true);
			writer.write("Hello Java\\n This is a Scanner example");
			
			writer.flush();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void writeFilewithOverride(String filepath) {
		try {
//			FileReadOperations obj=new FileReadOperations();
			// obj.fileprocess()
			
			File file=new File(filepath);
			FileWriter writer=new FileWriter(file);
			writer.write("Hello Java\\n This is a Scanner example");
			
			writer.flush();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
