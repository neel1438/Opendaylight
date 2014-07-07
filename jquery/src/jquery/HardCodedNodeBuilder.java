package jquery;


import org.w3c.dom.Node;

public class HardCodedNodeBuilder {
	Node texta,textb,textc1,textc2,a,b,c1,c2,root;

	public HardCodedNodeBuilder()
	{
		textc1 = new NodeBuilder("#text",Node.TEXT_NODE,"Some Value",false,null,"#text",null,c1,null, false,null,null);
		c1 = new NodeBuilder("c1",Node.ELEMENT_NODE,null,false,null,"c1",null,b,c2, true,textc1,textc1);
	
		textc2 = new NodeBuilder("#text",Node.TEXT_NODE,"Some Other Value",false,null,"#text",null,c2,null, false,null,null);
		c2 = new NodeBuilder("c2",Node.ELEMENT_NODE,null,false,null,"c2",null,b,null, true,textc2,textc2);
	
		textb = new NodeBuilder("#text",Node.TEXT_NODE,null,false,null,"#text",null,b,c1, false,null,null);
		b = new NodeBuilder("b",Node.ELEMENT_NODE,null,false,null,"b",null,a,null, true,textb,c2);
	
		texta = new NodeBuilder("#text",Node.TEXT_NODE,null,false,null,"#text",null,a,b, false,null,null);
		a = new NodeBuilder("a",Node.ELEMENT_NODE,null,false,null,"a",null,root,null, true,texta,b);
	
	
		root = new NodeBuilder("#document",Node.DOCUMENT_NODE,null,false,null,"#document",null,null,null, true,a,a);
	}
	

}
