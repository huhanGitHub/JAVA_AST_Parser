package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.SimpleName;
import util.Util;
import main.ASTGenerator;
import structure.MyMethodNode;

public class Main {
	public static void main(String[] args) throws IOException {
        String inputPath="testdata";
        String ouputPath="output/total.txt";
        if (args.length>=2){
            inputPath=args[0];
            ouputPath=args[1];
        }
	    try {
            ArrayList<File> files=Util.getFiles(inputPath);
            for (File f:files){
                access(f,ouputPath);
            }

        }catch (Exception e){
	        e.printStackTrace();
        }

	}

	public static void access(File input, String outputPath){
	    System.out.println(input.getAbsolutePath());
        ASTGenerator astGenerator = new ASTGenerator(input);
        List<MyMethodNode> methodNodeList = astGenerator.getMethodNodeList();
        for (MyMethodNode m : methodNodeList) {
            Javadoc doc = m.methodNode.getJavadoc();
            SimpleName name = m.methodNode.getName();
            Block body = m.methodNode.getBody();
            if (doc!=null && name !=null && body!=null){
                System.out.println(name);
                String docStr=doc.toString();
                if (docStr.indexOf(".")>0)
                    docStr = docStr.substring(0,doc.toString().indexOf("."));
                docStr=Util.removePunctuation(docStr);
                docStr=Util.replaceBlank(docStr).trim().toLowerCase();

                String nameStr = Util.splitWordsByCapitalLetter(name.toString()).toLowerCase();

                String bodyStr = Util.removeComment(body.toString());
                bodyStr = Util.removePunctuation(bodyStr);
                bodyStr=Util.replaceBlank(bodyStr).trim().toLowerCase();

                String ast = ASTtoDOT.astFormalParser(m);
                //"name","doc","body","entities:(hashcode, type),...:relations:(hashcode1->hashcode2)..."
                String ret = "\""+nameStr+"\",\""+docStr+"\",\""+bodyStr+"\",\""+ast+"\"\n";
                Util.writeFile(outputPath , ret);
            }
            else
                System.out.println("miss value!!!!!!!");
        }

    }

    public static void dot(){
        String FilePath = "testdata/test.java";
        String outputDir = "output/";
        File f = new File(FilePath);
        ASTGenerator astGenerator = new ASTGenerator(f);
        List<MyMethodNode> methodNodeList = astGenerator.getMethodNodeList();
        for (MyMethodNode m : methodNodeList) {
            Javadoc doc = m.methodNode.getJavadoc();
            SimpleName name = m.methodNode.getName();
            Block body = m.methodNode.getBody();
            if (doc!=null && name !=null && body!=null)
                System.out.println(doc+"-----"+name+"-----"+body);
            String dotStr = ASTtoDOT.ASTtoDotParser(m);
            Util.writeFile(outputDir + f.getName() + "_" + m.methodNode.getName() + ".dot", dotStr);
        }
        System.out.println("Done.");
    }
}
