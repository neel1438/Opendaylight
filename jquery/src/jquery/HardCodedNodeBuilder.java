package jquery;


import org.w3c.dom.Node;

public class HardCodedNodeBuilder {
	Node texta,textb,textc2,textc1;
	Node a,b,c1,c2;
	Node root;

	public HardCodedNodeBuilder()
	{
		textc1 = new NodeBuilderText("Some Value",c1,null);
		c1 = new NodeBuilderElement("c1","Some Value",b,c2, true,textc1,textc1);
	
		textc2 = new NodeBuilderText("Some Other Value",c2,null);
		c2 = new NodeBuilderElement("c2","Some Other Value",b,null,true,textc2,textc2);
	
		textb = new NodeBuilderText("\nSome Value\nSome Other Value\n",b,c1);
		b = new NodeBuilderElement("b","\nSome Value\nSome Other Value\n",a,null,true,textb,c2);
	
		texta = new NodeBuilderText("\n\nSome Value\nSome Other Value\n\n",a,b);
		a = new NodeBuilderElement("a","\n\nSome Value\nSome Other Value\n\n",root,null,true,texta,b);
	
	
		root = new NodeBuilder("#document",Node.DOCUMENT_NODE,null,"\n\nSome Value\nSome Other Value\n\n",null,null,true,a,a);
	}
	

}
