package main;

import org.eclipse.jdt.core.dom.ASTNode;

import structure.MyASTNode;
import structure.MyMethodNode;

public class ASTtoDOT {

	public static String ASTtoDotParser(MyMethodNode m) {
		String str = "digraph \"DirectedGraph\" {\n";
		// name
		str += ("graph [label = \"" + m.methodNode.getName() + "\", labelloc=t, concentrate = true];\n");
		for (MyASTNode mn : m.nodeList) {
			ASTNode astNode = mn.astNode;
			int hashcode = astNode.hashCode();
			int nodeType = astNode.getNodeType();

			int lineNum = mn.lineNum;
			str += ("\"" + String.valueOf(hashcode) + "\" [ type=" + String.valueOf(nodeType) + " line="
					+ String.valueOf(lineNum) + " ]\n");
		}
		for (int[] k : m.mapping) {
			int pHashcode = k[0];
			int hashcode = k[1];
			str += ("\"" + String.valueOf(pHashcode) + "\" -> \"" + String.valueOf(hashcode) + "\"\n");
		}
		str += "}\n";
		return str;
	}

    public static String astFormalParser(MyMethodNode m) {
        String str = "";
        // name
        str+="entities:";
        for (MyASTNode mn : m.nodeList) {
            ASTNode astNode = mn.astNode;
            int hashcode = astNode.hashCode();
            int nodeType = astNode.getNodeType();
            str +=  "("+String.valueOf(hashcode) +","+ String.valueOf(nodeType) +")" + ",";
        }
        str=str.substring(0,str.length()-1);
        str+=":";
        str+="relations:";
        for (int[] k : m.mapping) {
            int pHashcode = k[0];
            int hashcode = k[1];
            str += "("+(String.valueOf(pHashcode) + " -> " + String.valueOf(hashcode)+")" + ",");
        }
        str=str.substring(0,str.length()-1);
        return str;
    }
}
