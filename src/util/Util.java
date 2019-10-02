package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Util {

	public static void writeFile(String FilePath, String str) {
		try {
			FileWriter writer = new FileWriter(FilePath, true);
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public static ArrayList<File> getFiles(String path) throws Exception{
        ArrayList<File> fileList=new ArrayList<File>();
        File file=new File(path);
        if (file.isDirectory()){
            File []files=file.listFiles();
            for (File fileIndex:files){
                if (fileIndex.isDirectory()){
                    fileList.addAll(getFiles(fileIndex.getPath()));
                }else {
                    String name=fileIndex.getName();
                    //System.out.println(name);
                    if (name.substring(name.lastIndexOf(".")+1).equals("java")){
                        //System.out.println("choice:___________________"+name);
                        fileList.add(fileIndex);
                    }
                }
            }
        }else {
            String name = file.getName();
            //System.out.println(name);
            if (name.substring(name.lastIndexOf(".") + 1).equals("java")) {
                //System.out.println("choice:___________________"+name);
                fileList.add(file);
            }
        }
        return fileList;
    }

    public static String removeComment(String code){
        String []codeLines=code.split("\n");
        StringBuffer sb=new StringBuffer("");
        for (int i=0;i<codeLines.length;i++){
            if(codeLines[i].indexOf("//")>=0){
                System.out.println(codeLines[i]);
            }else {
                sb.append(codeLines[i]);
            }
        }
        return sb.toString();
    }

    public static String removePunctuation(String str){
        String s=str.replaceAll("[\\pP\\p{Punct}]"," ");
        return s;
    }

    public static String splitWordsByCapitalLetter(String str){
        if (str.length()<2)
            return str;
        String[] strs=str.split(" ");
        StringBuffer all=new StringBuffer();
        for (int i=0; i < strs.length; i++){
            StringBuffer sb=new StringBuffer(strs[i]);

            Boolean allIsCapital=true;
            for (int j=0; j<sb.length();j++){
                Boolean isCapital = Character.isUpperCase(sb.charAt(j));
                allIsCapital = allIsCapital && isCapital;

                if (isCapital && j != 0) {
                    sb.insert(j, " ");
                    j++;
                }
            }
            if (!allIsCapital)
                all.append(sb+" ");
            else
                all.append(strs[i]+" ");
        }
        return all.toString().substring(0, all.length()-1);
    }

    public static String replaceBlank(String str) {
        String s = str.replaceAll("\\s{1,}"," ");
        return s;
    }
}
