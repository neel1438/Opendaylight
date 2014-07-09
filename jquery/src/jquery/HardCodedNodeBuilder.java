package jquery;


import org.w3c.dom.Node;


public class HardCodedNodeBuilder {
	Node a;
	

	public HardCodedNodeBuilder()
	{
		NodeBuilderElement temp_a = new NodeBuilderElement("a","\n\nSome Value\nSome Other Value\n\n",null,null);
		
		NodeBuilderElement b = new NodeBuilderElement("b","\nSome Value\nSome Other Value\n\n",temp_a,null);

		NodeBuilderElement c2 = new NodeBuilderElement("c2","Some Other Value",b,null);
		NodeBuilderText textc2 = new NodeBuilderText("Some Other Value",c2,null);
		c2.setChildren(textc2);
		
		NodeBuilderElement c1 = new NodeBuilderElement("c1","Some Value",b,c2);
		NodeBuilderText textc1 = new NodeBuilderText("Some Value",c1,null);
		
		c1.setChildren(textc1);
		b.setChildren(c1,c2);
		temp_a.setChildren(b);
		a=temp_a;
	}
	

}
