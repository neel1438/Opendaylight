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
		Node[] c2Children={textc2};
		c2.setChildren(c2Children);
		
		NodeBuilderElement c1 = new NodeBuilderElement("c1","Some Value",b,c2);
		NodeBuilderText textc1 = new NodeBuilderText("Some Value",c1,null);
		Node[] c1Children = {textc1};
		c1.setChildren(c1Children);
		
		Node[] bChildren={c1,c2};
		b.setChildren(bChildren);
		
		Node[] aChildren={b};
		temp_a.setChildren(aChildren);
		a=temp_a;
	}
	

}
