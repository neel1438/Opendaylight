package jquery;


import org.w3c.dom.Node;

public class HardCodedNodeBuilder {
	Node texta,textb,textc2,textc1;
	Node a,b,c1,c2;
	Node root;

	public HardCodedNodeBuilder()
	{
		textc1 = new NodeBuilderText("#text",Node.TEXT_NODE,"Some Value","Some Value",false,null,"#text",null,c1,null, false,null,null);
		c1 = new NodeBuilderElement("c1",Node.ELEMENT_NODE,null,"Some Value",false,null,"c1",null,b,c2, true,textc1,textc1);
	
		textc2 = new NodeBuilderText("#text",Node.TEXT_NODE,"Some Other Value","Some Other Value",false,null,"#text",null,c2,null, false,null,null);
		c2 = new NodeBuilderElement("c2",Node.ELEMENT_NODE,null,"Some Other Value",false,null,"c2",null,b,null, true,textc2,textc2);
	
		textb = new NodeBuilderText("#text",Node.TEXT_NODE,null,"\nSome Value\nSome Other Value\n",false,null,"#text",null,b,c1, false,null,null);
		b = new NodeBuilderElement("b",Node.ELEMENT_NODE,null,"\nSome Value\nSome Other Value\n",false,null,"b",null,a,null, true,textb,c2);
	
		texta = new NodeBuilderText("#text",Node.TEXT_NODE,null,"\n\nSome Value\nSome Other Value\n\n",false,null,"#text",null,a,b, false,null,null);
		a = new NodeBuilderElement("a",Node.ELEMENT_NODE,null,"\n\nSome Value\nSome Other Value\n\n",false,null,"a",null,root,null, true,texta,b);
	
	
		root = new NodeBuilder("#document",Node.DOCUMENT_NODE,null,null,false,null,"#document",null,null,null, true,a,a);
	}
	

}
